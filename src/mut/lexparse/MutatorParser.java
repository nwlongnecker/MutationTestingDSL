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
		END=10, MUTATE=11, TO=12, ID=13, FILEPATH=14, DIRNAME=15, FILENAME=16, 
		WS=17, COMMENT=18, SYMBOL=19;
	public static final int
		RULE_mutFile = 0, RULE_command = 1, RULE_source = 2, RULE_test = 3, RULE_use = 4, 
		RULE_addSource = 5, RULE_removeSource = 6, RULE_addTest = 7, RULE_removeTest = 8, 
		RULE_listSource = 9, RULE_listTest = 10, RULE_strain = 11, RULE_mutate = 12, 
		RULE_idList = 13, RULE_symbolList = 14, RULE_fileList = 15;
	public static final String[] ruleNames = {
		"mutFile", "command", "source", "test", "use", "addSource", "removeSource", 
		"addTest", "removeTest", "listSource", "listTest", "strain", "mutate", 
		"idList", "symbolList", "fileList"
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
			setState(39);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SOURCE) | (1L << TEST) | (1L << USE) | (1L << ADD) | (1L << REMOVE) | (1L << LIST) | (1L << STRAIN) | (1L << MUTATE))) != 0)) {
					{
					{
					setState(32);
					command();
					}
					}
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
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
			setState(52);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				source();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				addSource();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(43);
				removeSource();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(44);
				listSource();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(45);
				test();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(46);
				addTest();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(47);
				removeTest();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(48);
				listTest();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(49);
				use();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(50);
				strain();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(51);
				mutate();
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
			setState(54);
			match(SOURCE);
			setState(55);
			match(COLON);
			setState(56);
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
			setState(58);
			match(TEST);
			setState(59);
			match(COLON);
			setState(60);
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
			setState(62);
			match(USE);
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
			setState(65);
			match(ADD);
			setState(66);
			match(SOURCE);
			setState(67);
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
			setState(69);
			match(REMOVE);
			setState(70);
			match(SOURCE);
			setState(71);
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
			setState(73);
			match(ADD);
			setState(74);
			match(TEST);
			setState(75);
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
			setState(77);
			match(REMOVE);
			setState(78);
			match(TEST);
			setState(79);
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
			setState(81);
			match(LIST);
			setState(82);
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
			setState(84);
			match(LIST);
			setState(85);
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
			setState(87);
			match(STRAIN);
			setState(88);
			match(ID);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89);
				mutate();
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MUTATE );
			setState(94);
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
			setState(96);
			match(MUTATE);
			setState(102);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(97);
				idList();
				}
				break;
			case SYMBOL:
				{
				setState(98);
				symbolList();
				setState(99);
				match(TO);
				setState(100);
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
		enterRule(_localctx, 26, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(ID);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(105);
				match(COMMA);
				setState(106);
				match(ID);
				}
				}
				setState(111);
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
		enterRule(_localctx, 28, RULE_symbolList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(SYMBOL);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(113);
				match(COMMA);
				setState(114);
				match(SYMBOL);
				}
				}
				setState(119);
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
		enterRule(_localctx, 30, RULE_fileList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(FILEPATH);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(121);
				match(COMMA);
				setState(122);
				match(FILEPATH);
				}
				}
				setState(127);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25\u0083\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2"+
		"$\n\2\f\2\16\2\'\13\2\3\2\5\2*\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\5\3\67\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\r\6\r]\n\r\r\r\16\r^\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16i\n\16\3\17\3\17\3\17\7\17n\n\17\f\17\16\17q\13\17"+
		"\3\20\3\20\3\20\7\20v\n\20\f\20\16\20y\13\20\3\21\3\21\3\21\7\21~\n\21"+
		"\f\21\16\21\u0081\13\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \2\2\u0083\2)\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\b<\3\2\2\2\n@\3\2\2"+
		"\2\fC\3\2\2\2\16G\3\2\2\2\20K\3\2\2\2\22O\3\2\2\2\24S\3\2\2\2\26V\3\2"+
		"\2\2\30Y\3\2\2\2\32b\3\2\2\2\34j\3\2\2\2\36r\3\2\2\2 z\3\2\2\2\"$\5\4"+
		"\3\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&*\3\2\2\2\'%\3\2\2\2(*"+
		"\7\2\2\3)%\3\2\2\2)(\3\2\2\2*\3\3\2\2\2+\67\5\6\4\2,\67\5\f\7\2-\67\5"+
		"\16\b\2.\67\5\24\13\2/\67\5\b\5\2\60\67\5\20\t\2\61\67\5\22\n\2\62\67"+
		"\5\26\f\2\63\67\5\n\6\2\64\67\5\30\r\2\65\67\5\32\16\2\66+\3\2\2\2\66"+
		",\3\2\2\2\66-\3\2\2\2\66.\3\2\2\2\66/\3\2\2\2\66\60\3\2\2\2\66\61\3\2"+
		"\2\2\66\62\3\2\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\5\3\2"+
		"\2\289\7\5\2\29:\7\4\2\2:;\5 \21\2;\7\3\2\2\2<=\7\6\2\2=>\7\4\2\2>?\5"+
		" \21\2?\t\3\2\2\2@A\7\7\2\2AB\5 \21\2B\13\3\2\2\2CD\7\b\2\2DE\7\5\2\2"+
		"EF\5 \21\2F\r\3\2\2\2GH\7\t\2\2HI\7\5\2\2IJ\5 \21\2J\17\3\2\2\2KL\7\b"+
		"\2\2LM\7\6\2\2MN\5 \21\2N\21\3\2\2\2OP\7\t\2\2PQ\7\6\2\2QR\5 \21\2R\23"+
		"\3\2\2\2ST\7\n\2\2TU\7\5\2\2U\25\3\2\2\2VW\7\n\2\2WX\7\6\2\2X\27\3\2\2"+
		"\2YZ\7\13\2\2Z\\\7\17\2\2[]\5\32\16\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2"+
		"^_\3\2\2\2_`\3\2\2\2`a\7\f\2\2a\31\3\2\2\2bh\7\r\2\2ci\5\34\17\2de\5\36"+
		"\20\2ef\7\16\2\2fg\5\36\20\2gi\3\2\2\2hc\3\2\2\2hd\3\2\2\2i\33\3\2\2\2"+
		"jo\7\17\2\2kl\7\3\2\2ln\7\17\2\2mk\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2"+
		"\2p\35\3\2\2\2qo\3\2\2\2rw\7\25\2\2st\7\3\2\2tv\7\25\2\2us\3\2\2\2vy\3"+
		"\2\2\2wu\3\2\2\2wx\3\2\2\2x\37\3\2\2\2yw\3\2\2\2z\177\7\20\2\2{|\7\3\2"+
		"\2|~\7\20\2\2}{\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2"+
		"\u0080!\3\2\2\2\u0081\177\3\2\2\2\n%)\66^how\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}