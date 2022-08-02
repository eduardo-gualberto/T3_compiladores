package br.ufscar.dc.compiladores;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

// import br.ufscar.dc.compiladores.LAGrammarParser.ExpressaoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.IdentificadorContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Parcela_nao_unarioContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Parcela_unarioContext;
import br.ufscar.dc.compiladores.SymbolTableInput.LATypes;

public class LASemanticsUtils {
    public static List<String> errosSemanticos = new ArrayList<>();
    public static PrintWriter pw;

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        int coluna = t.getCharPositionInLine();
        errosSemanticos.add(String.format("Erro %d:%d - %s", linha, coluna, mensagem));
        pw.write(String.format("Erro %d:%d - %s\n", linha, coluna, mensagem));
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.Exp_aritmeticaContext ctx) {
        LATypes ret = null;
        for (var ta : ctx.termo()) {
            LATypes aux = verificarTipo(tabela, ta);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != LATypes.ERROR) {
                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis");
                ret = LATypes.ERROR;
            }
        }

        return ret;
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.TermoContext ctx) {
        LATypes ret = null;

        for (var fa : ctx.fator()) {
            LATypes aux = verificarTipo(tabela, fa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != LATypes.ERROR) {
                adicionarErroSemantico(ctx.start, "Termo " + ctx.getText() + " contém tipos incompatíveis");
                ret = LATypes.ERROR;
            }
        }
        return ret;
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.FatorContext ctx) {
        LATypes ret = null;

        for (var fa : ctx.parcela()) {
            LATypes aux = verificarTipo(tabela, fa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != LATypes.ERROR) {
                adicionarErroSemantico(ctx.start, "Parcela " + ctx.getText() + " contém tipos incompatíveis");
                ret = LATypes.ERROR;
            }
        }
        return ret;
    }

    public static LATypes verificarTipo(SymbolTable tabela, LAGrammarParser.ParcelaContext ctx) {
        if (ctx.op_unario() != null) {
            return verificarTipo(tabela, ctx.parcela_unario());
        } else if (ctx.op_unario() == null && ctx.parcela_unario() != null) {
            adicionarErroSemantico(ctx.start, "Parcela unaria exige o uso do operador unario");
            return LATypes.ERROR;
        } else if (ctx.op_unario() != null && ctx.parcela_nao_unario() != null) {
            adicionarErroSemantico(ctx.start, "Parcela nao unaria nao pode ser precidida por um operador unario");
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
        return LATypes.IDENT;
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

    // private static LATypes verificarTipo(SymbolTable tabela, List<ExpressaoContext> ctx) {
    //     ExpressaoContext ret = ctx.remove(0);
    //     for (var fa : ctx) {
    //         LATypes aux = verificarTipo(tabela, fa);
    //         if (aux == LATypes.ERROR) {
    //             return LATypes.ERROR;
    //         }
    //     }
    //     return verificarTipo(tabela, ret);
    // }

    // private static LATypes verificarTipo(SymbolTable tabela, ExpressaoContext ctx) {
    //     return LATypes.OP_RELACIONAL;
    // }

    // public static LATypes verificarTipo(SymbolTable tabela, String nomeVar) {
    //     return tabela.peek(nomeVar).getType();
    // }
}