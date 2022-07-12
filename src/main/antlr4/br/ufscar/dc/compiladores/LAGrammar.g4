grammar LAGrammar;

// Regras léxicas

// Palavras chaves diversas da linguagem, reconhecidas através dos casos de uso
PALAVRA_CHAVE:
	'algoritmo'
	| 'fim_algoritmo'
	| 'declare'
	| 'var'
	| 'tipo'
	| 'literal'
	| 'inteiro'
	| 'real'
	| 'logico'
	| 'e'
	| 'ou'
	| 'verdadeiro'
	| 'falso'
	| 'constante'
	| 'leia'
	| 'escreva'
	| 'nao'
	| 'se'
	| 'entao'
	| 'senao'
	| 'fim_se'
	| 'caso'
	| 'fim_caso'
	| 'para'
	| 'fim_para'
	| 'enquanto'
	| 'fim_enquanto'
	| 'faca'
	| 'seja'
	| 'ate'
	| 'registro'
	| 'fim_registro'
	| 'procedimento'
	| 'fim_procedimento'
	| 'retorne'
	| 'funcao'
	| 'fim_funcao'
	| '<-'
	| '->';

// Números inteiros e reais de acordo com a especificação da linguagem
NUM_INT: ('0' ..'9')+;
NUM_REAL: ('0' ..'9')+ ('.' ('0' ..'9')+)?;

// Identificadores, como as variaveis devem ser nomeadas
IDENT: ('a' ..'z' | 'A' ..'Z') (
		'a' ..'z'
		| 'A' ..'Z'
		| '0' ..'9'
		| '_'
	)*;

// Como as 'strings' devem ser formatadas, não aceitam quebra de linha, back-slash ou aspas duplas
CADEIA: '"' ( ESC_SEQ | ~('"' | '\\' | '\n'))* '"';
//regra auxiliar para permitir \'' em cadeias
fragment ESC_SEQ: '\\\'';

// Padrão de comentários da linguagem, devem ser delimitados entre chaves não aceitam: quebra de
// linha ou o uso do símbolo '}'
COMENTARIO: '{' ~('\n' | '\r' | '}')* '}' {skip();};

// Espaços em branco, devem ser ignorados pelos analisadores
WS: ( ' ' | '\t' | '\r' | '\n') {skip();};

// Operadores relacionais e aritméticos de acordo com a especificação da linguagem
OP_RELACIONAIS: '<>' | '<=' | '>=' | '<' | '>' | '=';
OP_ARITMETICOS: '+' | '-' | '*' | '/' | '%' | '^';

// Caracteres especiais da linguagem
ABREPAR: '(';
FECHAPAR: ')';
ABRECOL: '[';
FECHACOL: ']';
DOT: '.';
DOTS: '..';
COMMA: ',';
DELIM: ':';
ENDERECO: '&';

// Grupo de regras para identificarem erros léxicos Comentário não fechado
ERROR_COMENTARIO: '{' .*? ~('}');
// Cadeia não fechada
ERROR_CADEIA: '"' .*? ~('"');
// Regra fallback: caso algum token não se encaixe em nenhuma regra acima será encaixado nessa e
// tratado como não-reconhecido
UNKNOWN: .;

// Regras sintáticas Todas as regras abaixo estão de acordo com o modelo fornecido pelo professor
// são apenas traduções da linguagem formal utilizada pelo professor, para a linguagem utilizada
// pelo Antlr

programa: declaracoes 'algoritmo' corpo 'fim_algoritmo';

declaracoes: decl_local_global*;

decl_local_global: declaracao_local | declaracao_global;

declaracao_local:
	'declare' variavel
	| 'constante' IDENT ':' tipo_basico '=' valor_constante
	| 'tipo' IDENT ':' tipo;

variavel: identificador (',' identificador)* ':' tipo;

identificador: IDENT ('.' IDENT)* dimensao;

dimensao: ('[' exp_aritmetica ']')*;

tipo: registro | tipo_estendido;

tipo_basico: 'literal' | 'inteiro' | 'real' | 'logico';

tipo_basico_ident: tipo_basico | IDENT;

tipo_estendido: ('^')? tipo_basico_ident;

valor_constante:
	CADEIA
	| NUM_INT
	| NUM_REAL
	| 'verdadeiro'
	| 'falso';

registro: 'registro' (variavel)* 'fim_registro';

declaracao_global:
	'procedimento' IDENT '(' (parametros)? ')' (declaracao_local)* (
		cmd
	)* 'fim_procedimento'
	| 'funcao' IDENT '(' (parametros)? ')' ':' tipo_estendido (
		declaracao_local
	)* (cmd)* 'fim_funcao';

parametro: ('var')? identificador (',' identificador)* ':' tipo_estendido;

parametros: parametro (',' parametro)*;

corpo: (declaracao_local)* (cmd)*;
cmd:
	cmdLeia
	| cmdEscreva
	| cmdSe
	| cmdCaso
	| cmdPara
	| cmdEnquanto
	| cmdFaca
	| cmdAtribuicao
	| cmdChamada
	| cmdRetorne;
cmdLeia:
	'leia' '(' ('^')? identificador (',' '^'? identificador)* ')';
cmdEscreva: 'escreva' '(' expressao (',' expressao)* ')';
cmdSe: 'se' expressao 'entao' cmd* ('senao' cmd*)? 'fim_se';
cmdCaso:
	'caso' exp_aritmetica 'seja' selecao ('senao' cmd*)? 'fim_caso';
cmdPara:
	'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' cmd* 'fim_para';
cmdEnquanto: 'enquanto' expressao 'faca' cmd* 'fim_enquanto';
cmdFaca: 'faca' cmd* 'ate' expressao;
cmdAtribuicao: '^'? identificador '<-' expressao;
cmdChamada: IDENT '(' expressao (',' expressao)* ')';
cmdRetorne: 'retorne' expressao;
selecao: item_selecao*;
item_selecao: constantes ':' (cmd)*;
constantes: numero_intervalo (',' numero_intervalo)*;
numero_intervalo: op_unario? NUM_INT ('..' op_unario? NUM_INT)?;
op_unario: '-';
exp_aritmetica: termo (op1 termo)*;
termo: fator (op2 fator)*;
fator: parcela (op3 parcela)*;
op1: '+' | '-';
op2: '*' | '/';
op3: '%';
parcela: op_unario? (parcela_unario) | parcela_nao_unario;
parcela_unario:
	'^'? identificador
	| IDENT '(' expressao (',' expressao)* ')'
	| NUM_INT
	| NUM_REAL
	| '(' expressao ')';
parcela_nao_unario: '&' identificador | CADEIA;
exp_relacional: exp_aritmetica (op_relacional exp_aritmetica)?;
op_relacional: '=' | '<>' | '>=' | '<=' | '>' | '<';
expressao: termo_logico (op_logico_1 termo_logico)*;
termo_logico: fator_logico (op_logico_2 fator_logico)*;
fator_logico: 'nao'? parcela_logica;
parcela_logica: ('verdadeiro' | 'falso') | exp_relacional;
op_logico_1: 'ou';
op_logico_2: 'e';