package br.ufscar.dc.compiladores;

import java.util.ArrayList;

import br.ufscar.dc.compiladores.LAGrammarParser.Declaracao_globalContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Declaracao_localContext;
import br.ufscar.dc.compiladores.LAGrammarParser.IdentificadorContext;
import br.ufscar.dc.compiladores.LAGrammarParser.ProgramaContext;
import br.ufscar.dc.compiladores.LAGrammarParser.TipoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Tipo_basico_identContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Valor_constanteContext;
import br.ufscar.dc.compiladores.LAGrammarParser.VariavelContext;
// import br.ufscar.dc.compiladores.LAGrammarParser.DeclaracoesContext;
// import br.ufscar.dc.compiladores.LAGrammarParser.TipoContext;
// import br.ufscar.dc.compiladores.LAGrammarParser.Tipo_basicoContext;
// import br.ufscar.dc.compiladores.LAGrammarParser.VariavelContext;
import br.ufscar.dc.compiladores.SymbolTableInput.LATypes;

public class LASemantics extends LAGrammarBaseVisitor<Void> {
    SymbolTable symbolTable = new SymbolTable();

    // Class entrypoint
    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracao_local(Declaracao_localContext ctx) {
        String varName;
        if (ctx.getText().contains("declare")) {
            varName = ctx.variavel().identificador(0).IDENT(0).getText();
        } else {
            varName = ctx.IDENT().getText();
        }

        if (symbolTable.contains(varName)) {
            LASemanticsUtils.adicionarErroSemantico(ctx.start, "Variavel ja declarada.");
        } else {
            symbolTable.insert(varName, LATypes.IDENT);
        }

        if (ctx.getText().contains("constante")) {
            String type = ctx.tipo_basico().getText();
            Valor_constanteContext value = ctx.valor_constante();
            boolean isValid = false;
            if (value.CADEIA() != null && type.equals("literal")) {
                isValid = true;
            } else if (value.NUM_INT() != null && type.equals("inteiro")) {
                isValid = true;
            } else if (value.NUM_REAL() != null && type.equals("real")) {
                isValid = true;
            } else if ((value.getText().equals("verdadeiro") || value.getText().equals("falso"))
                    && type.equals("logico")) {
                isValid = true;
            }

            if (!isValid) {
                LASemanticsUtils.adicionarErroSemantico(ctx.start, "Atribuição não compatível com o tipo declarado");
            }
        }

        if (ctx.getText().contains("declare")) {

        }

        return super.visitDeclaracao_local(ctx);
    }

    @Override
    public Void visitDeclaracao_global(Declaracao_globalContext ctx) {
        // TODO Auto-generated method stub

        String varName = ctx.IDENT().getText();

        if (symbolTable.contains(varName)) {
            LASemanticsUtils.adicionarErroSemantico(ctx.start, "Funcao ou procedimento ja declarada.");
        } else {
            symbolTable.insert(varName, LATypes.IDENT);
        }

        return super.visitDeclaracao_global(ctx);
    }

    @Override
    public Void visitIdentificador(IdentificadorContext ctx) {
        // TODO Auto-generated method stub

        for (var ident : ctx.IDENT()) {
            if (!symbolTable.contains(ident.getText())) {
                LASemanticsUtils.adicionarErroSemantico(ident.getSymbol(),
                        "Variavel utilizada mas nao declarada anteriormente.");
            }
        }

        return super.visitIdentificador(ctx);
    }

    // @Override
    // public Void visitVariavel(VariavelContext ctx) {
    //     // TODO Auto-generated method stub

    //     TipoContext type = ctx.tipo();
    //     String type_str = type.tipo_estendido().tipo_basico_ident().tipo_basico().getText();
    //     ArrayList<String> tipos_basicos = new ArrayList<String>();
    //     tipos_basicos.add("inteiro");
    //     tipos_basicos.add("real");
    //     tipos_basicos.add("logico");
    //     tipos_basicos.add("literal");

    //     if (!tipos_basicos.contains(type_str)) {
    //         LASemanticsUtils.adicionarErroSemantico(type.start, "Tipo utilizado nao declarado");
    //     }

    //     return super.visitVariavel(ctx);
    // }

    @Override
    public Void visitTipo_basico_ident(Tipo_basico_identContext ctx) {
        // TODO Auto-generated method stub

        if (ctx.IDENT() != null && !symbolTable.contains(ctx.IDENT().getText())) {
            LASemanticsUtils.adicionarErroSemantico(ctx.start, "Tipo utilizado mas nao declarado");
        }

        if (ctx.tipo_basico() != null) {
            String type_str = ctx.tipo_basico().getText();
            ArrayList<String> tipos_basicos = new ArrayList<String>();
            tipos_basicos.add("inteiro");
            tipos_basicos.add("real");
            tipos_basicos.add("logico");
            tipos_basicos.add("literal");

            if (!tipos_basicos.contains(type_str)) {
                LASemanticsUtils.adicionarErroSemantico(ctx.start, "Tipo utilizado mas nao declarado");
            }
        }

        return super.visitTipo_basico_ident(ctx);
    }

    // @Override
    // public Void visitTipo(TipoContext ctx) {
    // if(ctx.registro() != null) {

    // } else {
    // ctx.tipo_estendido().tipo_basico_ident()
    // }
    // return super.visitTipo(ctx);
    // }
}
