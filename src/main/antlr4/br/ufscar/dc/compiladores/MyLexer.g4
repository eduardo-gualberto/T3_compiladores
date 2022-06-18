lexer grammar MyLexer;

// O bloco de código Java abaixo é introduzido a classe MyLexer, gerada pelo Antlr4
@members {
  private java.util.Queue<Token> queue = new java.util.LinkedList<Token>();

  // É feito a sobrescrita do método nextToken() com o intuito de processar 
  // os simbolos não reconhecidos da input-stream
  @Override
  public Token nextToken() {    

    if(!queue.isEmpty()) {
      return queue.poll();
    }

    Token next = super.nextToken();

	// Caso o token nao pertença ao Padrão UNKNOWN, o método atua como o da classe-pai
    if(next.getType() != UNKNOWN) {
      return next;
    }

	// Variavel para construir uma representação de cadeia para o simbolo não-reconhecido
    StringBuilder builder = new StringBuilder();
    // Guarda a linha na qual primeiro ocorre um simbolo não-reconhecido
	int initialLine = getLine();

	// Enquanto houverem tokens não-reconhecidos em sequência, consumi-los e adicionar seus lexemas
	// ao 'stringBuilder'
    while(next.getType() == UNKNOWN) {
      builder.append(next.getText());
      next = super.nextToken();
    }

	// Retorna o contador de linha para a posição do primeiro token não-reconhecido consumido
    setLine(initialLine);

    queue.offer(next);

    return new CommonToken(UNKNOWN, builder.toString());
  }
}
// palavras chave da linguagem
PALAVRA_CHAVE:
	'algoritmo'
	| 'declare'
	| 'fim_algoritmo'
	| 'leia'
	| 'escreva'
	| 'literal'
	| 'inteiro'
	| 'real'
	| 'logico'
	| 'e'
	| 'ou'
	| 'se'
	| 'entao'
	| 'senao'
	| 'fim_se'
	| 'caso'
	| 'seja'
	| 'fim_caso'
	| 'para'
	| 'fim_para'
	| 'ate'
	| 'faca'
	| 'enquanto'
	| 'fim_enquanto'
	| 'registro'
	| 'fim_registro'
	| 'tipo'
	| 'procedimento'
	| 'fim_procedimento'
	| 'var'
	| 'funcao'
	| 'fim_funcao'
	| 'retorne'
	| 'constante'
	| 'falso'
	| 'nao'
	| 'verdadeiro';
// sinais de pontuaçao utilizados
PUNCTATION: ',' | '.';
// operador especial de intervalo entre numeros
INTERVALO: '..';
// numeros inteiros
NUM_INT: ('0' ..'9')+;
// numeros em ponto flutuante
NUM_REAL: ('0' ..'9')+ ('.' ('0' ..'9')+)?;
// identificadores, como as variaveis devem ser nomeadas
IDENT: ('a' ..'z' | 'A' ..'Z') (
		'a' ..'z'
		| 'A' ..'Z'
		| '0' ..'9'
		| '_'
	)*;
// como as cadeias de caracteres devem ser construidas
CADEIA: '"' ( ESC_SEQ | ~('"' | '\\' | '\n' | '\r'))* '"';

fragment ESC_SEQ: '\\\'';

// como os comentarios devem ser construidos
COMENTARIO: '{' ~('}' | '\n' | '\r')* '}' {skip();};

// white spaces
WS: ( ' ' | '\t' | '\r' | '\n') {skip();};

// operador de atribuição
ATRIBUICAO: '<-';

// simbolos que fazem referencia a endereços de memoria
POINTER: '^' | '&';

OP_REL: '>' | '>=' | '<' | '<=' | '<>' | '=';
OP_ARIT: '+' | '-' | '*' | '/' | '%';

// delimitador da linguagem
DELIM: ':';

ABREPAR: '(';
FECHAPAR: ')';
ABRECOLCH: '[';
FECHACOLCH: ']';

// regras utilizadas para se identificar os erros especificados
// cadeia nao fechada na mesma linha que foi aberta
ERROR_CADEIA:
	'"' (ESC_SEQ | ~('"' | '\\' | '\n' | '\r'))* ('\n' | '\r');
// comentario nao fechado na mesma linha que foi aberto
ERROR_COMENTARIO: '{' ~('}' | '\n' | '\r')* ('\n' | '\r');
// simboloss não-reconhecidos
UNKNOWN: .;