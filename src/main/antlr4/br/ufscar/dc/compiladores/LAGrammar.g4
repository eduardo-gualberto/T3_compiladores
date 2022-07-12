// grammar LAGrammar;

// PALAVRA_CHAVE:
// 	'algoritmo'
// 	| 'declare'
// 	| 'fim_algoritmo'
// 	| 'leia'
// 	| 'escreva'
// 	| 'se'
// 	| 'entao'
// 	| 'senao'
// 	| 'fim_se'
// 	| 'caso'
// 	| 'seja'
// 	| 'fim_caso'
// 	| 'para'
// 	| 'fim_para'
// 	| 'ate'
// 	| 'faca'
// 	| 'enquanto'
// 	| 'fim_enquanto'
// 	| 'registro'
// 	| 'fim_registro'
// 	| 'tipo'
// 	| 'procedimento'
// 	| 'fim_procedimento'
// 	| 'var'
// 	| 'funcao'
// 	| 'fim_funcao'
// 	| 'retorne'
// 	| 'constante'
// 	| 'nao';
// // sinais de pontuaçao utilizados
// PUNCTATION: ',' | '.';
// // operador especial de intervalo entre numeros
// INTERVALO: '..';
// // numeros inteiros
// NUM_INT: ('0' ..'9')+;
// // numeros em ponto flutuante
// NUM_REAL: ('0' ..'9')+ ('.' ('0' ..'9')+)?;
// // tipos booleanos
// BOOLEANO: 'verdadeiro' | 'falso';
// // identificadores, como as variaveis devem ser nomeadas
// IDENT: ('a' ..'z' | 'A' ..'Z') (
// 		'a' ..'z'
// 		| 'A' ..'Z'
// 		| '0' ..'9'
// 		| '_'
// 	)*;
// // como as cadeias de caracteres devem ser construidas
// CADEIA: '"' ( ESC_SEQ | ~('"' | '\\' | '\n' | '\r'))* '"';

// fragment ESC_SEQ: '\\\'';

// // como os comentarios devem ser construidos
// COMENTARIO: '{' ~('}' | '\n' | '\r')* '}' {skip();};

// // white spaces
// WS: ( ' ' | '\t' | '\r' | '\n') {skip();};

// // operador de atribuição
// ATRIBUICAO: '<-';

// // simbolos que fazem referencia a endereços de memoria
// POINTER: '^' | '&';

// OP_REL: '>' | '>=' | '<' | '<=' | '<>' | '=';
// OP_ARIT: OP1 | OP2 | OP3;
// OP1: '+' | '-';
// OP2: '*' | '/';
// OP3: '%';

// // delimitador da linguagem
// DELIM: ':';

// ABREPAR: '(';
// FECHAPAR: ')';
// ABRECOLCH: '[';
// FECHACOLCH: ']';

// // regras utilizadas para se identificar os erros especificados
// // cadeia nao fechada na mesma linha que foi aberta
// ERROR_CADEIA:
// 	'"' (ESC_SEQ | ~('"' | '\\' | '\n' | '\r'))* ('\n' | '\r');
// // comentario nao fechado na mesma linha que foi aberto
// ERROR_COMENTARIO: '{' ~('}' | '\n' | '\r')* ('\n' | '\r');
// // simboloss não-reconhecidos
// UNKNOWN: .;

// //AQUI COMEÇA A IMPLEMENTAÇÃO DO TRABALHO 2

// programa: delcaracoes? 'algoritmo' corpo 'fim_agoritmo';
// delcaracoes: decl_local_global+;
// decl_local_global: decl_local | decl_global;
// decl_local:
// 	'declare' variavel
// 	| 'contante' IDENT ':' tipo_basico '=' valor_constante
// 	| 'tipo' IDENT ':' tipo;
// variavel: identificador (',' identificador)* ':' tipo;
// identificador: IDENT ('.' IDENT)* dimensao?;
// dimensao: ('[' exp_artimetica ']')+;
// tipo: registro | tipo_estendido;
// tipo_basico: 'literal' | 'inteiro' | 'real' | 'logico';
// tipo_basico_ident: tipo_basico | IDENT;
// tipo_estendido: '^'? tipo_basico_ident;
// valor_constante: CADEIA | NUM_INT | NUM_REAL | BOOLEANO;
// registro: 'registro' variavel* 'fim_registro';
// decl_global:
// 	'procedimento' IDENT '(' paramentros? ')' decl_local* cmd* 'fim_procedimento'
// 	| 'funcao' IDENT '(' paramentros? ')' ':' tipo_estendido decl_local* cmd* 'fim_funcao';
// paramentro:
// 	'var'? identificador (',' identificador)* ':' tipo_estendido;
// paramentros: paramentro (',' paramentro)*;
// corpo: decl_local+ cmd+;
// cmd:
// 	cmd_leia
// 	| cmd_escreva
// 	| cmd_se
// 	| cmd_caso
// 	| cmd_para
// 	| cmd_enquanto
// 	| cmd_faca
// 	| cmd_atribuicao
// 	| cmd_chamada
// 	| cmd_retorne;
// cmd_leia:
// 	'leia' '(' '^'? identificador (',' '^'? identificador)* ')';
// cmd_escreva: 'escreva' '(' expressao (',' expressao)* ')';
// cmd_se: 'se' expressao 'entao' cmd* ('senao' cmd*)? 'fim_se';
// cmd_caso:
// 	'caso' exp_artimetica 'seja' selecao ('senao' cmd*)? 'fim_caso';
// cmd_para:
// 	'para' IDENT '<-' exp_artimetica 'ate' exp_artimetica 'faca' cmd* 'fim_para';
// cmd_enquanto: 'enquanto' expressao 'faca' cmd* 'fim_enquanto';
// cmd_faca: 'faca' cmd* 'ate' expressao;
// cmd_atribuicao: '^'? identificador '<-' expressao;
// cmd_chamada: IDENT '(' expressao (',' expressao)* ')';
// cmd_retorne: 'retorne' expressao;
// selecao: item_selecao*;
// item_selecao: constantes ':' cmd*;
// constantes: num_intervalo (',' num_intervalo)*;
// num_intervalo: op_unario? NUM_INT ('..' op_unario? NUM_INT)?;
// op_unario: '-';
// exp_artimetica: termo (OP1 termo)*;
// termo: fator (OP2 fator)*;
// fator: parcela (OP3 parcela)*;
// parcela: op_unario? parcela_unario | parcela_nao_unario;
// parcela_unario:
// 	'^'? identificador
// 	| IDENT '(' expressao (',' expressao)* ')'
// 	| NUM_INT
// 	| NUM_REAL
// 	| '(' expressao ')';
// parcela_nao_unario: '&' identificador | CADEIA;
// exp_relacional: exp_artimetica (OP_REL exp_artimetica)?;
// expressao: termo_logico (op_logico1 termo_logico)*;
// termo_logico: fator_logico (op_logico2 fator_logico)*;
// fator_logico: 'nao'? parcela_logica;
// parcela_logica: BOOLEANO | exp_relacional;
// op_logico1: 'ou';
// op_logico2: 'e';

grammar LAGrammar;

// Palavras chaves
PALAVRA_CHAVE 			: 'algoritmo' | 'fim_algoritmo' | 'declare' | 'var' | 'tipo' | 'literal' 
						| 'inteiro' | 'real' | 'logico' | 'e' | 'ou' | 'verdadeiro' | 'falso' | 'constante' 
						| 'leia' | 'escreva'  | 'nao' | 'se' | 'entao' | 'senao' | 'fim_se' | 'caso' | 'fim_caso' 
						| 'para' | 'fim_para' | 'enquanto' | 'fim_enquanto' | 'faca' | 'seja' | 'ate' 
						| 'registro' | 'fim_registro' | 'procedimento' | 'fim_procedimento' | 'retorne' 
						| 'funcao' | 'fim_funcao' | '<-' | '->';

// Números inteiros e reais
NUM_INT 				: ('0'..'9')+;
NUM_REAL				: ('0'..'9')+ ('.' ('0'..'9')+)?;

// Identificador
IDENT					: ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// Cadeia de caracteres
//considera como a sequência de escape ou qualquer coisa menos aspas, barras e quebra de linha
CADEIA 					: '"' ( ESC_SEQ | ~('"'|'\\'|'\n') )* '"';
//regra auxiliar para permitir \'' em cadeias
fragment ESC_SEQ : '\\\'';

// Ignorando comentários
//dentro do comentário, são proibidos \n, \r e }. 
//O fechamento de chaves é proíbido para que não sejam aceitos comentários com o final }},
//visto que o algorítimo utiliza a estratégia greedy e aceitaria o lexema considerando } como parte do comentário
COMENTARIO 				: '{' ~('\n'|'\r'|'}')* '}' {skip();};

// Ignorando espaços, tabulação, retorno e quebra de linha
WS  					:   ( ' ' | '\t' | '\r' | '\n') {skip();};

// Operadores relacionais e aritméticos
OP_RELACIONAIS 			: '<>' | '<=' | '>=' | '<' | '>' | '=';
OP_ARITMETICOS 			: '+' | '-' | '*' | '/' | '%' | '^';

// Caracteres especiais
ABREPAR 				:	'(';
FECHAPAR				:	')';
ABRECOL 				:	'[';
FECHACOL				:	']';
DOT						:	'.';
DOTS					:	'..';
COMMA					:	',';
DELIM					:	':';
ENDERECO				:	'&';

// Erros

//Caso seja aberto um comentário, e venham sendo recbidos caracteres sem que haja fechamento das chaves, retorna erro de comentario não fechado
COMENTARIO_N_FECHADO 	: '{'.*?~('}');
//Caso seja aberta uma cadeia, e venham sendo recbidos caracteres sem que haja fechamento das das aspas, retorna erro de cadeia não fechada
CADEIA_N_FECHADA		: '"'.*?~('"');
//Caso não se tenha encontrado nenhuma correspondência entre o lexema e as regras definidas,o lexema é considerado como um símbolo desconhecido
SIMBOLO_DESCONHECIDO	: .;

programa
	:	declaracoes 'algoritmo' corpo 'fim_algoritmo'
	;
	
declaracoes
	:	decl_local_global*
	;
	
decl_local_global
	:	declaracao_local | declaracao_global
	;

declaracao_local
	:	'declare' variavel
	|	'constante' IDENT ':' tipo_basico '=' valor_constante
	|	'tipo' IDENT ':' tipo
	;
	
variavel
	:	identificador (',' identificador)* ':' tipo
	;

identificador
	:	IDENT ('.' IDENT)* dimensao
	;

dimensao
	:	('[' exp_aritmetica ']')*
	;

tipo
	:	registro | tipo_estendido
	;

tipo_basico
	:	'literal' | 'inteiro' | 'real' | 'logico'
	;

tipo_basico_ident
	:	tipo_basico | IDENT
	;

tipo_estendido
	:	('^')? tipo_basico_ident
	;

valor_constante
	:	CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso'
	;

registro
	:	'registro' (variavel)* 'fim_registro'
	;

declaracao_global
	:	'procedimento' IDENT '(' (parametros)? ')' (declaracao_local)* (cmd)* 'fim_procedimento'
	|	'funcao' IDENT '(' (parametros)? ')' ':' tipo_estendido (declaracao_local)* (cmd)* 'fim_funcao'
	;

parametro
	:	('var')? identificador (',' identificador)* ':' tipo_estendido
	;

parametros
	:	parametro (',' parametro)*
	;

corpo
	: (declaracao_local)* (cmd)*
	;
cmd
	: cmdLeia|cmdEscreva|cmdSe|cmdCaso|cmdPara|cmdEnquanto|cmdFaca|cmdAtribuicao|cmdChamada|cmdRetorne
	;
cmdLeia
	: 'leia' '(' ('^')? identificador (',' '^'? identificador)* ')'
	;
cmdEscreva
	: 'escreva' '(' expressao (',' expressao)*')'
	;
cmdSe
	: 'se' expressao 'entao' cmd* ('senao' cmd*)? 'fim_se'
	;
cmdCaso
	: 'caso' exp_aritmetica 'seja' selecao ('senao' cmd*)? 'fim_caso'
	;
cmdPara
	: 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' cmd* 'fim_para'
	;
cmdEnquanto
	: 'enquanto' expressao 'faca' cmd* 'fim_enquanto'
	;
cmdFaca
	: 'faca' cmd* 'ate' expressao
	;
cmdAtribuicao
	: '^'? identificador '<-' expressao
	;
cmdChamada
	: IDENT '(' expressao (',' expressao)* ')'
	;
cmdRetorne
	: 'retorne' expressao
	;
selecao
	: item_selecao*
	;
item_selecao
	: constantes ':' (cmd)*
	;
constantes
	: numero_intervalo (',' numero_intervalo)*
	;
numero_intervalo
	: op_unario? NUM_INT ('..' op_unario? NUM_INT)?
	;
op_unario
	: '-'
	;
exp_aritmetica
	: termo (op1 termo)*
	;
termo
	: fator (op2 fator)*
	;
fator
	: parcela (op3 parcela)*
	;
op1
	: '+'| '-'
	;
op2
	: '*'|'/'
	;
op3
	: '%'
	;
parcela
	: op_unario? (parcela_unario) | parcela_nao_unario
	;
parcela_unario
	: '^'? identificador
	|IDENT '(' expressao (',' expressao)* ')'
	|NUM_INT
	|NUM_REAL
	|'(' expressao ')'
	;
parcela_nao_unario
	: '&' identificador | CADEIA
	;
exp_relacional
	: exp_aritmetica (op_relacional exp_aritmetica)?
	;
op_relacional
	: '='|'<>'|'>='|'<='|'>'|'<'
	;
expressao
	: termo_logico (op_logico_1 termo_logico)*
	;
termo_logico
	: fator_logico (op_logico_2 fator_logico)*
	;
fator_logico
	: 'nao'? parcela_logica
	;
parcela_logica
	: ('verdadeiro'|'falso') | exp_relacional
	;
op_logico_1
	: 'ou'
	;
op_logico_2
	: 'e'
	;