package br.ufscar.dc.compiladores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class Main {
    public static void main(String[] args) {
        // Try-Catch que deve funcionar apenas se os arquivos de entrada existirem
        try (PrintWriter pw = new PrintWriter(new FileWriter(args[1]))) {
            CharStream cs = CharStreams.fromFileName(args[0]);
            // Instancia do Lexer que recebe o arquivo 'args[0]' como input-stream
            MyLexer lex = new MyLexer(cs);

            // Loop que coleta cada um dos tokens gerados pelo Lexer, até seja coletado um
            // token do tipo 'End Of File'
            Token t = null;
            while ((t = lex.nextToken()).getType() != Token.EOF) {
                // recupera o tipo do token, seu padrao
                String tokenType = MyLexer.VOCABULARY.getDisplayName(t.getType());
                // recupera a linha referente a input-stream na qual o Lexer atualmente se
                // encontra processando
                int currLine = lex.getLine();

                switch (tokenType) {
                    // Os tokens pertencentes aos Padrões abaixo são enviados ao 'handler' de erros
                    case "UNKNOWN":
                    case "ERROR_COMENTARIO":
                    case "ERROR_CADEIA":
                        handleLexicalError(tokenType, t, currLine, pw);
                        return;
                    // Os tokens pertencentes aos Padrẽes abaixo apresentam uma representação
                    // especial
                    case "IDENT":
                    case "CADEIA":
                    case "NUM_REAL":
                    case "NUM_INT":
                        printSpecialToken(tokenType, t, pw);
                        break;
                    // Todos os demais Padrões de token são representados de forma genérica
                    default:
                        printGenericToken(t, pw);
                }
            }
        } catch (IOException ex) {
        }
    }

    // 'handler' responsável por processar qualquer erro léxico
    public static void handleLexicalError(String tokenType, Token t, int currLine, PrintWriter pw) {
        // Segrega o processamento do token de acordo com o Padrão a que pertence
        switch (tokenType) {
            case "UNKNOWN":
                handleUnknownTokenError(t, currLine, pw);
                break;
            case "ERROR_COMENTARIO":
                // currLine - 1, por que o Lexer so reconhece esse caso uma linha após sua
                // ocorrencia
                handleCommentError(t, currLine - 1, pw);
                break;
            case "ERROR_CADEIA":
                // currLine - 1, por que o Lexer so reconhece esse caso uma linha após sua
                // ocorrencia
                handleStringError(t, currLine - 1, pw);
        }
    }

    public static void handleUnknownTokenError(Token t, int currLine, PrintWriter pw) {
        // Mensagem de erro padrão para simbolos não reconhecidos
        pw.println("Linha " + currLine + ": " + t.getText() + " - simbolo nao identificado");
    }

    public static void handleCommentError(Token t, int currLine, PrintWriter pw) {
        // Mensagem de erro padrão para blocos de comentário não fechados na mesma linha
        // que foram abertos
        pw.println("Linha " + currLine + ": comentario nao fechado");
    }

    public static void handleStringError(Token t, int currLine, PrintWriter pw) {
        // Mensagem de erro padrão para declarações literais de cadeias que não fechadas
        // na mesma linha que foram abertas
        pw.println("Linha " + currLine + ": cadeia literal nao fechada");
    }

    public static void printSpecialToken(String tokenType, Token t, PrintWriter pw) {
        // Formatação especial: <LEXEMA, PADRAO>
        pw.println("<\'" + t.getText() + "\'," + tokenType + ">");
    }

    public static void printGenericToken(Token t, PrintWriter pw) {
        // Formatação especial: <LEXEMA, LEXEMA>
        pw.println("<\'" + t.getText() + "\',\'" + t.getText() + "\'>");
    }
}
