package br.ufscar.dc.compiladores;

import java.util.Queue;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

public class LALexer extends LAGrammarLexer {
    
    private Queue<Token> queue = new java.util.LinkedList<Token>();
    
    public LALexer(CharStream input) {
        super(input);
    }

    @Override
    public Token nextToken() {

        if (!queue.isEmpty()) {
            return queue.poll();
        }

        Token next = super.nextToken();

        // Caso o token nao pertença ao Padrão UNKNOWN, o método atua como o da
        // classe-pai
        if (next.getType() != SIMBOLO_DESCONHECIDO) {
            return next;
        }

        // Variavel para construir uma representação de cadeia para o simbolo
        // não-reconhecido
        StringBuilder builder = new StringBuilder();
        // Guarda a linha na qual primeiro ocorre um simbolo não-reconhecido
        int initialLine = getLine();

        // Enquanto houverem tokens não-reconhecidos em sequência, consumi-los e
        // adicionar seus lexemas
        // ao 'stringBuilder'
        while (next.getType() == SIMBOLO_DESCONHECIDO) {
            builder.append(next.getText());
            next = super.nextToken();
        }

        // Retorna o contador de linha para a posição do primeiro token não-reconhecido
        // consumido
        setLine(initialLine);

        queue.offer(next);

        return new CommonToken(SIMBOLO_DESCONHECIDO, builder.toString());
    }
}
