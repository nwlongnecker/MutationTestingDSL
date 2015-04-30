// Generated from Mutator.g4 by ANTLR 4.5
package mut.lexparse;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MutatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, COLON=2, SOURCE=3, TEST=4, USE=5, ADD=6, REMOVE=7, LIST=8, STRAIN=9, 
		END=10, MUTATE=11, TO=12, REPORT=13, LAST=14, ALL=15, SURVIVED=16, KILLED=17, 
		STILLBORN=18, ID=19, FILEPATH=20, DIRNAME=21, FILENAME=22, WS=23, COMMENT=24, 
		SYMBOL=25;
	public static final int
		RULE_mutFile = 0, RULE_command = 1, RULE_source = 2, RULE_test = 3, RULE_use = 4, 
		RULE_addSource = 5, RULE_removeSource = 6, RULE_addTest = 7, RULE_removeTest = 8, 
		RULE_listSource = 9, RULE_listTest = 10, RULE_strain = 11, RULE_mutate = 12, 
		RULE_report = 13, RULE_idList = 14, RULE_symbolList = 15, RULE_fileList = 16;
	public static final String[] ruleNames = {
		"mutFile", "command", "source", "test", "use", "addSource", "removeSource", 
		"addTest", "removeTest", "listSource", "listTest", "strain", "mutate", 
		"report", "idList", "symbolList", "fileList"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "':'", "'source'", "'test'", "'use'", "'add'", "'remove'", 
		"'list'", "'strain'", "'end'", "'mutate'", "'to'", "'report'", "'last'", 
		"'all'", "'survived'", "'killed'", "'stillborn'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "COLON", "SOURCE", "TEST", "USE", "ADD", "REMOVE", "LIST", 
		"STRAIN", "END", "MUTATE", "TO", "REPORT", "LAST", "ALL", "SURVIVED", 
		"KILLED", "STILLBORN", "ID", "FILEPATH", "DIRNAME", "FILENAME", "WS", 
		"COMMENT", "SYMBOL"
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

	@Override
	public String getGrammarFileName() { return "Mutator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MutatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MutFileContext extends ParserRuleContext {
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public TerminalNode EOF() { return getToken(MutatorParser.EOF, 0); }
		public MutFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mutFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterMutFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitMutFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitMutFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MutFileContext mutFile() throws RecognitionException {
		MutFileContext _localctx = new MutFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_mutFile);
		int _la;
		try {
			setState(41);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SOURCE) | (1L << TEST) | (1L << USE) | (1L << ADD) | (1L << REMOVE) | (1L << LIST) | (1L << STRAIN) | (1L << MUTATE) | (1L << REPORT))) != 0)) {
					{
					{
					setState(34);
					command();
					}
					}
					setState(39);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public AddSourceContext addSource() {
			return getRuleContext(AddSourceContext.class,0);
		}
		public RemoveSourceContext removeSource() {
			return getRuleContext(RemoveSourceContext.class,0);
		}
		public ListSourceContext listSource() {
			return getRuleContext(ListSourceContext.class,0);
		}
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public AddTestContext addTest() {
			return getRuleContext(AddTestContext.class,0);
		}
		public RemoveTestContext removeTest() {
			return getRuleContext(RemoveTestContext.class,0);
		}
		public ListTestContext listTest() {
			return getRuleContext(ListTestContext.class,0);
		}
		public UseContext use() {
			return getRuleContext(UseContext.class,0);
		}
		public StrainContext strain() {
			return getRuleContext(StrainContext.class,0);
		}
		public MutateContext mutate() {
			return getRuleContext(MutateContext.class,0);
		}
		public ReportContext report() {
			return getRuleContext(ReportContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			setState(55);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				source();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				addSource();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				removeSource();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(46);
				listSource();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
				test();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(48);
				addTest();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(49);
				removeTest();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(50);
				listTest();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(51);
				use();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(52);
				strain();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(53);
				mutate();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(54);
				report();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SourceContext extends ParserRuleContext {
		public TerminalNode SOURCE() { return getToken(MutatorParser.SOURCE, 0); }
		public TerminalNode COLON() { return getToken(MutatorParser.COLON, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(SOURCE);
			setState(58);
			match(COLON);
			setState(59);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestContext extends ParserRuleContext {
		public TerminalNode TEST() { return getToken(MutatorParser.TEST, 0); }
		public TerminalNode COLON() { return getToken(MutatorParser.COLON, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_test);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(TEST);
			setState(62);
			match(COLON);
			setState(63);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UseContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(MutatorParser.USE, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public UseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterUse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitUse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitUse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseContext use() throws RecognitionException {
		UseContext _localctx = new UseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(USE);
			setState(66);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddSourceContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(MutatorParser.ADD, 0); }
		public TerminalNode SOURCE() { return getToken(MutatorParser.SOURCE, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public AddSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterAddSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitAddSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitAddSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddSourceContext addSource() throws RecognitionException {
		AddSourceContext _localctx = new AddSourceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_addSource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(ADD);
			setState(69);
			match(SOURCE);
			setState(70);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RemoveSourceContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(MutatorParser.REMOVE, 0); }
		public TerminalNode SOURCE() { return getToken(MutatorParser.SOURCE, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public RemoveSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterRemoveSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitRemoveSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitRemoveSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveSourceContext removeSource() throws RecognitionException {
		RemoveSourceContext _localctx = new RemoveSourceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_removeSource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(REMOVE);
			setState(73);
			match(SOURCE);
			setState(74);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddTestContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(MutatorParser.ADD, 0); }
		public TerminalNode TEST() { return getToken(MutatorParser.TEST, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public AddTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterAddTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitAddTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitAddTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddTestContext addTest() throws RecognitionException {
		AddTestContext _localctx = new AddTestContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_addTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(ADD);
			setState(77);
			match(TEST);
			setState(78);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RemoveTestContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(MutatorParser.REMOVE, 0); }
		public TerminalNode TEST() { return getToken(MutatorParser.TEST, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public RemoveTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterRemoveTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitRemoveTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitRemoveTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveTestContext removeTest() throws RecognitionException {
		RemoveTestContext _localctx = new RemoveTestContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_removeTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(REMOVE);
			setState(81);
			match(TEST);
			setState(82);
			fileList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListSourceContext extends ParserRuleContext {
		public TerminalNode LIST() { return getToken(MutatorParser.LIST, 0); }
		public TerminalNode SOURCE() { return getToken(MutatorParser.SOURCE, 0); }
		public ListSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterListSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitListSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitListSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListSourceContext listSource() throws RecognitionException {
		ListSourceContext _localctx = new ListSourceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_listSource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(LIST);
			setState(85);
			match(SOURCE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListTestContext extends ParserRuleContext {
		public TerminalNode LIST() { return getToken(MutatorParser.LIST, 0); }
		public TerminalNode TEST() { return getToken(MutatorParser.TEST, 0); }
		public ListTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterListTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitListTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitListTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListTestContext listTest() throws RecognitionException {
		ListTestContext _localctx = new ListTestContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_listTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(LIST);
			setState(88);
			match(TEST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrainContext extends ParserRuleContext {
		public TerminalNode STRAIN() { return getToken(MutatorParser.STRAIN, 0); }
		public TerminalNode ID() { return getToken(MutatorParser.ID, 0); }
		public TerminalNode END() { return getToken(MutatorParser.END, 0); }
		public List<MutateContext> mutate() {
			return getRuleContexts(MutateContext.class);
		}
		public MutateContext mutate(int i) {
			return getRuleContext(MutateContext.class,i);
		}
		public StrainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterStrain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitStrain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitStrain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrainContext strain() throws RecognitionException {
		StrainContext _localctx = new StrainContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_strain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(STRAIN);
			setState(91);
			match(ID);
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				mutate();
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MUTATE );
			setState(97);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MutateContext extends ParserRuleContext {
		public TerminalNode MUTATE() { return getToken(MutatorParser.MUTATE, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public List<SymbolListContext> symbolList() {
			return getRuleContexts(SymbolListContext.class);
		}
		public SymbolListContext symbolList(int i) {
			return getRuleContext(SymbolListContext.class,i);
		}
		public TerminalNode TO() { return getToken(MutatorParser.TO, 0); }
		public MutateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mutate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterMutate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitMutate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitMutate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MutateContext mutate() throws RecognitionException {
		MutateContext _localctx = new MutateContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_mutate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(MUTATE);
			setState(105);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(100);
				idList();
				}
				break;
			case SYMBOL:
				{
				setState(101);
				symbolList();
				setState(102);
				match(TO);
				setState(103);
				symbolList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReportContext extends ParserRuleContext {
		public TerminalNode REPORT() { return getToken(MutatorParser.REPORT, 0); }
		public TerminalNode LAST() { return getToken(MutatorParser.LAST, 0); }
		public TerminalNode ALL() { return getToken(MutatorParser.ALL, 0); }
		public FileListContext fileList() {
			return getRuleContext(FileListContext.class,0);
		}
		public TerminalNode SURVIVED() { return getToken(MutatorParser.SURVIVED, 0); }
		public TerminalNode KILLED() { return getToken(MutatorParser.KILLED, 0); }
		public TerminalNode STILLBORN() { return getToken(MutatorParser.STILLBORN, 0); }
		public ReportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_report; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterReport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitReport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitReport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReportContext report() throws RecognitionException {
		ReportContext _localctx = new ReportContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_report);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(REPORT);
			setState(108);
			_la = _input.LA(1);
			if ( !(_la==LAST || _la==ALL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(111);
			switch (_input.LA(1)) {
			case SURVIVED:
			case KILLED:
			case STILLBORN:
				{
				setState(109);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SURVIVED) | (1L << KILLED) | (1L << STILLBORN))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case FILEPATH:
			case SYMBOL:
				{
				setState(110);
				fileList();
				}
				break;
			case EOF:
			case SOURCE:
			case TEST:
			case USE:
			case ADD:
			case REMOVE:
			case LIST:
			case STRAIN:
			case MUTATE:
			case REPORT:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MutatorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MutatorParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MutatorParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MutatorParser.COMMA, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitIdList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitIdList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(ID);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(114);
				match(COMMA);
				setState(115);
				match(ID);
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolListContext extends ParserRuleContext {
		public List<TerminalNode> SYMBOL() { return getTokens(MutatorParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(MutatorParser.SYMBOL, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MutatorParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MutatorParser.COMMA, i);
		}
		public SymbolListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterSymbolList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitSymbolList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitSymbolList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolListContext symbolList() throws RecognitionException {
		SymbolListContext _localctx = new SymbolListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_symbolList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(SYMBOL);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(122);
				match(COMMA);
				setState(123);
				match(SYMBOL);
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FileListContext extends ParserRuleContext {
		public List<TerminalNode> FILEPATH() { return getTokens(MutatorParser.FILEPATH); }
		public TerminalNode FILEPATH(int i) {
			return getToken(MutatorParser.FILEPATH, i);
		}
		public List<TerminalNode> SYMBOL() { return getTokens(MutatorParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(MutatorParser.SYMBOL, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MutatorParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MutatorParser.COMMA, i);
		}
		public FileListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).enterFileList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MutatorListener ) ((MutatorListener)listener).exitFileList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MutatorVisitor ) return ((MutatorVisitor<? extends T>)visitor).visitFileList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileListContext fileList() throws RecognitionException {
		FileListContext _localctx = new FileListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fileList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_la = _input.LA(1);
			if ( !(_la==FILEPATH || _la==SYMBOL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(130);
				match(COMMA);
				setState(131);
				_la = _input.LA(1);
				if ( !(_la==FILEPATH || _la==SYMBOL) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\33\u008c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\7\2&\n\2\f\2\16\2)\13\2\3\2\5\2,\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3:\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\6\r`\n\r\r\r\16\ra\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16l\n\16\3\17\3\17\3\17\3\17\5\17r\n\17\3"+
		"\20\3\20\3\20\7\20w\n\20\f\20\16\20z\13\20\3\21\3\21\3\21\7\21\177\n\21"+
		"\f\21\16\21\u0082\13\21\3\22\3\22\3\22\7\22\u0087\n\22\f\22\16\22\u008a"+
		"\13\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\5\3\2\20"+
		"\21\3\2\22\24\4\2\26\26\33\33\u008e\2+\3\2\2\2\49\3\2\2\2\6;\3\2\2\2\b"+
		"?\3\2\2\2\nC\3\2\2\2\fF\3\2\2\2\16J\3\2\2\2\20N\3\2\2\2\22R\3\2\2\2\24"+
		"V\3\2\2\2\26Y\3\2\2\2\30\\\3\2\2\2\32e\3\2\2\2\34m\3\2\2\2\36s\3\2\2\2"+
		" {\3\2\2\2\"\u0083\3\2\2\2$&\5\4\3\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'"+
		"(\3\2\2\2(,\3\2\2\2)\'\3\2\2\2*,\7\2\2\3+\'\3\2\2\2+*\3\2\2\2,\3\3\2\2"+
		"\2-:\5\6\4\2.:\5\f\7\2/:\5\16\b\2\60:\5\24\13\2\61:\5\b\5\2\62:\5\20\t"+
		"\2\63:\5\22\n\2\64:\5\26\f\2\65:\5\n\6\2\66:\5\30\r\2\67:\5\32\16\28:"+
		"\5\34\17\29-\3\2\2\29.\3\2\2\29/\3\2\2\29\60\3\2\2\29\61\3\2\2\29\62\3"+
		"\2\2\29\63\3\2\2\29\64\3\2\2\29\65\3\2\2\29\66\3\2\2\29\67\3\2\2\298\3"+
		"\2\2\2:\5\3\2\2\2;<\7\5\2\2<=\7\4\2\2=>\5\"\22\2>\7\3\2\2\2?@\7\6\2\2"+
		"@A\7\4\2\2AB\5\"\22\2B\t\3\2\2\2CD\7\7\2\2DE\5\"\22\2E\13\3\2\2\2FG\7"+
		"\b\2\2GH\7\5\2\2HI\5\"\22\2I\r\3\2\2\2JK\7\t\2\2KL\7\5\2\2LM\5\"\22\2"+
		"M\17\3\2\2\2NO\7\b\2\2OP\7\6\2\2PQ\5\"\22\2Q\21\3\2\2\2RS\7\t\2\2ST\7"+
		"\6\2\2TU\5\"\22\2U\23\3\2\2\2VW\7\n\2\2WX\7\5\2\2X\25\3\2\2\2YZ\7\n\2"+
		"\2Z[\7\6\2\2[\27\3\2\2\2\\]\7\13\2\2]_\7\25\2\2^`\5\32\16\2_^\3\2\2\2"+
		"`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\7\f\2\2d\31\3\2\2\2ek\7\r\2"+
		"\2fl\5\36\20\2gh\5 \21\2hi\7\16\2\2ij\5 \21\2jl\3\2\2\2kf\3\2\2\2kg\3"+
		"\2\2\2l\33\3\2\2\2mn\7\17\2\2nq\t\2\2\2or\t\3\2\2pr\5\"\22\2qo\3\2\2\2"+
		"qp\3\2\2\2qr\3\2\2\2r\35\3\2\2\2sx\7\25\2\2tu\7\3\2\2uw\7\25\2\2vt\3\2"+
		"\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\37\3\2\2\2zx\3\2\2\2{\u0080\7\33\2"+
		"\2|}\7\3\2\2}\177\7\33\2\2~|\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081!\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0088\t"+
		"\4\2\2\u0084\u0085\7\3\2\2\u0085\u0087\t\4\2\2\u0086\u0084\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089#\3\2\2\2"+
		"\u008a\u0088\3\2\2\2\13\'+9akqx\u0080\u0088";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}