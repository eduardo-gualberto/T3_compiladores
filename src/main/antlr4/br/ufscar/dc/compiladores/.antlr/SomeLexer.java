// Generated from /home/eduardo/Documents/Compiladores/Lexer/src/main/antlr4/br/ufscar/dc/compiladores/SomeLexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SomeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PALAVRA_CHAVE=1, PUNCTATION=2, INTERVALO=3, NUM_INT=4, NUM_REAL=5, IDENT=6, 
		CADEIA=7, COMENTARIO=8, WS=9, ATRIBUICAO=10, POINTER=11, OP_REL=12, OP_ARIT=13, 
		DELIM=14, ABREPAR=15, FECHAPAR=16, ABRECOLCH=17, FECHACOLCH=18, ERROR_CADEIA=19, 
		ERROR_COMENTARIO=20, UNKNOWN=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PALAVRA_CHAVE", "PUNCTATION", "INTERVALO", "NUM_INT", "NUM_REAL", "IDENT", 
			"CADEIA", "ESC_SEQ", "COMENTARIO", "WS", "ATRIBUICAO", "POINTER", "OP_REL", 
			"OP_ARIT", "DELIM", "ABREPAR", "FECHAPAR", "ABRECOLCH", "FECHACOLCH", 
			"ERROR_CADEIA", "ERROR_COMENTARIO", "UNKNOWN"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'..'", null, null, null, null, null, null, "'<-'", 
			null, null, null, "':'", "'('", "')'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PALAVRA_CHAVE", "PUNCTATION", "INTERVALO", "NUM_INT", "NUM_REAL", 
			"IDENT", "CADEIA", "COMENTARIO", "WS", "ATRIBUICAO", "POINTER", "OP_REL", 
			"OP_ARIT", "DELIM", "ABREPAR", "FECHAPAR", "ABRECOLCH", "FECHACOLCH", 
			"ERROR_CADEIA", "ERROR_COMENTARIO", "UNKNOWN"
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

	    if(!queue.isEmpty()) {
	      return queue.poll();
	    }

	    Token next = super.nextToken();

	    if(next.getType() != UNKNOWN) {
	      return next;
	    }

	    StringBuilder builder = new StringBuilder();
	    int initialLine = getLine();
	    while(next.getType() == UNKNOWN) {
	      builder.append(next.getText());
	      next = super.nextToken();
	    }

	    setLine(initialLine);

	    queue.offer(next);

	    return new CommonToken(UNKNOWN, builder.toString());
	  }


	public SomeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SomeLexer.g4"; }

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
		case 8:
			COMENTARIO_action((RuleContext)_localctx, actionIndex);
			break;
		case 9:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u018f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
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
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u0125\n\2"+
		"\3\3\3\3\3\4\3\4\3\4\3\5\6\5\u012d\n\5\r\5\16\5\u012e\3\6\6\6\u0132\n"+
		"\6\r\6\16\6\u0133\3\6\3\6\6\6\u0138\n\6\r\6\16\6\u0139\5\6\u013c\n\6\3"+
		"\7\3\7\7\7\u0140\n\7\f\7\16\7\u0143\13\7\3\b\3\b\3\b\7\b\u0148\n\b\f\b"+
		"\16\b\u014b\13\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\7\n\u0154\n\n\f\n\16\n\u0157"+
		"\13\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\5\16\u016d\n\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\7\25\u017e\n\25\f\25"+
		"\16\25\u0181\13\25\3\25\3\25\3\26\3\26\7\26\u0187\n\26\f\26\16\26\u018a"+
		"\13\26\3\26\3\26\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\2\23"+
		"\n\25\13\27\f\31\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26-\27\3\2"+
		"\13\4\2..\60\60\4\2C\\c|\6\2\62;C\\aac|\6\2\f\f\17\17$$^^\5\2\f\f\17\17"+
		"\177\177\5\2\13\f\17\17\"\"\4\2((``\6\2\'\',-//\61\61\4\2\f\f\17\17\2"+
		"\u01c1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3\u0124\3\2\2\2"+
		"\5\u0126\3\2\2\2\7\u0128\3\2\2\2\t\u012c\3\2\2\2\13\u0131\3\2\2\2\r\u013d"+
		"\3\2\2\2\17\u0144\3\2\2\2\21\u014e\3\2\2\2\23\u0151\3\2\2\2\25\u015b\3"+
		"\2\2\2\27\u015e\3\2\2\2\31\u0161\3\2\2\2\33\u016c\3\2\2\2\35\u016e\3\2"+
		"\2\2\37\u0170\3\2\2\2!\u0172\3\2\2\2#\u0174\3\2\2\2%\u0176\3\2\2\2\'\u0178"+
		"\3\2\2\2)\u017a\3\2\2\2+\u0184\3\2\2\2-\u018d\3\2\2\2/\60\7c\2\2\60\61"+
		"\7n\2\2\61\62\7i\2\2\62\63\7q\2\2\63\64\7t\2\2\64\65\7k\2\2\65\66\7v\2"+
		"\2\66\67\7o\2\2\67\u0125\7q\2\289\7f\2\29:\7g\2\2:;\7e\2\2;<\7n\2\2<="+
		"\7c\2\2=>\7t\2\2>\u0125\7g\2\2?@\7h\2\2@A\7k\2\2AB\7o\2\2BC\7a\2\2CD\7"+
		"c\2\2DE\7n\2\2EF\7i\2\2FG\7q\2\2GH\7t\2\2HI\7k\2\2IJ\7v\2\2JK\7o\2\2K"+
		"\u0125\7q\2\2LM\7n\2\2MN\7g\2\2NO\7k\2\2O\u0125\7c\2\2PQ\7g\2\2QR\7u\2"+
		"\2RS\7e\2\2ST\7t\2\2TU\7g\2\2UV\7x\2\2V\u0125\7c\2\2WX\7n\2\2XY\7k\2\2"+
		"YZ\7v\2\2Z[\7g\2\2[\\\7t\2\2\\]\7c\2\2]\u0125\7n\2\2^_\7k\2\2_`\7p\2\2"+
		"`a\7v\2\2ab\7g\2\2bc\7k\2\2cd\7t\2\2d\u0125\7q\2\2ef\7t\2\2fg\7g\2\2g"+
		"h\7c\2\2h\u0125\7n\2\2ij\7n\2\2jk\7q\2\2kl\7i\2\2lm\7k\2\2mn\7e\2\2n\u0125"+
		"\7q\2\2o\u0125\7g\2\2pq\7q\2\2q\u0125\7w\2\2rs\7u\2\2s\u0125\7g\2\2tu"+
		"\7g\2\2uv\7p\2\2vw\7v\2\2wx\7c\2\2x\u0125\7q\2\2yz\7u\2\2z{\7g\2\2{|\7"+
		"p\2\2|}\7c\2\2}\u0125\7q\2\2~\177\7h\2\2\177\u0080\7k\2\2\u0080\u0081"+
		"\7o\2\2\u0081\u0082\7a\2\2\u0082\u0083\7u\2\2\u0083\u0125\7g\2\2\u0084"+
		"\u0085\7e\2\2\u0085\u0086\7c\2\2\u0086\u0087\7u\2\2\u0087\u0125\7q\2\2"+
		"\u0088\u0089\7u\2\2\u0089\u008a\7g\2\2\u008a\u008b\7l\2\2\u008b\u0125"+
		"\7c\2\2\u008c\u008d\7h\2\2\u008d\u008e\7k\2\2\u008e\u008f\7o\2\2\u008f"+
		"\u0090\7a\2\2\u0090\u0091\7e\2\2\u0091\u0092\7c\2\2\u0092\u0093\7u\2\2"+
		"\u0093\u0125\7q\2\2\u0094\u0095\7r\2\2\u0095\u0096\7c\2\2\u0096\u0097"+
		"\7t\2\2\u0097\u0125\7c\2\2\u0098\u0099\7h\2\2\u0099\u009a\7k\2\2\u009a"+
		"\u009b\7o\2\2\u009b\u009c\7a\2\2\u009c\u009d\7r\2\2\u009d\u009e\7c\2\2"+
		"\u009e\u009f\7t\2\2\u009f\u0125\7c\2\2\u00a0\u00a1\7c\2\2\u00a1\u00a2"+
		"\7v\2\2\u00a2\u0125\7g\2\2\u00a3\u00a4\7h\2\2\u00a4\u00a5\7c\2\2\u00a5"+
		"\u00a6\7e\2\2\u00a6\u0125\7c\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7p\2\2"+
		"\u00a9\u00aa\7s\2\2\u00aa\u00ab\7w\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad"+
		"\7p\2\2\u00ad\u00ae\7v\2\2\u00ae\u0125\7q\2\2\u00af\u00b0\7h\2\2\u00b0"+
		"\u00b1\7k\2\2\u00b1\u00b2\7o\2\2\u00b2\u00b3\7a\2\2\u00b3\u00b4\7g\2\2"+
		"\u00b4\u00b5\7p\2\2\u00b5\u00b6\7s\2\2\u00b6\u00b7\7w\2\2\u00b7\u00b8"+
		"\7c\2\2\u00b8\u00b9\7p\2\2\u00b9\u00ba\7v\2\2\u00ba\u0125\7q\2\2\u00bb"+
		"\u00bc\7t\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7i\2\2\u00be\u00bf\7k\2\2"+
		"\u00bf\u00c0\7u\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7t\2\2\u00c2\u0125"+
		"\7q\2\2\u00c3\u00c4\7h\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7o\2\2\u00c6"+
		"\u00c7\7a\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7i\2\2"+
		"\u00ca\u00cb\7k\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce"+
		"\7t\2\2\u00ce\u0125\7q\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1\7k\2\2\u00d1"+
		"\u00d2\7r\2\2\u00d2\u0125\7q\2\2\u00d3\u00d4\7r\2\2\u00d4\u00d5\7t\2\2"+
		"\u00d5\u00d6\7q\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9"+
		"\7f\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7o\2\2\u00db\u00dc\7g\2\2\u00dc"+
		"\u00dd\7p\2\2\u00dd\u00de\7v\2\2\u00de\u0125\7q\2\2\u00df\u00e0\7h\2\2"+
		"\u00e0\u00e1\7k\2\2\u00e1\u00e2\7o\2\2\u00e2\u00e3\7a\2\2\u00e3\u00e4"+
		"\7r\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7e\2\2\u00e7"+
		"\u00e8\7g\2\2\u00e8\u00e9\7f\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7o\2\2"+
		"\u00eb\u00ec\7g\2\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7v\2\2\u00ee\u0125"+
		"\7q\2\2\u00ef\u00f0\7x\2\2\u00f0\u00f1\7c\2\2\u00f1\u0125\7t\2\2\u00f2"+
		"\u00f3\7h\2\2\u00f3\u00f4\7w\2\2\u00f4\u00f5\7p\2\2\u00f5\u00f6\7e\2\2"+
		"\u00f6\u00f7\7c\2\2\u00f7\u0125\7q\2\2\u00f8\u00f9\7h\2\2\u00f9\u00fa"+
		"\7k\2\2\u00fa\u00fb\7o\2\2\u00fb\u00fc\7a\2\2\u00fc\u00fd\7h\2\2\u00fd"+
		"\u00fe\7w\2\2\u00fe\u00ff\7p\2\2\u00ff\u0100\7e\2\2\u0100\u0101\7c\2\2"+
		"\u0101\u0125\7q\2\2\u0102\u0103\7t\2\2\u0103\u0104\7g\2\2\u0104\u0105"+
		"\7v\2\2\u0105\u0106\7q\2\2\u0106\u0107\7t\2\2\u0107\u0108\7p\2\2\u0108"+
		"\u0125\7g\2\2\u0109\u010a\7e\2\2\u010a\u010b\7q\2\2\u010b\u010c\7p\2\2"+
		"\u010c\u010d\7u\2\2\u010d\u010e\7v\2\2\u010e\u010f\7c\2\2\u010f\u0110"+
		"\7p\2\2\u0110\u0111\7v\2\2\u0111\u0125\7g\2\2\u0112\u0113\7h\2\2\u0113"+
		"\u0114\7c\2\2\u0114\u0115\7n\2\2\u0115\u0116\7u\2\2\u0116\u0125\7q\2\2"+
		"\u0117\u0118\7p\2\2\u0118\u0119\7c\2\2\u0119\u0125\7q\2\2\u011a\u011b"+
		"\7x\2\2\u011b\u011c\7g\2\2\u011c\u011d\7t\2\2\u011d\u011e\7f\2\2\u011e"+
		"\u011f\7c\2\2\u011f\u0120\7f\2\2\u0120\u0121\7g\2\2\u0121\u0122\7k\2\2"+
		"\u0122\u0123\7t\2\2\u0123\u0125\7q\2\2\u0124/\3\2\2\2\u01248\3\2\2\2\u0124"+
		"?\3\2\2\2\u0124L\3\2\2\2\u0124P\3\2\2\2\u0124W\3\2\2\2\u0124^\3\2\2\2"+
		"\u0124e\3\2\2\2\u0124i\3\2\2\2\u0124o\3\2\2\2\u0124p\3\2\2\2\u0124r\3"+
		"\2\2\2\u0124t\3\2\2\2\u0124y\3\2\2\2\u0124~\3\2\2\2\u0124\u0084\3\2\2"+
		"\2\u0124\u0088\3\2\2\2\u0124\u008c\3\2\2\2\u0124\u0094\3\2\2\2\u0124\u0098"+
		"\3\2\2\2\u0124\u00a0\3\2\2\2\u0124\u00a3\3\2\2\2\u0124\u00a7\3\2\2\2\u0124"+
		"\u00af\3\2\2\2\u0124\u00bb\3\2\2\2\u0124\u00c3\3\2\2\2\u0124\u00cf\3\2"+
		"\2\2\u0124\u00d3\3\2\2\2\u0124\u00df\3\2\2\2\u0124\u00ef\3\2\2\2\u0124"+
		"\u00f2\3\2\2\2\u0124\u00f8\3\2\2\2\u0124\u0102\3\2\2\2\u0124\u0109\3\2"+
		"\2\2\u0124\u0112\3\2\2\2\u0124\u0117\3\2\2\2\u0124\u011a\3\2\2\2\u0125"+
		"\4\3\2\2\2\u0126\u0127\t\2\2\2\u0127\6\3\2\2\2\u0128\u0129\7\60\2\2\u0129"+
		"\u012a\7\60\2\2\u012a\b\3\2\2\2\u012b\u012d\4\62;\2\u012c\u012b\3\2\2"+
		"\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\n"+
		"\3\2\2\2\u0130\u0132\4\62;\2\u0131\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u013b\3\2\2\2\u0135\u0137\7\60"+
		"\2\2\u0136\u0138\4\62;\2\u0137\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0135\3\2"+
		"\2\2\u013b\u013c\3\2\2\2\u013c\f\3\2\2\2\u013d\u0141\t\3\2\2\u013e\u0140"+
		"\t\4\2\2\u013f\u013e\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0142\3\2\2\2\u0142\16\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0149\7$\2\2"+
		"\u0145\u0148\5\21\t\2\u0146\u0148\n\5\2\2\u0147\u0145\3\2\2\2\u0147\u0146"+
		"\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014d\7$\2\2\u014d\20\3\2\2\2"+
		"\u014e\u014f\7^\2\2\u014f\u0150\7)\2\2\u0150\22\3\2\2\2\u0151\u0155\7"+
		"}\2\2\u0152\u0154\n\6\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155"+
		"\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u0155\3\2"+
		"\2\2\u0158\u0159\7\177\2\2\u0159\u015a\b\n\2\2\u015a\24\3\2\2\2\u015b"+
		"\u015c\t\7\2\2\u015c\u015d\b\13\3\2\u015d\26\3\2\2\2\u015e\u015f\7>\2"+
		"\2\u015f\u0160\7/\2\2\u0160\30\3\2\2\2\u0161\u0162\t\b\2\2\u0162\32\3"+
		"\2\2\2\u0163\u016d\7@\2\2\u0164\u0165\7@\2\2\u0165\u016d\7?\2\2\u0166"+
		"\u016d\7>\2\2\u0167\u0168\7>\2\2\u0168\u016d\7?\2\2\u0169\u016a\7>\2\2"+
		"\u016a\u016d\7@\2\2\u016b\u016d\7?\2\2\u016c\u0163\3\2\2\2\u016c\u0164"+
		"\3\2\2\2\u016c\u0166\3\2\2\2\u016c\u0167\3\2\2\2\u016c\u0169\3\2\2\2\u016c"+
		"\u016b\3\2\2\2\u016d\34\3\2\2\2\u016e\u016f\t\t\2\2\u016f\36\3\2\2\2\u0170"+
		"\u0171\7<\2\2\u0171 \3\2\2\2\u0172\u0173\7*\2\2\u0173\"\3\2\2\2\u0174"+
		"\u0175\7+\2\2\u0175$\3\2\2\2\u0176\u0177\7]\2\2\u0177&\3\2\2\2\u0178\u0179"+
		"\7_\2\2\u0179(\3\2\2\2\u017a\u017f\7$\2\2\u017b\u017e\5\21\t\2\u017c\u017e"+
		"\n\5\2\2\u017d\u017b\3\2\2\2\u017d\u017c\3\2\2\2\u017e\u0181\3\2\2\2\u017f"+
		"\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u017f\3\2"+
		"\2\2\u0182\u0183\t\n\2\2\u0183*\3\2\2\2\u0184\u0188\7}\2\2\u0185\u0187"+
		"\n\6\2\2\u0186\u0185\3\2\2\2\u0187\u018a\3\2\2\2\u0188\u0186\3\2\2\2\u0188"+
		"\u0189\3\2\2\2\u0189\u018b\3\2\2\2\u018a\u0188\3\2\2\2\u018b\u018c\t\n"+
		"\2\2\u018c,\3\2\2\2\u018d\u018e\13\2\2\2\u018e.\3\2\2\2\20\2\u0124\u012e"+
		"\u0133\u0139\u013b\u0141\u0147\u0149\u0155\u016c\u017d\u017f\u0188\4\3"+
		"\n\2\3\13\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}