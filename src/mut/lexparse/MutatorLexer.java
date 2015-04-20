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
		END=10, MUTATE=11, TO=12, REPORT=13, LAST=14, ALL=15, ID=16, FILEPATH=17, 
		DIRNAME=18, FILENAME=19, WS=20, COMMENT=21, SYMBOL=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "COLON", "SOURCE", "TEST", "USE", "ADD", "REMOVE", "LIST", "STRAIN", 
		"END", "MUTATE", "TO", "REPORT", "LAST", "ALL", "ID", "FILEPATH", "DIRNAME", 
		"FILENAME", "WS", "COMMENT", "SYMBOL", "LETTER", "DIGIT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "':'", "'source'", "'test'", "'use'", "'add'", "'remove'", 
		"'list'", "'strain'", "'end'", "'mutate'", "'to'", "'report'", "'last'", 
		"'all'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "COLON", "SOURCE", "TEST", "USE", "ADD", "REMOVE", "LIST", 
		"STRAIN", "END", "MUTATE", "TO", "REPORT", "LAST", "ALL", "ID", "FILEPATH", 
		"DIRNAME", "FILENAME", "WS", "COMMENT", "SYMBOL"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u00ca\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u0081"+
		"\n\21\f\21\16\21\u0084\13\21\3\22\3\22\3\22\7\22\u0089\n\22\f\22\16\22"+
		"\u008c\13\22\3\22\3\22\7\22\u0090\n\22\f\22\16\22\u0093\13\22\3\22\5\22"+
		"\u0096\n\22\3\23\3\23\3\23\6\23\u009b\n\23\r\23\16\23\u009c\3\23\3\23"+
		"\3\23\5\23\u00a2\n\23\5\23\u00a4\n\23\3\23\5\23\u00a7\n\23\3\24\3\24\3"+
		"\24\6\24\u00ac\n\24\r\24\16\24\u00ad\3\25\3\25\6\25\u00b2\n\25\r\25\16"+
		"\25\u00b3\3\25\3\25\3\26\3\26\7\26\u00ba\n\26\f\26\16\26\u00bd\13\26\3"+
		"\26\5\26\u00c0\n\26\3\27\6\27\u00c3\n\27\r\27\16\27\u00c4\3\30\3\30\3"+
		"\31\3\31\3\u00bb\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\2\61\2\3\2\b"+
		"\4\2\60\60aa\5\2\13\f\17\17\"\"\3\3\f\f\b\2\13\f\17\17\"\"%%..<<\4\2C"+
		"\\c|\3\2\62;\u00da\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t>\3\2\2\2\13C\3\2\2\2\r"+
		"G\3\2\2\2\17K\3\2\2\2\21R\3\2\2\2\23W\3\2\2\2\25^\3\2\2\2\27b\3\2\2\2"+
		"\31i\3\2\2\2\33l\3\2\2\2\35s\3\2\2\2\37x\3\2\2\2!|\3\2\2\2#\u0095\3\2"+
		"\2\2%\u00a3\3\2\2\2\'\u00ab\3\2\2\2)\u00b1\3\2\2\2+\u00b7\3\2\2\2-\u00c2"+
		"\3\2\2\2/\u00c6\3\2\2\2\61\u00c8\3\2\2\2\63\64\7.\2\2\64\4\3\2\2\2\65"+
		"\66\7<\2\2\66\6\3\2\2\2\678\7u\2\289\7q\2\29:\7w\2\2:;\7t\2\2;<\7e\2\2"+
		"<=\7g\2\2=\b\3\2\2\2>?\7v\2\2?@\7g\2\2@A\7u\2\2AB\7v\2\2B\n\3\2\2\2CD"+
		"\7w\2\2DE\7u\2\2EF\7g\2\2F\f\3\2\2\2GH\7c\2\2HI\7f\2\2IJ\7f\2\2J\16\3"+
		"\2\2\2KL\7t\2\2LM\7g\2\2MN\7o\2\2NO\7q\2\2OP\7x\2\2PQ\7g\2\2Q\20\3\2\2"+
		"\2RS\7n\2\2ST\7k\2\2TU\7u\2\2UV\7v\2\2V\22\3\2\2\2WX\7u\2\2XY\7v\2\2Y"+
		"Z\7t\2\2Z[\7c\2\2[\\\7k\2\2\\]\7p\2\2]\24\3\2\2\2^_\7g\2\2_`\7p\2\2`a"+
		"\7f\2\2a\26\3\2\2\2bc\7o\2\2cd\7w\2\2de\7v\2\2ef\7c\2\2fg\7v\2\2gh\7g"+
		"\2\2h\30\3\2\2\2ij\7v\2\2jk\7q\2\2k\32\3\2\2\2lm\7t\2\2mn\7g\2\2no\7r"+
		"\2\2op\7q\2\2pq\7t\2\2qr\7v\2\2r\34\3\2\2\2st\7n\2\2tu\7c\2\2uv\7u\2\2"+
		"vw\7v\2\2w\36\3\2\2\2xy\7c\2\2yz\7n\2\2z{\7n\2\2{ \3\2\2\2|\u0082\5/\30"+
		"\2}\u0081\5/\30\2~\u0081\5\61\31\2\177\u0081\7a\2\2\u0080}\3\2\2\2\u0080"+
		"~\3\2\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\"\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\5%\23\2"+
		"\u0086\u0087\7\61\2\2\u0087\u0089\3\2\2\2\u0088\u0085\3\2\2\2\u0089\u008c"+
		"\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008d\u0096\5%\23\2\u008e\u0090\5%\23\2\u008f\u008e\3\2"+
		"\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0096\5\'\24\2\u0095\u008a\3"+
		"\2\2\2\u0095\u0091\3\2\2\2\u0096$\3\2\2\2\u0097\u009b\5/\30\2\u0098\u009b"+
		"\5\61\31\2\u0099\u009b\7a\2\2\u009a\u0097\3\2\2\2\u009a\u0098\3\2\2\2"+
		"\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d"+
		"\3\2\2\2\u009d\u00a4\3\2\2\2\u009e\u00a2\7\60\2\2\u009f\u00a0\7\60\2\2"+
		"\u00a0\u00a2\7\60\2\2\u00a1\u009e\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4"+
		"\3\2\2\2\u00a3\u009a\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5"+
		"\u00a7\7\61\2\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7&\3\2\2\2"+
		"\u00a8\u00ac\5/\30\2\u00a9\u00ac\5\61\31\2\u00aa\u00ac\t\2\2\2\u00ab\u00a8"+
		"\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae(\3\2\2\2\u00af\u00b2\t\3\2\2"+
		"\u00b0\u00b2\5+\26\2\u00b1\u00af\3\2\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3"+
		"\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b6\b\25\2\2\u00b6*\3\2\2\2\u00b7\u00bb\7%\2\2\u00b8\u00ba\13\2\2\2"+
		"\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c0\t\4\2\2\u00bf"+
		"\u00be\3\2\2\2\u00c0,\3\2\2\2\u00c1\u00c3\n\5\2\2\u00c2\u00c1\3\2\2\2"+
		"\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5.\3"+
		"\2\2\2\u00c6\u00c7\t\6\2\2\u00c7\60\3\2\2\2\u00c8\u00c9\t\7\2\2\u00c9"+
		"\62\3\2\2\2\24\2\u0080\u0082\u008a\u0091\u0095\u009a\u009c\u00a1\u00a3"+
		"\u00a6\u00ab\u00ad\u00b1\u00b3\u00bb\u00bf\u00c4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}