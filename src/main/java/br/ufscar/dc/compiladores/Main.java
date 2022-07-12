package br.ufscar.dc.compiladores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Main {

    private static String error = "";
    private static String result = "";

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
                    case "SIMBOLO_DESCONHECIDO":
                    case "COMENTARIO_N_FECHADO":
                    case "CADEIA_N_FECHADA":
                        System.out.println("Error!");
                        handleLexicalError(tokenType, t, currLine);
                        break;
                    default:
                        handleGenericToken(t);
                }
            }

            // Analise sistática
            if (error.isEmpty()) {
                lex.reset();
                CommonTokenStream tokens = new CommonTokenStream(lex);
                LAGrammarParser parser = new LAGrammarParser(tokens);

                LACustomErrorMessage errorListener = new LACustomErrorMessage(pw);
                parser.removeErrorListeners();
                parser.addErrorListener(errorListener);
                parser.programa();
            } else {
                pw.write(error);
                pw.write("Fim da compilacao\n");
                pw.close();
            }
            pw.write(result);
            pw.close();
        } catch (IOException ex) {
        }
    }

    //
    public static void handleGenericToken(Token t) {
        String token = t.getText();
        String regra = LAGrammarLexer.VOCABULARY.getDisplayName(t.getType());
        result += ("<\'" + token + "\'," + regra + ">\n");
    }

    // 'handler' responsável por processar qualquer erro léxico
    public static void handleLexicalError(String tokenType, Token t, int currLine) {
        // Segrega o processamento do token de acordo com o Padrão a que pertence
        switch (tokenType) {
            case "SIMBOLO_DESCONHECIDO":
                handleUnknownTokenError(t, currLine);
                break;
            case "COMENTARIO_N_FECHADO":
                // currLine - 1, por que o Lexer so reconhece esse caso uma linha após sua
                // ocorrencia
                handleCommentError(t, currLine);
                break;
            case "CADEIA_N_FECHADA":
                // currLine - 1, por que o Lexer so reconhece esse caso uma linha após sua
                // ocorrencia
                handleStringError(t, currLine);
        }
    }

    public static void handleUnknownTokenError(Token t, int currLine) {
        // Mensagem de erro padrão para simbolos não reconhecidos
        error = "Linha " + currLine + ": " + t.getText() + " - simbolo nao identificado\n";
        System.out.println(error);
        result += error;
    }

    public static void handleCommentError(Token t, int currLine) {
        // Mensagem de erro padrão para blocos de comentário não fechados na mesma linha
        // que foram abertos
        error = "Linha " + currLine + ": comentario nao fechado\n";
        System.out.println(error);
        result += error;
    }

    public static void handleStringError(Token t, int currLine) {
        // Mensagem de erro padrão para declarações literais de cadeias que não fechadas
        // na mesma linha que foram abertas
        error = "Linha " + currLine + ": cadeia literal nao fechada\n";
        System.out.println(error);
        result += error;
    }
}
