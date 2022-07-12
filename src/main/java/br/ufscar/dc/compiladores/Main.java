package br.ufscar.dc.compiladores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Main {

    private static String lexicalError = "";
    private static String generatedOutput = "";

    public static void main(String[] args) {
        // Try-Catch que deve funcionar apenas se os arquivos de entrada existirem
        try (PrintWriter pw = new PrintWriter(new FileWriter(args[1]))) {
            CharStream cs = CharStreams.fromFileName(args[0]);
            // Instancia do Lexer que recebe o arquivo 'args[0]' como input-stream
            LALexer lex = new LALexer(cs);

            // Loop que coleta cada um dos tokens gerados pelo Lexer, até seja coletado um
            // token do tipo 'End Of File'
            Token t = null;


            // Analise lexica
            while ((t = lex.nextToken()).getType() != Token.EOF) {
                // recupera o tipo do token, seu padrao
                String tokenType = LALexer.VOCABULARY.getDisplayName(t.getType());
                // recupera a linha referente a input-stream na qual o Lexer atualmente se
                // encontra processando
                int currLine = lex.getLine();

                switch (tokenType) {
                    // Os tokens pertencentes aos Padrões abaixo são enviados ao 'handler' de erros
                    case "UNKNOWN":
                    case "ERROR_COMENTARIO":
                    case "ERROR_CADEIA":
                        handleLexicalError(tokenType, t, currLine);
                        break;
                    default:
                        handleGenericToken(t);
                }
            }

            // Analise sistática

            // Caso não haja nenhum erro léxico reportado
            if (lexicalError.isEmpty()) {
                // Reseta o Lexer: ele volta ao inicio do fluxo de tokens
                lex.reset();
                CommonTokenStream tokens = new CommonTokenStream(lex);
                LAGrammarParser parser = new LAGrammarParser(tokens);

                // Instancia um novo listener de erros para o Parser
                LACustomErrorMessage errorListener = new LACustomErrorMessage(pw);
                
                // Remove o listener padrao do Parser e adiciona o customizado
                parser.removeErrorListeners();
                parser.addErrorListener(errorListener);
                
                // Inicia o processo de análise sintática
                parser.programa();
            } 
            // Caso existam erros léxicos
            else {
                pw.write(lexicalError);
                pw.write("Fim da compilacao\n");
                // O uso de pw.close() é intencional ao longo do programa,
                // mesmo que o formato de Try-Catch utilizado já feche o arquivo ao fim.
                // Ao tentar escrever em um arquivo já fechado
                // uma IOException é lançada e tratada pelo programa, no sentido de encerrar sua execução
                pw.close();
            }
            pw.write(generatedOutput);
            pw.close();
        } catch (IOException ex) {
            // Tratamento das IOExceptions lançadas
        }
    }

    // 'handler' responsável por gerar o output padronizado e especificado pelo documento da trabalho 2
    public static void handleGenericToken(Token t) {
        String token = t.getText();
        String regra = LAGrammarLexer.VOCABULARY.getDisplayName(t.getType());
        generatedOutput += ("<\'" + token + "\'," + regra + ">\n");
    }

    // 'handler' responsável por processar qualquer erro léxico
    public static void handleLexicalError(String tokenType, Token t, int currLine) {
        // Segrega o processamento do token de acordo com o Padrão a que pertence
        switch (tokenType) {
            case "UNKNOWN":
                handleUnknownTokenError(t, currLine);
                break;
            case "ERROR_COMENTARIO":
                handleCommentError(t, currLine);
                break;
            case "ERROR_CADEIA":
                handleStringError(t, currLine);
        }
    }

    public static void handleUnknownTokenError(Token t, int currLine) {
        // Mensagem de erro padrão para simbolos não reconhecidos
        lexicalError = "Linha " + currLine + ": " + t.getText() + " - simbolo nao identificado\n";
        generatedOutput += lexicalError;
    }

    public static void handleCommentError(Token t, int currLine) {
        // Mensagem de erro padrão para blocos de comentário não fechados na mesma linha
        // que foram abertos
        lexicalError = "Linha " + currLine + ": comentario nao fechado\n";
        generatedOutput += lexicalError;
    }

    public static void handleStringError(Token t, int currLine) {
        // Mensagem de erro padrão para declarações literais de cadeias que não fechadas
        // na mesma linha que foram abertas
        lexicalError = "Linha " + currLine + ": cadeia literal nao fechada\n";
        generatedOutput += lexicalError;
    }
}
