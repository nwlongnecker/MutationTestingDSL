// Generated from MutatorOld.g4 by ANTLR 4.5
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MutatorOldLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, MUTANT_ZERO=2, KILL_WITH=3, ALIEN_STRAINS=4, STRAIN=5, END=6, 
		MUTATE=7, TO=8, ID=9, FILENAME=10, SYMBOL=11, WS=12, COMMENT=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "MUTANT_ZERO", "KILL_WITH", "ALIEN_STRAINS", "STRAIN", "END", 
		"MUTATE", "TO", "ID", "FILENAME", "SYMBOL", "WS", "COMMENT", "LETTER", 
		"DIGIT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'mutant zero:'", "'kill with:'", "'alien strains:'", "'strain'", 
		"'end'", "'mutate'", "'to'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "MUTANT_ZERO", "KILL_WITH", "ALIEN_STRAINS", "STRAIN", 
		"END", "MUTATE", "TO", "ID", "FILENAME", "SYMBOL", "WS", "COMMENT"
	};
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


	public MutatorOldLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MutatorOld.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\u008c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\nd\n\n\f\n\16\ng\13\n\3\13\3"+
		"\13\3\13\3\13\7\13m\n\13\f\13\16\13p\13\13\3\f\6\fs\n\f\r\f\16\ft\3\r"+
		"\3\r\6\ry\n\r\r\r\16\rz\3\r\3\r\3\16\3\16\7\16\u0081\n\16\f\16\16\16\u0084"+
		"\13\16\3\16\5\16\u0087\n\16\3\17\3\17\3\20\3\20\3\u0082\2\21\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\2\37\2\3\2\t"+
		"\4\2AAaa\5\2\60\61AAaa\n\2##((,-//\61\61>@~~\u0080\u0080\5\2\13\f\17\17"+
		"\"\"\3\3\f\f\4\2C\\c|\3\2\62;\u0093\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3!\3\2\2"+
		"\2\5#\3\2\2\2\7\60\3\2\2\2\t;\3\2\2\2\13J\3\2\2\2\rQ\3\2\2\2\17U\3\2\2"+
		"\2\21\\\3\2\2\2\23_\3\2\2\2\25h\3\2\2\2\27r\3\2\2\2\31x\3\2\2\2\33~\3"+
		"\2\2\2\35\u0088\3\2\2\2\37\u008a\3\2\2\2!\"\7.\2\2\"\4\3\2\2\2#$\7o\2"+
		"\2$%\7w\2\2%&\7v\2\2&\'\7c\2\2\'(\7p\2\2()\7v\2\2)*\7\"\2\2*+\7|\2\2+"+
		",\7g\2\2,-\7t\2\2-.\7q\2\2./\7<\2\2/\6\3\2\2\2\60\61\7m\2\2\61\62\7k\2"+
		"\2\62\63\7n\2\2\63\64\7n\2\2\64\65\7\"\2\2\65\66\7y\2\2\66\67\7k\2\2\67"+
		"8\7v\2\289\7j\2\29:\7<\2\2:\b\3\2\2\2;<\7c\2\2<=\7n\2\2=>\7k\2\2>?\7g"+
		"\2\2?@\7p\2\2@A\7\"\2\2AB\7u\2\2BC\7v\2\2CD\7t\2\2DE\7c\2\2EF\7k\2\2F"+
		"G\7p\2\2GH\7u\2\2HI\7<\2\2I\n\3\2\2\2JK\7u\2\2KL\7v\2\2LM\7t\2\2MN\7c"+
		"\2\2NO\7k\2\2OP\7p\2\2P\f\3\2\2\2QR\7g\2\2RS\7p\2\2ST\7f\2\2T\16\3\2\2"+
		"\2UV\7o\2\2VW\7w\2\2WX\7v\2\2XY\7c\2\2YZ\7v\2\2Z[\7g\2\2[\20\3\2\2\2\\"+
		"]\7v\2\2]^\7q\2\2^\22\3\2\2\2_e\5\35\17\2`d\5\35\17\2ad\5\37\20\2bd\t"+
		"\2\2\2c`\3\2\2\2ca\3\2\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\24"+
		"\3\2\2\2ge\3\2\2\2hn\5\35\17\2im\5\35\17\2jm\5\37\20\2km\t\3\2\2li\3\2"+
		"\2\2lj\3\2\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\26\3\2\2\2pn\3"+
		"\2\2\2qs\t\4\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\30\3\2\2\2v"+
		"y\t\5\2\2wy\5\33\16\2xv\3\2\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2"+
		"\2{|\3\2\2\2|}\b\r\2\2}\32\3\2\2\2~\u0082\7%\2\2\177\u0081\13\2\2\2\u0080"+
		"\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0083\3\2\2\2\u0082\u0080\3\2\2"+
		"\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0087\t\6\2\2\u0086\u0085"+
		"\3\2\2\2\u0087\34\3\2\2\2\u0088\u0089\t\7\2\2\u0089\36\3\2\2\2\u008a\u008b"+
		"\t\b\2\2\u008b \3\2\2\2\f\2celntxz\u0082\u0086\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}