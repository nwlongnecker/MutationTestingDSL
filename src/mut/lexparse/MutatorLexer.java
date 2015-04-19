// Generated from Mutator.g4 by ANTLR 4.5
package mut.lexparse;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MutatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, COLON=2, SOURCE=3, TEST=4, USE=5, ADD=6, REMOVE=7, LIST=8, STRAIN=9, 
		END=10, MUTATE=11, TO=12, ID=13, FILEPATH=14, DIRNAME=15, FILENAME=16, 
		WS=17, COMMENT=18, SYMBOL=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "COLON", "SOURCE", "TEST", "USE", "ADD", "REMOVE", "LIST", "STRAIN", 
		"END", "MUTATE", "TO", "ID", "FILEPATH", "DIRNAME", "FILENAME", "WS", 
		"COMMENT", "SYMBOL", "LETTER", "DIGIT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "':'", "'source'", "'test'", "'use'", "'add'", "'remove'", 
		"'list'", "'strain'", "'end'", "'mutate'", "'to'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "COLON", "SOURCE", "TEST", "USE", "ADD", "REMOVE", "LIST", 
		"STRAIN", "END", "MUTATE", "TO", "ID", "FILEPATH", "DIRNAME", "FILENAME", 
		"WS", "COMMENT", "SYMBOL"
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


	public MutatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mutator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\u00b4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\7\16k\n\16\f\16\16\16n\13\16\3\17\3\17\3\17\7\17s\n"+
		"\17\f\17\16\17v\13\17\3\17\3\17\7\17z\n\17\f\17\16\17}\13\17\3\17\5\17"+
		"\u0080\n\17\3\20\3\20\3\20\6\20\u0085\n\20\r\20\16\20\u0086\3\20\3\20"+
		"\3\20\5\20\u008c\n\20\5\20\u008e\n\20\3\20\5\20\u0091\n\20\3\21\3\21\3"+
		"\21\6\21\u0096\n\21\r\21\16\21\u0097\3\22\3\22\6\22\u009c\n\22\r\22\16"+
		"\22\u009d\3\22\3\22\3\23\3\23\7\23\u00a4\n\23\f\23\16\23\u00a7\13\23\3"+
		"\23\5\23\u00aa\n\23\3\24\6\24\u00ad\n\24\r\24\16\24\u00ae\3\25\3\25\3"+
		"\26\3\26\3\u00a5\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\2+\2\3\2\b\4\2\60\60aa\5"+
		"\2\13\f\17\17\"\"\3\3\f\f\b\2\13\f\17\17\"\"%%..<<\4\2C\\c|\3\2\62;\u00c4"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2\2\t8\3\2"+
		"\2\2\13=\3\2\2\2\rA\3\2\2\2\17E\3\2\2\2\21L\3\2\2\2\23Q\3\2\2\2\25X\3"+
		"\2\2\2\27\\\3\2\2\2\31c\3\2\2\2\33f\3\2\2\2\35\177\3\2\2\2\37\u008d\3"+
		"\2\2\2!\u0095\3\2\2\2#\u009b\3\2\2\2%\u00a1\3\2\2\2\'\u00ac\3\2\2\2)\u00b0"+
		"\3\2\2\2+\u00b2\3\2\2\2-.\7.\2\2.\4\3\2\2\2/\60\7<\2\2\60\6\3\2\2\2\61"+
		"\62\7u\2\2\62\63\7q\2\2\63\64\7w\2\2\64\65\7t\2\2\65\66\7e\2\2\66\67\7"+
		"g\2\2\67\b\3\2\2\289\7v\2\29:\7g\2\2:;\7u\2\2;<\7v\2\2<\n\3\2\2\2=>\7"+
		"w\2\2>?\7u\2\2?@\7g\2\2@\f\3\2\2\2AB\7c\2\2BC\7f\2\2CD\7f\2\2D\16\3\2"+
		"\2\2EF\7t\2\2FG\7g\2\2GH\7o\2\2HI\7q\2\2IJ\7x\2\2JK\7g\2\2K\20\3\2\2\2"+
		"LM\7n\2\2MN\7k\2\2NO\7u\2\2OP\7v\2\2P\22\3\2\2\2QR\7u\2\2RS\7v\2\2ST\7"+
		"t\2\2TU\7c\2\2UV\7k\2\2VW\7p\2\2W\24\3\2\2\2XY\7g\2\2YZ\7p\2\2Z[\7f\2"+
		"\2[\26\3\2\2\2\\]\7o\2\2]^\7w\2\2^_\7v\2\2_`\7c\2\2`a\7v\2\2ab\7g\2\2"+
		"b\30\3\2\2\2cd\7v\2\2de\7q\2\2e\32\3\2\2\2fl\5)\25\2gk\5)\25\2hk\5+\26"+
		"\2ik\7a\2\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2"+
		"\2m\34\3\2\2\2nl\3\2\2\2op\5\37\20\2pq\7\61\2\2qs\3\2\2\2ro\3\2\2\2sv"+
		"\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3\2\2\2w\u0080\5\37\20\2xz\5"+
		"\37\20\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~"+
		"\u0080\5!\21\2\177t\3\2\2\2\177{\3\2\2\2\u0080\36\3\2\2\2\u0081\u0085"+
		"\5)\25\2\u0082\u0085\5+\26\2\u0083\u0085\7a\2\2\u0084\u0081\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u008e\3\2\2\2\u0088\u008c\7\60\2\2\u0089"+
		"\u008a\7\60\2\2\u008a\u008c\7\60\2\2\u008b\u0088\3\2\2\2\u008b\u0089\3"+
		"\2\2\2\u008c\u008e\3\2\2\2\u008d\u0084\3\2\2\2\u008d\u008b\3\2\2\2\u008e"+
		"\u0090\3\2\2\2\u008f\u0091\7\61\2\2\u0090\u008f\3\2\2\2\u0090\u0091\3"+
		"\2\2\2\u0091 \3\2\2\2\u0092\u0096\5)\25\2\u0093\u0096\5+\26\2\u0094\u0096"+
		"\t\2\2\2\u0095\u0092\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\"\3\2\2\2"+
		"\u0099\u009c\t\3\2\2\u009a\u009c\5%\23\2\u009b\u0099\3\2\2\2\u009b\u009a"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a0\b\22\2\2\u00a0$\3\2\2\2\u00a1\u00a5\7%\2\2"+
		"\u00a2\u00a4\13\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8"+
		"\u00aa\t\4\2\2\u00a9\u00a8\3\2\2\2\u00aa&\3\2\2\2\u00ab\u00ad\n\5\2\2"+
		"\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af(\3\2\2\2\u00b0\u00b1\t\6\2\2\u00b1*\3\2\2\2\u00b2\u00b3"+
		"\t\7\2\2\u00b3,\3\2\2\2\24\2jlt{\177\u0084\u0086\u008b\u008d\u0090\u0095"+
		"\u0097\u009b\u009d\u00a5\u00a9\u00ae\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}