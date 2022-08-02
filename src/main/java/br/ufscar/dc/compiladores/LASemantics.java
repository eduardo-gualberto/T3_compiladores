package br.ufscar.dc.compiladores;

import java.util.ArrayList;

import br.ufscar.dc.compiladores.LAGrammarParser.CmdAtribuicaoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Declaracao_globalContext;
import br.ufscar.dc.compiladores.LAGrammarParser.ExpressaoContext;
import br.ufscar.dc.compiladores.LAGrammarParser.IdentificadorContext;
import br.ufscar.dc.compiladores.LAGrammarParser.ProgramaContext;
import br.ufscar.dc.compiladores.LAGrammarParser.Tipo_basico_identContext;
import br.ufscar.dc.compiladores.LAGrammarParser.VariavelContext;
import br.ufscar.dc.compiladores.SymbolTableInput.LATypes;

public class LASemantics extends LAGrammarBaseVisitor<Void> {
    SymbolTable symbolTable = new SymbolTable();

    // Class entrypoint
    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitCmdAtribuicao(CmdAtribuicaoContext ctx) {
        // TODO Auto-generated method stub
        LATypes identType = LASemanticsUtils.verificarTipo(symbolTable, ctx.identificador().getText());
        LATypes expType = LASemanticsUtils.verificarTipo(symbolTable, ctx);

        if (identType != expType || expType == null)
            LASemanticsUtils.adicionarErroSemantico(ctx.identificador().start,
                    "atribuicao nao compativel para " + ctx.identificador().getText());

        return super.visitCmdAtribuicao(ctx);
    }

    // @Override
    // public Void visitDeclaracao_global(Declaracao_globalContext ctx) {
    // // TODO Auto-generated method stub

    // String varName = ctx.IDENT().getText();

    // if (symbolTable.contains(varName)) {
    // LASemanticsUtils.adicionarErroSemantico(ctx.start, "Funcao ou procedimento ja
    // declarada.");
    // } else {
    // symbolTable.insert(varName, LATypes.IDENT);
    // }

    // return super.visitDeclaracao_global(ctx);
    // }

    @Override
    public Void visitIdentificador(IdentificadorContext ctx) {
        // TODO Auto-generated method stub

        for (var ident : ctx.IDENT()) {
            if (!symbolTable.contains(ident.getText())) {
                LASemanticsUtils.adicionarErroSemantico(ident.getSymbol(),
                        "identificador " + ident.getText() + " nao declarado");
            }
        }

        return super.visitIdentificador(ctx);
    }

    @Override
    public Void visitVariavel(VariavelContext ctx) {
        // TODO Auto-generated method stub
        LATypes type = LASemanticsUtils.verificarTipo(symbolTable, ctx.tipo());
        for (var ident : ctx.identificador()) {
            if (symbolTable.contains(ident.getText()))
                LASemanticsUtils.adicionarErroSemantico(ident.start,
                        "identificador " + ident.getText() + " ja declarado anteriormente");
            else
                symbolTable.insert(ident.getText(), type);
        }

        return super.visitVariavel(ctx);
    }

    @Override
    public Void visitTipo_basico_ident(Tipo_basico_identContext ctx) {
        // TODO Auto-generated method stub

        if (ctx.IDENT() != null && !symbolTable.contains(ctx.IDENT().getText())) {
            String type_str = ctx.IDENT().getText();
            LASemanticsUtils.adicionarErroSemantico(ctx.start, "tipo " + type_str + " nao declarado");
        }

        if (ctx.tipo_basico() != null) {
            String type_str = ctx.tipo_basico().getText();
            ArrayList<String> tipos_basicos = new ArrayList<String>();
            tipos_basicos.add("inteiro");
            tipos_basicos.add("real");
            tipos_basicos.add("logico");
            tipos_basicos.add("literal");

            if (!tipos_basicos.contains(type_str)) {
                LASemanticsUtils.adicionarErroSemantico(ctx.start, "tipo " + type_str + " nao declarado");
            }
        }

        return super.visitTipo_basico_ident(ctx);
    }
}
