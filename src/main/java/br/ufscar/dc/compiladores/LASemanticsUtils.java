package br.ufscar.dc.compiladores;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.tool.LabelType;

import br.ufscar.dc.compiladores.LAGrammarParser.Exp_relacionalContext;
import br.ufscar.dc.compiladores.LAGrammarParser.ExpressaoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Fator_logicoContext;
// import br.ufscar.dc.compiladores.LAGrammarParser.ExpressaoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.IdentificadorContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Parcela_logicaContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Parcela_nao_unarioContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Parcela_unarioContext;
import br.ufscar.dc.compiladores.LAGrammarParser.RegistroContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Termo_logicoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.TipoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Tipo_basicoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Tipo_basico_identContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Tipo_estendidoContext;
import br.ufscar.dc.compiladores.SymbolTableInput.LATypes;

public class LASemanticsUtils {
    public static List<String> errosSemanticos = new ArrayList<>();

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.CmdAtribuicaoContext ctx) {
        String ident = ctx.identificador().getText();
        // ExpressaoContext expCtx = ctx.expressao();
        LATypes identType = tabela.peek(ident).getType();
        // LATypes expType = verificarTipo(tabela, expCtx);
        String expStr = ctx.expressao().getText();

        for(var id: tabela.getKeys()) {
            SymbolTableInput idObj = tabela.peek(id);
            String idName = idObj.getName();
            LATypes idType = idObj.getType();
            // adicionarErroSemantico(ctx.start, idType.toString());

            if(expStr.contains(idName)) {
                if(idType != identType)
                    return LATypes.ERROR;
            }
        }

        for(int i = 0; i < 10; i++) {
            if(expStr.contains("+" + String.valueOf(i))) {
                return LATypes.ERROR;
            }
        }

        return identType;
        
    }

    private static LATypes verificarTipo(SymbolTable tabela, ExpressaoContext ctx) {
        if(ctx.op_logico_1().size() != 0) {
            return LATypes.BOOL;
        }
        LATypes aux = null;
        for (var termo : ctx.termo_logico()) {
            LATypes termo_type = verificarTipo(tabela, termo);
            if (aux == null)
                aux = termo_type;
            else if (aux != termo_type || termo_type == LATypes.ERROR) {

                return LATypes.ERROR;
            }
        }
        return aux;
    }

    private static LATypes verificarTipo(SymbolTable tabela, Termo_logicoContext ctx) {
        if(ctx.op_logico_2().size() != 0) {
            return LATypes.BOOL;
        }
        
        LATypes aux = null;
        for (var fator : ctx.fator_logico()) {
            LATypes fator_type = verificarTipo(tabela, fator);
            if (aux == null)
                aux = fator_type;
            else if (aux != fator_type || fator_type == LATypes.ERROR)

                return LATypes.ERROR;
        }
        return aux;
    }

    private static LATypes verificarTipo(SymbolTable tabela, Fator_logicoContext ctx) {
        if(ctx.getText().contains("nao")) {
            return LATypes.BOOL;
        }
        return verificarTipo(tabela, ctx.parcela_logica());
    }

    private static LATypes verificarTipo(SymbolTable tabela, Parcela_logicaContext ctx) {
        if (ctx.exp_relacional() != null) {
            return verificarTipo(tabela, ctx.exp_relacional());
        }
        return LATypes.BOOL;
    }

    private static LATypes verificarTipo(SymbolTable tabela, Exp_relacionalContext ctx) {
        if (ctx.exp_aritmetica().size() > 1)
            return LATypes.BOOL;

        return verificarTipo(tabela, ctx.exp_aritmetica(0));
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.Exp_aritmeticaContext ctx) {
        LATypes aux = null;
        for (var ta : ctx.termo()) {
            LATypes termo_type = verificarTipo(tabela, ta);
            if (aux == null) {
                aux = termo_type;
            } else if (aux != termo_type || termo_type == LATypes.ERROR) {
                return LATypes.ERROR;
            }
        }
        return aux;
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.TermoContext ctx) {
        LATypes aux = null;

        for (var fa : ctx.fator()) {
            LATypes fator_type = verificarTipo(tabela, fa);
            if (aux == null) {
                aux = fator_type;
            } else if (aux != fator_type || fator_type == LATypes.ERROR) {

                return LATypes.ERROR;
            }
        }
        return aux;
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.FatorContext ctx) {
        LATypes aux = null;

        for (var fa : ctx.parcela()) {
            LATypes parcela_type = verificarTipo(tabela, fa);
            if (aux == null) {
                aux = parcela_type;
            } else if (aux != parcela_type || parcela_type == LATypes.ERROR) {

                return LATypes.ERROR;
            }
        }
        return aux;
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.ParcelaContext ctx) {
        if (ctx.op_unario() != null) {

            return verificarTipo(tabela, ctx.parcela_unario());
        } else if (ctx.op_unario() == null && ctx.parcela_unario() != null) {
            // adicionarErroSemantico(ctx.start, "Parcela unaria exige o uso do operador
            // unario");

            return LATypes.ERROR;
        } else if (ctx.op_unario() != null && ctx.parcela_nao_unario() != null) {
            // adicionarErroSemantico(ctx.start, "Parcela nao unaria nao pode ser precidida
            // por um operador unario");

            return LATypes.ERROR;
        } else {
            return verificarTipo(tabela, ctx.parcela_nao_unario());
        }
    }

    private static LATypes verificarTipo(SymbolTable tabela, Parcela_nao_unarioContext ctx) {
        if (ctx.CADEIA() != null) {
            return LATypes.STR;
        } else {
            return verificarTipo(tabela, ctx.identificador());
        }
    }

    private static LATypes verificarTipo(SymbolTable tabela, IdentificadorContext ctx) {
        if (!tabela.contains(ctx.getText())) {

            return LATypes.ERROR;
        }
        return verificarTipo(tabela, ctx.getText());
    }

    private static LATypes verificarTipo(SymbolTable tabela, Parcela_unarioContext ctx) {
        if (ctx.NUM_INT() != null) {
            return LATypes.INT;
        } else if (ctx.NUM_REAL() != null) {
            return LATypes.REAL;
        } else if (ctx.identificador() != null) {
            return LATypes.IDENT;
        } else {
            return LATypes.BOOL;
        }
    }

    // private static LATypes verificarTipo(SymbolTable tabela,
    // List<ExpressaoContext> ctx) {
    // ExpressaoContext ret = ctx.remove(0);
    // for (var fa : ctx) {
    // LATypes aux = verificarTipo(tabela, fa);
    // if (aux == LATypes.ERROR) {
    // return LATypes.ERROR;
    // }
    // }
    // return verificarTipo(tabela, ret);
    // }

    // private static LATypes verificarTipo(SymbolTable tabela, ExpressaoContext
    // ctx) {
    // return LATypes.OP_RELACIONAL;
    // }

    public static LATypes verificarTipo(SymbolTable tabela, String nomeVar) {
        return tabela.peek(nomeVar).getType();
    }

    public static LATypes verificarTipo(SymbolTable tabela, TipoContext ctx) {
        if (ctx.registro() != null)
            return verificarTipo(tabela, ctx.registro());
        else
            return verificarTipo(tabela, ctx.tipo_estendido());
    }

    private static LATypes verificarTipo(SymbolTable tabela, Tipo_estendidoContext ctx) {
        return verificarTipo(tabela, ctx.tipo_basico_ident());
    }

    private static LATypes verificarTipo(SymbolTable tabela, Tipo_basico_identContext ctx) {
        if (ctx.IDENT() != null)
            return LATypes.IDENT;
        return verificarTipo(tabela, ctx.tipo_basico());
    }

    private static LATypes verificarTipo(SymbolTable tabela, Tipo_basicoContext ctx) {
        switch (ctx.getText()) {
            case "inteiro":
                return LATypes.INT;
            case "real":
                return LATypes.REAL;
            case "logico":
                return LATypes.BOOL;
            case "literal":
                return LATypes.STR;
            default:
                return LATypes.ERROR;
        }
    }

    private static LATypes verificarTipo(SymbolTable tabela, RegistroContext ctx) {
        return LATypes.IDENT;
    }
}