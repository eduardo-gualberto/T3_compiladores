// Generated from /home/eduardo/Documents/Compiladores/T2/Syntax/src/main/antlr4/br/ufscar/dc/compiladores/LA.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LA extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PALAVRA_CHAVE=1, PUNCTATION=2, INTERVALO=3, NUM_INT=4, NUM_REAL=5, BOOLEANO=6, 
		IDENT=7, CADEIA=8, COMENTARIO=9, WS=10, ATRIBUICAO=11, POINTER=12, OP_REL=13, 
		OP_ARIT=14, OP1=15, OP2=16, OP3=17, DELIM=18, ABREPAR=19, FECHAPAR=20, 
		ABRECOLCH=21, FECHACOLCH=22, ERROR_CADEIA=23, ERROR_COMENTARIO=24, UNKNOWN=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PALAVRA_CHAVE", "PUNCTATION", "INTERVALO", "NUM_INT", "NUM_REAL", "BOOLEANO", 
			"IDENT", "CADEIA", "ESC_SEQ", "COMENTARIO", "WS", "ATRIBUICAO", "POINTER", 
			"OP_REL", "OP_ARIT", "OP1", "OP2", "OP3", "DELIM", "ABREPAR", "FECHAPAR", 
			"ABRECOLCH", "FECHACOLCH", "ERROR_CADEIA", "ERROR_COMENTARIO", "UNKNOWN"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'..'", null, null, null, null, null, null, null, "'<-'", 
			null, null, null, null, null, "'%'", "':'", "'('", "')'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PALAVRA_CHAVE", "PUNCTATION", "INTERVALO", "NUM_INT", "NUM_REAL", 
			"BOOLEANO", "IDENT", "CADEIA", "COMENTARIO", "WS", "ATRIBUICAO", "POINTER", 
			"OP_REL", "OP_ARIT", "OP1", "OP2", "OP3", "DELIM", "ABREPAR", "FECHAPAR", 
			"ABRECOLCH", "FECHACOLCH", "ERROR_CADEIA", "ERROR_COMENTARIO", "UNKNOWN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private java.util.Queue<Token> queue = new java.util.LinkedList<Token>();

	    @Override
	    public Token nextToken() {

	        if (!queue.isEmpty()) {
	            return queue.poll();
	        }

	        Token next = super.nextToken();

	        // Caso o token nao pertença ao Padrão UNKNOWN, o método atua como o da
	        // classe-pai
	        if (next.getType() != UNKNOWN) {
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
	        while (next.getType() == UNKNOWN) {
	            builder.append(next.getText());
	            next = super.nextToken();
	        }

	        // Retorna o contador de linha para a posição do primeiro token não-reconhecido
	        // consumido
	        setLine(initialLine);

	        queue.offer(next);

	        return new CommonToken(UNKNOWN, builder.toString());
	    }


	public LA(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 9:
			COMENTARIO_action((RuleContext)_localctx, actionIndex);
			break;
		case 10:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void COMENTARIO_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u0187\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2\u0103\n\2\3\3\3\3\3\4\3\4\3\4\3\5\6\5\u010b\n\5\r\5\16\5\u010c\3\6"+
		"\6\6\u0110\n\6\r\6\16\6\u0111\3\6\3\6\6\6\u0116\n\6\r\6\16\6\u0117\5\6"+
		"\u011a\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7\u012b\n\7\3\b\3\b\7\b\u012f\n\b\f\b\16\b\u0132\13\b\3\t\3\t\3\t\7"+
		"\t\u0137\n\t\f\t\16\t\u013a\13\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\7\13\u0143"+
		"\n\13\f\13\16\13\u0146\13\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u015c\n\17"+
		"\3\20\3\20\3\20\5\20\u0161\n\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\7\31\u0176\n\31"+
		"\f\31\16\31\u0179\13\31\3\31\3\31\3\32\3\32\7\32\u017f\n\32\f\32\16\32"+
		"\u0182\13\32\3\32\3\32\3\33\3\33\2\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\2\25\13\27\f\31\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26"+
		"-\27/\30\61\31\63\32\65\33\3\2\f\4\2..\60\60\4\2C\\c|\6\2\62;C\\aac|\6"+
		"\2\f\f\17\17$$^^\5\2\f\f\17\17\177\177\5\2\13\f\17\17\"\"\4\2((``\4\2"+
		"--//\4\2,,\61\61\4\2\f\f\17\17\2\u01b4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\u0102"+
		"\3\2\2\2\5\u0104\3\2\2\2\7\u0106\3\2\2\2\t\u010a\3\2\2\2\13\u010f\3\2"+
		"\2\2\r\u012a\3\2\2\2\17\u012c\3\2\2\2\21\u0133\3\2\2\2\23\u013d\3\2\2"+
		"\2\25\u0140\3\2\2\2\27\u014a\3\2\2\2\31\u014d\3\2\2\2\33\u0150\3\2\2\2"+
		"\35\u015b\3\2\2\2\37\u0160\3\2\2\2!\u0162\3\2\2\2#\u0164\3\2\2\2%\u0166"+
		"\3\2\2\2\'\u0168\3\2\2\2)\u016a\3\2\2\2+\u016c\3\2\2\2-\u016e\3\2\2\2"+
		"/\u0170\3\2\2\2\61\u0172\3\2\2\2\63\u017c\3\2\2\2\65\u0185\3\2\2\2\67"+
		"8\7c\2\289\7n\2\29:\7i\2\2:;\7q\2\2;<\7t\2\2<=\7k\2\2=>\7v\2\2>?\7o\2"+
		"\2?\u0103\7q\2\2@A\7f\2\2AB\7g\2\2BC\7e\2\2CD\7n\2\2DE\7c\2\2EF\7t\2\2"+
		"F\u0103\7g\2\2GH\7h\2\2HI\7k\2\2IJ\7o\2\2JK\7a\2\2KL\7c\2\2LM\7n\2\2M"+
		"N\7i\2\2NO\7q\2\2OP\7t\2\2PQ\7k\2\2QR\7v\2\2RS\7o\2\2S\u0103\7q\2\2TU"+
		"\7n\2\2UV\7g\2\2VW\7k\2\2W\u0103\7c\2\2XY\7g\2\2YZ\7u\2\2Z[\7e\2\2[\\"+
		"\7t\2\2\\]\7g\2\2]^\7x\2\2^\u0103\7c\2\2_`\7u\2\2`\u0103\7g\2\2ab\7g\2"+
		"\2bc\7p\2\2cd\7v\2\2de\7c\2\2e\u0103\7q\2\2fg\7u\2\2gh\7g\2\2hi\7p\2\2"+
		"ij\7c\2\2j\u0103\7q\2\2kl\7h\2\2lm\7k\2\2mn\7o\2\2no\7a\2\2op\7u\2\2p"+
		"\u0103\7g\2\2qr\7e\2\2rs\7c\2\2st\7u\2\2t\u0103\7q\2\2uv\7u\2\2vw\7g\2"+
		"\2wx\7l\2\2x\u0103\7c\2\2yz\7h\2\2z{\7k\2\2{|\7o\2\2|}\7a\2\2}~\7e\2\2"+
		"~\177\7c\2\2\177\u0080\7u\2\2\u0080\u0103\7q\2\2\u0081\u0082\7r\2\2\u0082"+
		"\u0083\7c\2\2\u0083\u0084\7t\2\2\u0084\u0103\7c\2\2\u0085\u0086\7h\2\2"+
		"\u0086\u0087\7k\2\2\u0087\u0088\7o\2\2\u0088\u0089\7a\2\2\u0089\u008a"+
		"\7r\2\2\u008a\u008b\7c\2\2\u008b\u008c\7t\2\2\u008c\u0103\7c\2\2\u008d"+
		"\u008e\7c\2\2\u008e\u008f\7v\2\2\u008f\u0103\7g\2\2\u0090\u0091\7h\2\2"+
		"\u0091\u0092\7c\2\2\u0092\u0093\7e\2\2\u0093\u0103\7c\2\2\u0094\u0095"+
		"\7g\2\2\u0095\u0096\7p\2\2\u0096\u0097\7s\2\2\u0097\u0098\7w\2\2\u0098"+
		"\u0099\7c\2\2\u0099\u009a\7p\2\2\u009a\u009b\7v\2\2\u009b\u0103\7q\2\2"+
		"\u009c\u009d\7h\2\2\u009d\u009e\7k\2\2\u009e\u009f\7o\2\2\u009f\u00a0"+
		"\7a\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7s\2\2\u00a3"+
		"\u00a4\7w\2\2\u00a4\u00a5\7c\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7v\2\2"+
		"\u00a7\u0103\7q\2\2\u00a8\u00a9\7t\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab"+
		"\7i\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7v\2\2\u00ae"+
		"\u00af\7t\2\2\u00af\u0103\7q\2\2\u00b0\u00b1\7h\2\2\u00b1\u00b2\7k\2\2"+
		"\u00b2\u00b3\7o\2\2\u00b3\u00b4\7a\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6"+
		"\7g\2\2\u00b6\u00b7\7i\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7u\2\2\u00b9"+
		"\u00ba\7v\2\2\u00ba\u00bb\7t\2\2\u00bb\u0103\7q\2\2\u00bc\u00bd\7v\2\2"+
		"\u00bd\u00be\7k\2\2\u00be\u00bf\7r\2\2\u00bf\u0103\7q\2\2\u00c0\u00c1"+
		"\7r\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7e\2\2\u00c4"+
		"\u00c5\7g\2\2\u00c5\u00c6\7f\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7o\2\2"+
		"\u00c8\u00c9\7g\2\2\u00c9\u00ca\7p\2\2\u00ca\u00cb\7v\2\2\u00cb\u0103"+
		"\7q\2\2\u00cc\u00cd\7h\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7o\2\2\u00cf"+
		"\u00d0\7a\2\2\u00d0\u00d1\7r\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7q\2\2"+
		"\u00d3\u00d4\7e\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7f\2\2\u00d6\u00d7"+
		"\7k\2\2\u00d7\u00d8\7o\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7p\2\2\u00da"+
		"\u00db\7v\2\2\u00db\u0103\7q\2\2\u00dc\u00dd\7x\2\2\u00dd\u00de\7c\2\2"+
		"\u00de\u0103\7t\2\2\u00df\u00e0\7h\2\2\u00e0\u00e1\7w\2\2\u00e1\u00e2"+
		"\7p\2\2\u00e2\u00e3\7e\2\2\u00e3\u00e4\7c\2\2\u00e4\u0103\7q\2\2\u00e5"+
		"\u00e6\7h\2\2\u00e6\u00e7\7k\2\2\u00e7\u00e8\7o\2\2\u00e8\u00e9\7a\2\2"+
		"\u00e9\u00ea\7h\2\2\u00ea\u00eb\7w\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed"+
		"\7e\2\2\u00ed\u00ee\7c\2\2\u00ee\u0103\7q\2\2\u00ef\u00f0\7t\2\2\u00f0"+
		"\u00f1\7g\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7t\2\2"+
		"\u00f4\u00f5\7p\2\2\u00f5\u0103\7g\2\2\u00f6\u00f7\7e\2\2\u00f7\u00f8"+
		"\7q\2\2\u00f8\u00f9\7p\2\2\u00f9\u00fa\7u\2\2\u00fa\u00fb\7v\2\2\u00fb"+
		"\u00fc\7c\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7v\2\2\u00fe\u0103\7g\2\2"+
		"\u00ff\u0100\7p\2\2\u0100\u0101\7c\2\2\u0101\u0103\7q\2\2\u0102\67\3\2"+
		"\2\2\u0102@\3\2\2\2\u0102G\3\2\2\2\u0102T\3\2\2\2\u0102X\3\2\2\2\u0102"+
		"_\3\2\2\2\u0102a\3\2\2\2\u0102f\3\2\2\2\u0102k\3\2\2\2\u0102q\3\2\2\2"+
		"\u0102u\3\2\2\2\u0102y\3\2\2\2\u0102\u0081\3\2\2\2\u0102\u0085\3\2\2\2"+
		"\u0102\u008d\3\2\2\2\u0102\u0090\3\2\2\2\u0102\u0094\3\2\2\2\u0102\u009c"+
		"\3\2\2\2\u0102\u00a8\3\2\2\2\u0102\u00b0\3\2\2\2\u0102\u00bc\3\2\2\2\u0102"+
		"\u00c0\3\2\2\2\u0102\u00cc\3\2\2\2\u0102\u00dc\3\2\2\2\u0102\u00df\3\2"+
		"\2\2\u0102\u00e5\3\2\2\2\u0102\u00ef\3\2\2\2\u0102\u00f6\3\2\2\2\u0102"+
		"\u00ff\3\2\2\2\u0103\4\3\2\2\2\u0104\u0105\t\2\2\2\u0105\6\3\2\2\2\u0106"+
		"\u0107\7\60\2\2\u0107\u0108\7\60\2\2\u0108\b\3\2\2\2\u0109\u010b\4\62"+
		";\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\n\3\2\2\2\u010e\u0110\4\62;\2\u010f\u010e\3\2\2\2"+
		"\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0119"+
		"\3\2\2\2\u0113\u0115\7\60\2\2\u0114\u0116\4\62;\2\u0115\u0114\3\2\2\2"+
		"\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a"+
		"\3\2\2\2\u0119\u0113\3\2\2\2\u0119\u011a\3\2\2\2\u011a\f\3\2\2\2\u011b"+
		"\u011c\7x\2\2\u011c\u011d\7g\2\2\u011d\u011e\7t\2\2\u011e\u011f\7f\2\2"+
		"\u011f\u0120\7c\2\2\u0120\u0121\7f\2\2\u0121\u0122\7g\2\2\u0122\u0123"+
		"\7k\2\2\u0123\u0124\7t\2\2\u0124\u012b\7q\2\2\u0125\u0126\7h\2\2\u0126"+
		"\u0127\7c\2\2\u0127\u0128\7n\2\2\u0128\u0129\7u\2\2\u0129\u012b\7q\2\2"+
		"\u012a\u011b\3\2\2\2\u012a\u0125\3\2\2\2\u012b\16\3\2\2\2\u012c\u0130"+
		"\t\3\2\2\u012d\u012f\t\4\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130"+
		"\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\20\3\2\2\2\u0132\u0130\3\2\2"+
		"\2\u0133\u0138\7$\2\2\u0134\u0137\5\23\n\2\u0135\u0137\n\5\2\2\u0136\u0134"+
		"\3\2\2\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138"+
		"\u0139\3\2\2\2\u0139\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013c\7$"+
		"\2\2\u013c\22\3\2\2\2\u013d\u013e\7^\2\2\u013e\u013f\7)\2\2\u013f\24\3"+
		"\2\2\2\u0140\u0144\7}\2\2\u0141\u0143\n\6\2\2\u0142\u0141\3\2\2\2\u0143"+
		"\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2"+
		"\2\2\u0146\u0144\3\2\2\2\u0147\u0148\7\177\2\2\u0148\u0149\b\13\2\2\u0149"+
		"\26\3\2\2\2\u014a\u014b\t\7\2\2\u014b\u014c\b\f\3\2\u014c\30\3\2\2\2\u014d"+
		"\u014e\7>\2\2\u014e\u014f\7/\2\2\u014f\32\3\2\2\2\u0150\u0151\t\b\2\2"+
		"\u0151\34\3\2\2\2\u0152\u015c\7@\2\2\u0153\u0154\7@\2\2\u0154\u015c\7"+
		"?\2\2\u0155\u015c\7>\2\2\u0156\u0157\7>\2\2\u0157\u015c\7?\2\2\u0158\u0159"+
		"\7>\2\2\u0159\u015c\7@\2\2\u015a\u015c\7?\2\2\u015b\u0152\3\2\2\2\u015b"+
		"\u0153\3\2\2\2\u015b\u0155\3\2\2\2\u015b\u0156\3\2\2\2\u015b\u0158\3\2"+
		"\2\2\u015b\u015a\3\2\2\2\u015c\36\3\2\2\2\u015d\u0161\5!\21\2\u015e\u0161"+
		"\5#\22\2\u015f\u0161\5%\23\2\u0160\u015d\3\2\2\2\u0160\u015e\3\2\2\2\u0160"+
		"\u015f\3\2\2\2\u0161 \3\2\2\2\u0162\u0163\t\t\2\2\u0163\"\3\2\2\2\u0164"+
		"\u0165\t\n\2\2\u0165$\3\2\2\2\u0166\u0167\7\'\2\2\u0167&\3\2\2\2\u0168"+
		"\u0169\7<\2\2\u0169(\3\2\2\2\u016a\u016b\7*\2\2\u016b*\3\2\2\2\u016c\u016d"+
		"\7+\2\2\u016d,\3\2\2\2\u016e\u016f\7]\2\2\u016f.\3\2\2\2\u0170\u0171\7"+
		"_\2\2\u0171\60\3\2\2\2\u0172\u0177\7$\2\2\u0173\u0176\5\23\n\2\u0174\u0176"+
		"\n\5\2\2\u0175\u0173\3\2\2\2\u0175\u0174\3\2\2\2\u0176\u0179\3\2\2\2\u0177"+
		"\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a\3\2\2\2\u0179\u0177\3\2"+
		"\2\2\u017a\u017b\t\13\2\2\u017b\62\3\2\2\2\u017c\u0180\7}\2\2\u017d\u017f"+
		"\n\6\2\2\u017e\u017d\3\2\2\2\u017f\u0182\3\2\2\2\u0180\u017e\3\2\2\2\u0180"+
		"\u0181\3\2\2\2\u0181\u0183\3\2\2\2\u0182\u0180\3\2\2\2\u0183\u0184\t\13"+
		"\2\2\u0184\64\3\2\2\2\u0185\u0186\13\2\2\2\u0186\66\3\2\2\2\22\2\u0102"+
		"\u010c\u0111\u0117\u0119\u012a\u0130\u0136\u0138\u0144\u015b\u0160\u0175"+
		"\u0177\u0180\4\3\13\2\3\f\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}