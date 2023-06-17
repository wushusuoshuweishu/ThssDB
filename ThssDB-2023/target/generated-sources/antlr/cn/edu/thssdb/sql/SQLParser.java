// Generated from SQL.g4 by ANTLR 4.8

package cn.edu.thssdb.sql;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, EQ=6, NE=7, LT=8, GT=9, LE=10, 
		GE=11, ADD=12, SUB=13, MUL=14, DIV=15, AND=16, OR=17, T_INT=18, T_LONG=19, 
		T_FLOAT=20, T_DOUBLE=21, T_STRING=22, K_ADD=23, K_ALL=24, K_AS=25, K_BY=26, 
		K_COLUMN=27, K_CREATE=28, K_DATABASE=29, K_DATABASES=30, K_DELETE=31, 
		K_DISTINCT=32, K_DROP=33, K_EXISTS=34, K_FROM=35, K_GRANT=36, K_IF=37, 
		K_IDENTIFIED=38, K_INSERT=39, K_INTO=40, K_JOIN=41, K_KEY=42, K_NOT=43, 
		K_NULL=44, K_ON=45, K_PRIMARY=46, K_QUIT=47, K_REVOKE=48, K_SELECT=49, 
		K_SET=50, K_SHOW=51, K_TABLE=52, K_TO=53, K_UPDATE=54, K_USE=55, K_USER=56, 
		K_VALUES=57, K_VIEW=58, K_WHERE=59, K_BEGIN=60, K_COMMIT=61, K_TRANSACTION=62, 
		IDENTIFIER=63, NUMERIC_LITERAL=64, EXPONENT=65, STRING_LITERAL=66, SINGLE_LINE_COMMENT=67, 
		MULTILINE_COMMENT=68, SPACES=69;
	public static final int
		RULE_parse = 0, RULE_sqlStmtList = 1, RULE_sqlStmt = 2, RULE_beginTransactionStmt = 3, 
		RULE_commitStmt = 4, RULE_createDbStmt = 5, RULE_dropDbStmt = 6, RULE_createUserStmt = 7, 
		RULE_dropUserStmt = 8, RULE_createTableStmt = 9, RULE_grantStmt = 10, 
		RULE_revokeStmt = 11, RULE_useDbStmt = 12, RULE_deleteStmt = 13, RULE_dropTableStmt = 14, 
		RULE_showDbStmt = 15, RULE_quitStmt = 16, RULE_showTableStmt = 17, RULE_insertStmt = 18, 
		RULE_valueEntry = 19, RULE_selectStmt = 20, RULE_createViewStmt = 21, 
		RULE_dropViewStmt = 22, RULE_updateStmt = 23, RULE_columnDef = 24, RULE_typeName = 25, 
		RULE_columnConstraint = 26, RULE_multipleCondition = 27, RULE_condition = 28, 
		RULE_comparer = 29, RULE_comparator = 30, RULE_expression = 31, RULE_tableConstraint = 32, 
		RULE_resultColumn = 33, RULE_tableQuery = 34, RULE_authLevel = 35, RULE_literalValue = 36, 
		RULE_columnFullName = 37, RULE_databaseName = 38, RULE_tableName = 39, 
		RULE_userName = 40, RULE_columnName = 41, RULE_viewName = 42, RULE_password = 43;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "sqlStmtList", "sqlStmt", "beginTransactionStmt", "commitStmt", 
			"createDbStmt", "dropDbStmt", "createUserStmt", "dropUserStmt", "createTableStmt", 
			"grantStmt", "revokeStmt", "useDbStmt", "deleteStmt", "dropTableStmt", 
			"showDbStmt", "quitStmt", "showTableStmt", "insertStmt", "valueEntry", 
			"selectStmt", "createViewStmt", "dropViewStmt", "updateStmt", "columnDef", 
			"typeName", "columnConstraint", "multipleCondition", "condition", "comparer", 
			"comparator", "expression", "tableConstraint", "resultColumn", "tableQuery", 
			"authLevel", "literalValue", "columnFullName", "databaseName", "tableName", 
			"userName", "columnName", "viewName", "password"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'.'", "'='", "'<>'", "'<'", "'>'", 
			"'<='", "'>='", "'+'", "'-'", "'*'", "'/'", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "EQ", "NE", "LT", "GT", "LE", "GE", 
			"ADD", "SUB", "MUL", "DIV", "AND", "OR", "T_INT", "T_LONG", "T_FLOAT", 
			"T_DOUBLE", "T_STRING", "K_ADD", "K_ALL", "K_AS", "K_BY", "K_COLUMN", 
			"K_CREATE", "K_DATABASE", "K_DATABASES", "K_DELETE", "K_DISTINCT", "K_DROP", 
			"K_EXISTS", "K_FROM", "K_GRANT", "K_IF", "K_IDENTIFIED", "K_INSERT", 
			"K_INTO", "K_JOIN", "K_KEY", "K_NOT", "K_NULL", "K_ON", "K_PRIMARY", 
			"K_QUIT", "K_REVOKE", "K_SELECT", "K_SET", "K_SHOW", "K_TABLE", "K_TO", 
			"K_UPDATE", "K_USE", "K_USER", "K_VALUES", "K_VIEW", "K_WHERE", "K_BEGIN", 
			"K_COMMIT", "K_TRANSACTION", "IDENTIFIER", "NUMERIC_LITERAL", "EXPONENT", 
			"STRING_LITERAL", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT", "SPACES"
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

	@Override
	public String getGrammarFileName() { return "SQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public SqlStmtListContext sqlStmtList() {
			return getRuleContext(SqlStmtListContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			sqlStmtList();
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

	public static class SqlStmtListContext extends ParserRuleContext {
		public List<SqlStmtContext> sqlStmt() {
			return getRuleContexts(SqlStmtContext.class);
		}
		public SqlStmtContext sqlStmt(int i) {
			return getRuleContext(SqlStmtContext.class,i);
		}
		public SqlStmtListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStmtList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSqlStmtList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSqlStmtList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSqlStmtList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStmtListContext sqlStmtList() throws RecognitionException {
		SqlStmtListContext _localctx = new SqlStmtListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sqlStmtList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(90);
				match(T__0);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			sqlStmt();
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(98); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(97);
						match(T__0);
						}
						}
						setState(100); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(102);
					sqlStmt();
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(108);
				match(T__0);
				}
				}
				setState(113);
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

	public static class SqlStmtContext extends ParserRuleContext {
		public CreateTableStmtContext createTableStmt() {
			return getRuleContext(CreateTableStmtContext.class,0);
		}
		public CreateDbStmtContext createDbStmt() {
			return getRuleContext(CreateDbStmtContext.class,0);
		}
		public CreateUserStmtContext createUserStmt() {
			return getRuleContext(CreateUserStmtContext.class,0);
		}
		public DropDbStmtContext dropDbStmt() {
			return getRuleContext(DropDbStmtContext.class,0);
		}
		public DropUserStmtContext dropUserStmt() {
			return getRuleContext(DropUserStmtContext.class,0);
		}
		public DeleteStmtContext deleteStmt() {
			return getRuleContext(DeleteStmtContext.class,0);
		}
		public DropTableStmtContext dropTableStmt() {
			return getRuleContext(DropTableStmtContext.class,0);
		}
		public InsertStmtContext insertStmt() {
			return getRuleContext(InsertStmtContext.class,0);
		}
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public CreateViewStmtContext createViewStmt() {
			return getRuleContext(CreateViewStmtContext.class,0);
		}
		public DropViewStmtContext dropViewStmt() {
			return getRuleContext(DropViewStmtContext.class,0);
		}
		public GrantStmtContext grantStmt() {
			return getRuleContext(GrantStmtContext.class,0);
		}
		public RevokeStmtContext revokeStmt() {
			return getRuleContext(RevokeStmtContext.class,0);
		}
		public UseDbStmtContext useDbStmt() {
			return getRuleContext(UseDbStmtContext.class,0);
		}
		public ShowDbStmtContext showDbStmt() {
			return getRuleContext(ShowDbStmtContext.class,0);
		}
		public ShowTableStmtContext showTableStmt() {
			return getRuleContext(ShowTableStmtContext.class,0);
		}
		public QuitStmtContext quitStmt() {
			return getRuleContext(QuitStmtContext.class,0);
		}
		public UpdateStmtContext updateStmt() {
			return getRuleContext(UpdateStmtContext.class,0);
		}
		public BeginTransactionStmtContext beginTransactionStmt() {
			return getRuleContext(BeginTransactionStmtContext.class,0);
		}
		public CommitStmtContext commitStmt() {
			return getRuleContext(CommitStmtContext.class,0);
		}
		public SqlStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSqlStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSqlStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSqlStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStmtContext sqlStmt() throws RecognitionException {
		SqlStmtContext _localctx = new SqlStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sqlStmt);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				createTableStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				createDbStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				createUserStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				dropDbStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(118);
				dropUserStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(119);
				deleteStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(120);
				dropTableStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(121);
				insertStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(122);
				selectStmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(123);
				createViewStmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(124);
				dropViewStmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(125);
				grantStmt();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(126);
				revokeStmt();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(127);
				useDbStmt();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(128);
				showDbStmt();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(129);
				showTableStmt();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(130);
				quitStmt();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(131);
				updateStmt();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(132);
				beginTransactionStmt();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(133);
				commitStmt();
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

	public static class BeginTransactionStmtContext extends ParserRuleContext {
		public TerminalNode K_BEGIN() { return getToken(SQLParser.K_BEGIN, 0); }
		public TerminalNode K_TRANSACTION() { return getToken(SQLParser.K_TRANSACTION, 0); }
		public BeginTransactionStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginTransactionStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterBeginTransactionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitBeginTransactionStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitBeginTransactionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeginTransactionStmtContext beginTransactionStmt() throws RecognitionException {
		BeginTransactionStmtContext _localctx = new BeginTransactionStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_beginTransactionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(K_BEGIN);
			setState(137);
			match(K_TRANSACTION);
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

	public static class CommitStmtContext extends ParserRuleContext {
		public TerminalNode K_COMMIT() { return getToken(SQLParser.K_COMMIT, 0); }
		public CommitStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commitStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCommitStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCommitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCommitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommitStmtContext commitStmt() throws RecognitionException {
		CommitStmtContext _localctx = new CommitStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_commitStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(K_COMMIT);
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

	public static class CreateDbStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public CreateDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateDbStmtContext createDbStmt() throws RecognitionException {
		CreateDbStmtContext _localctx = new CreateDbStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_createDbStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(K_CREATE);
			setState(142);
			match(K_DATABASE);
			setState(143);
			databaseName();
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

	public static class DropDbStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDbStmtContext dropDbStmt() throws RecognitionException {
		DropDbStmtContext _localctx = new DropDbStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dropDbStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(K_DROP);
			setState(146);
			match(K_DATABASE);
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(147);
				match(K_IF);
				setState(148);
				match(K_EXISTS);
				}
			}

			setState(151);
			databaseName();
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

	public static class CreateUserStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_USER() { return getToken(SQLParser.K_USER, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public TerminalNode K_IDENTIFIED() { return getToken(SQLParser.K_IDENTIFIED, 0); }
		public TerminalNode K_BY() { return getToken(SQLParser.K_BY, 0); }
		public PasswordContext password() {
			return getRuleContext(PasswordContext.class,0);
		}
		public CreateUserStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createUserStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateUserStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateUserStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateUserStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateUserStmtContext createUserStmt() throws RecognitionException {
		CreateUserStmtContext _localctx = new CreateUserStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_createUserStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(K_CREATE);
			setState(154);
			match(K_USER);
			setState(155);
			userName();
			setState(156);
			match(K_IDENTIFIED);
			setState(157);
			match(K_BY);
			setState(158);
			password();
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

	public static class DropUserStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_USER() { return getToken(SQLParser.K_USER, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropUserStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropUserStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropUserStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropUserStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropUserStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropUserStmtContext dropUserStmt() throws RecognitionException {
		DropUserStmtContext _localctx = new DropUserStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_dropUserStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(K_DROP);
			setState(161);
			match(K_USER);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(162);
				match(K_IF);
				setState(163);
				match(K_EXISTS);
				}
			}

			setState(166);
			userName();
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

	public static class CreateTableStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<ColumnDefContext> columnDef() {
			return getRuleContexts(ColumnDefContext.class);
		}
		public ColumnDefContext columnDef(int i) {
			return getRuleContext(ColumnDefContext.class,i);
		}
		public TableConstraintContext tableConstraint() {
			return getRuleContext(TableConstraintContext.class,0);
		}
		public CreateTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateTableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateTableStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableStmtContext createTableStmt() throws RecognitionException {
		CreateTableStmtContext _localctx = new CreateTableStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_createTableStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(K_CREATE);
			setState(169);
			match(K_TABLE);
			setState(170);
			tableName();
			setState(171);
			match(T__1);
			setState(172);
			columnDef();
			setState(177);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(173);
					match(T__2);
					setState(174);
					columnDef();
					}
					} 
				}
				setState(179);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(180);
				match(T__2);
				setState(181);
				tableConstraint();
				}
			}

			setState(184);
			match(T__3);
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

	public static class GrantStmtContext extends ParserRuleContext {
		public TerminalNode K_GRANT() { return getToken(SQLParser.K_GRANT, 0); }
		public List<AuthLevelContext> authLevel() {
			return getRuleContexts(AuthLevelContext.class);
		}
		public AuthLevelContext authLevel(int i) {
			return getRuleContext(AuthLevelContext.class,i);
		}
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_TO() { return getToken(SQLParser.K_TO, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public GrantStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grantStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterGrantStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitGrantStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitGrantStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrantStmtContext grantStmt() throws RecognitionException {
		GrantStmtContext _localctx = new GrantStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_grantStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(K_GRANT);
			setState(187);
			authLevel();
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(188);
				match(T__2);
				setState(189);
				authLevel();
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(195);
			match(K_ON);
			setState(196);
			tableName();
			setState(197);
			match(K_TO);
			setState(198);
			userName();
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

	public static class RevokeStmtContext extends ParserRuleContext {
		public TerminalNode K_REVOKE() { return getToken(SQLParser.K_REVOKE, 0); }
		public List<AuthLevelContext> authLevel() {
			return getRuleContexts(AuthLevelContext.class);
		}
		public AuthLevelContext authLevel(int i) {
			return getRuleContext(AuthLevelContext.class,i);
		}
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public RevokeStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_revokeStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterRevokeStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitRevokeStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitRevokeStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RevokeStmtContext revokeStmt() throws RecognitionException {
		RevokeStmtContext _localctx = new RevokeStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_revokeStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(K_REVOKE);
			setState(201);
			authLevel();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(202);
				match(T__2);
				setState(203);
				authLevel();
				}
				}
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(209);
			match(K_ON);
			setState(210);
			tableName();
			setState(211);
			match(K_FROM);
			setState(212);
			userName();
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

	public static class UseDbStmtContext extends ParserRuleContext {
		public TerminalNode K_USE() { return getToken(SQLParser.K_USE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public UseDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUseDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUseDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUseDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseDbStmtContext useDbStmt() throws RecognitionException {
		UseDbStmtContext _localctx = new UseDbStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_useDbStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(K_USE);
			setState(215);
			databaseName();
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

	public static class DeleteStmtContext extends ParserRuleContext {
		public TerminalNode K_DELETE() { return getToken(SQLParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public DeleteStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDeleteStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDeleteStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDeleteStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStmtContext deleteStmt() throws RecognitionException {
		DeleteStmtContext _localctx = new DeleteStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_deleteStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(K_DELETE);
			setState(218);
			match(K_FROM);
			setState(219);
			tableName();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(220);
				match(K_WHERE);
				setState(221);
				multipleCondition(0);
				}
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

	public static class DropTableStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropTableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropTableStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableStmtContext dropTableStmt() throws RecognitionException {
		DropTableStmtContext _localctx = new DropTableStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_dropTableStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(K_DROP);
			setState(225);
			match(K_TABLE);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(226);
				match(K_IF);
				setState(227);
				match(K_EXISTS);
				}
			}

			setState(230);
			tableName();
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

	public static class ShowDbStmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_DATABASES() { return getToken(SQLParser.K_DATABASES, 0); }
		public ShowDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShowDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShowDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShowDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowDbStmtContext showDbStmt() throws RecognitionException {
		ShowDbStmtContext _localctx = new ShowDbStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_showDbStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(K_SHOW);
			setState(233);
			match(K_DATABASES);
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

	public static class QuitStmtContext extends ParserRuleContext {
		public TerminalNode K_QUIT() { return getToken(SQLParser.K_QUIT, 0); }
		public QuitStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quitStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterQuitStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitQuitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitQuitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuitStmtContext quitStmt() throws RecognitionException {
		QuitStmtContext _localctx = new QuitStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_quitStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(K_QUIT);
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

	public static class ShowTableStmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ShowTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShowTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShowTableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShowTableStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowTableStmtContext showTableStmt() throws RecognitionException {
		ShowTableStmtContext _localctx = new ShowTableStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_showTableStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(K_SHOW);
			setState(238);
			match(K_TABLE);
			setState(239);
			tableName();
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

	public static class InsertStmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(SQLParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(SQLParser.K_INTO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(SQLParser.K_VALUES, 0); }
		public List<ValueEntryContext> valueEntry() {
			return getRuleContexts(ValueEntryContext.class);
		}
		public ValueEntryContext valueEntry(int i) {
			return getRuleContext(ValueEntryContext.class,i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public InsertStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterInsertStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitInsertStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitInsertStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStmtContext insertStmt() throws RecognitionException {
		InsertStmtContext _localctx = new InsertStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_insertStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(K_INSERT);
			setState(242);
			match(K_INTO);
			setState(243);
			tableName();
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(244);
				match(T__1);
				setState(245);
				columnName();
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(246);
					match(T__2);
					setState(247);
					columnName();
					}
					}
					setState(252);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(253);
				match(T__3);
				}
			}

			setState(257);
			match(K_VALUES);
			setState(258);
			valueEntry();
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(259);
				match(T__2);
				setState(260);
				valueEntry();
				}
				}
				setState(265);
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

	public static class ValueEntryContext extends ParserRuleContext {
		public List<LiteralValueContext> literalValue() {
			return getRuleContexts(LiteralValueContext.class);
		}
		public LiteralValueContext literalValue(int i) {
			return getRuleContext(LiteralValueContext.class,i);
		}
		public ValueEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterValueEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitValueEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitValueEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueEntryContext valueEntry() throws RecognitionException {
		ValueEntryContext _localctx = new ValueEntryContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_valueEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__1);
			setState(267);
			literalValue();
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(268);
				match(T__2);
				setState(269);
				literalValue();
				}
				}
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(275);
			match(T__3);
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

	public static class SelectStmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SQLParser.K_SELECT, 0); }
		public List<ResultColumnContext> resultColumn() {
			return getRuleContexts(ResultColumnContext.class);
		}
		public ResultColumnContext resultColumn(int i) {
			return getRuleContext(ResultColumnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public List<TableQueryContext> tableQuery() {
			return getRuleContexts(TableQueryContext.class);
		}
		public TableQueryContext tableQuery(int i) {
			return getRuleContext(TableQueryContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(SQLParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(SQLParser.K_ALL, 0); }
		public SelectStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSelectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSelectStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSelectStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStmtContext selectStmt() throws RecognitionException {
		SelectStmtContext _localctx = new SelectStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_selectStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(K_SELECT);
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ALL || _la==K_DISTINCT) {
				{
				setState(278);
				_la = _input.LA(1);
				if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(281);
			resultColumn();
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(282);
				match(T__2);
				setState(283);
				resultColumn();
				}
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			match(K_FROM);
			setState(290);
			tableQuery();
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(291);
				match(T__2);
				setState(292);
				tableQuery();
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(298);
				match(K_WHERE);
				setState(299);
				multipleCondition(0);
				}
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

	public static class CreateViewStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_VIEW() { return getToken(SQLParser.K_VIEW, 0); }
		public ViewNameContext viewName() {
			return getRuleContext(ViewNameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(SQLParser.K_AS, 0); }
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public CreateViewStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createViewStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateViewStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateViewStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateViewStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateViewStmtContext createViewStmt() throws RecognitionException {
		CreateViewStmtContext _localctx = new CreateViewStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_createViewStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(K_CREATE);
			setState(303);
			match(K_VIEW);
			setState(304);
			viewName();
			setState(305);
			match(K_AS);
			setState(306);
			selectStmt();
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

	public static class DropViewStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_VIEW() { return getToken(SQLParser.K_VIEW, 0); }
		public ViewNameContext viewName() {
			return getRuleContext(ViewNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropViewStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropViewStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropViewStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropViewStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropViewStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropViewStmtContext dropViewStmt() throws RecognitionException {
		DropViewStmtContext _localctx = new DropViewStmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dropViewStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(K_DROP);
			setState(309);
			match(K_VIEW);
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(310);
				match(K_IF);
				setState(311);
				match(K_EXISTS);
				}
			}

			setState(314);
			viewName();
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

	public static class UpdateStmtContext extends ParserRuleContext {
		public TerminalNode K_UPDATE() { return getToken(SQLParser.K_UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_SET() { return getToken(SQLParser.K_SET, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public UpdateStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUpdateStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUpdateStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUpdateStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateStmtContext updateStmt() throws RecognitionException {
		UpdateStmtContext _localctx = new UpdateStmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_updateStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(K_UPDATE);
			setState(317);
			tableName();
			setState(318);
			match(K_SET);
			setState(319);
			columnName();
			setState(320);
			match(EQ);
			setState(321);
			expression(0);
			setState(324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(322);
				match(K_WHERE);
				setState(323);
				multipleCondition(0);
				}
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

	public static class ColumnDefContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<ColumnConstraintContext> columnConstraint() {
			return getRuleContexts(ColumnConstraintContext.class);
		}
		public ColumnConstraintContext columnConstraint(int i) {
			return getRuleContext(ColumnConstraintContext.class,i);
		}
		public ColumnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnDefContext columnDef() throws RecognitionException {
		ColumnDefContext _localctx = new ColumnDefContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_columnDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			columnName();
			setState(327);
			typeName();
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT || _la==K_PRIMARY) {
				{
				{
				setState(328);
				columnConstraint();
				}
				}
				setState(333);
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

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode T_INT() { return getToken(SQLParser.T_INT, 0); }
		public TerminalNode T_LONG() { return getToken(SQLParser.T_LONG, 0); }
		public TerminalNode T_FLOAT() { return getToken(SQLParser.T_FLOAT, 0); }
		public TerminalNode T_DOUBLE() { return getToken(SQLParser.T_DOUBLE, 0); }
		public TerminalNode T_STRING() { return getToken(SQLParser.T_STRING, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLParser.NUMERIC_LITERAL, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_typeName);
		try {
			setState(342);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				match(T_INT);
				}
				break;
			case T_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				match(T_LONG);
				}
				break;
			case T_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(336);
				match(T_FLOAT);
				}
				break;
			case T_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(337);
				match(T_DOUBLE);
				}
				break;
			case T_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(338);
				match(T_STRING);
				setState(339);
				match(T__1);
				setState(340);
				match(NUMERIC_LITERAL);
				setState(341);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ColumnConstraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(SQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(SQLParser.K_KEY, 0); }
		public TerminalNode K_NOT() { return getToken(SQLParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(SQLParser.K_NULL, 0); }
		public ColumnConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnConstraintContext columnConstraint() throws RecognitionException {
		ColumnConstraintContext _localctx = new ColumnConstraintContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_columnConstraint);
		try {
			setState(348);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				match(K_PRIMARY);
				setState(345);
				match(K_KEY);
				}
				break;
			case K_NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
				match(K_NOT);
				setState(347);
				match(K_NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class MultipleConditionContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<MultipleConditionContext> multipleCondition() {
			return getRuleContexts(MultipleConditionContext.class);
		}
		public MultipleConditionContext multipleCondition(int i) {
			return getRuleContext(MultipleConditionContext.class,i);
		}
		public TerminalNode AND() { return getToken(SQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQLParser.OR, 0); }
		public MultipleConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterMultipleCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitMultipleCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitMultipleCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleConditionContext multipleCondition() throws RecognitionException {
		return multipleCondition(0);
	}

	private MultipleConditionContext multipleCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultipleConditionContext _localctx = new MultipleConditionContext(_ctx, _parentState);
		MultipleConditionContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_multipleCondition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(351);
			condition();
			}
			_ctx.stop = _input.LT(-1);
			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(359);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new MultipleConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multipleCondition);
						setState(353);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(354);
						match(AND);
						setState(355);
						multipleCondition(3);
						}
						break;
					case 2:
						{
						_localctx = new MultipleConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multipleCondition);
						setState(356);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(357);
						match(OR);
						setState(358);
						multipleCondition(2);
						}
						break;
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			expression(0);
			setState(365);
			comparator();
			setState(366);
			expression(0);
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

	public static class ComparerContext extends ParserRuleContext {
		public ColumnFullNameContext columnFullName() {
			return getRuleContext(ColumnFullNameContext.class,0);
		}
		public LiteralValueContext literalValue() {
			return getRuleContext(LiteralValueContext.class,0);
		}
		public ComparerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterComparer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitComparer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitComparer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparerContext comparer() throws RecognitionException {
		ComparerContext _localctx = new ComparerContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_comparer);
		try {
			setState(370);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(368);
				columnFullName();
				}
				break;
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				literalValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public TerminalNode NE() { return getToken(SQLParser.NE, 0); }
		public TerminalNode LE() { return getToken(SQLParser.LE, 0); }
		public TerminalNode GE() { return getToken(SQLParser.GE, 0); }
		public TerminalNode LT() { return getToken(SQLParser.LT, 0); }
		public TerminalNode GT() { return getToken(SQLParser.GT, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NE) | (1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ComparerContext comparer() {
			return getRuleContext(ComparerContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(SQLParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(SQLParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(SQLParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(SQLParser.SUB, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_NULL:
			case IDENTIFIER:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				{
				setState(375);
				comparer();
				}
				break;
			case T__1:
				{
				setState(376);
				match(T__1);
				setState(377);
				expression(0);
				setState(378);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(390);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(388);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(382);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(383);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(384);
						expression(4);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(385);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(386);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(387);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(392);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(SQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(SQLParser.K_KEY, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TableConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTableConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTableConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTableConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableConstraintContext tableConstraint() throws RecognitionException {
		TableConstraintContext _localctx = new TableConstraintContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_tableConstraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(K_PRIMARY);
			setState(394);
			match(K_KEY);
			setState(395);
			match(T__1);
			setState(396);
			columnName();
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(397);
				match(T__2);
				setState(398);
				columnName();
				}
				}
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(404);
			match(T__3);
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

	public static class ResultColumnContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(SQLParser.MUL, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ColumnFullNameContext columnFullName() {
			return getRuleContext(ColumnFullNameContext.class,0);
		}
		public ResultColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultColumn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterResultColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitResultColumn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitResultColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResultColumnContext resultColumn() throws RecognitionException {
		ResultColumnContext _localctx = new ResultColumnContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_resultColumn);
		try {
			setState(412);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(MUL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(407);
				tableName();
				setState(408);
				match(T__4);
				setState(409);
				match(MUL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(411);
				columnFullName();
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

	public static class TableQueryContext extends ParserRuleContext {
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public List<TerminalNode> K_JOIN() { return getTokens(SQLParser.K_JOIN); }
		public TerminalNode K_JOIN(int i) {
			return getToken(SQLParser.K_JOIN, i);
		}
		public TableQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTableQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTableQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTableQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableQueryContext tableQuery() throws RecognitionException {
		TableQueryContext _localctx = new TableQueryContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_tableQuery);
		int _la;
		try {
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(414);
				tableName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(415);
				tableName();
				setState(418); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(416);
					match(K_JOIN);
					setState(417);
					tableName();
					}
					}
					setState(420); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_JOIN );
				setState(422);
				match(K_ON);
				setState(423);
				multipleCondition(0);
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

	public static class AuthLevelContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SQLParser.K_SELECT, 0); }
		public TerminalNode K_INSERT() { return getToken(SQLParser.K_INSERT, 0); }
		public TerminalNode K_UPDATE() { return getToken(SQLParser.K_UPDATE, 0); }
		public TerminalNode K_DELETE() { return getToken(SQLParser.K_DELETE, 0); }
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public AuthLevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_authLevel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterAuthLevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitAuthLevel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitAuthLevel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthLevelContext authLevel() throws RecognitionException {
		AuthLevelContext _localctx = new AuthLevelContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_authLevel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_DELETE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_UPDATE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class LiteralValueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SQLParser.STRING_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(SQLParser.K_NULL, 0); }
		public LiteralValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterLiteralValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitLiteralValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitLiteralValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralValueContext literalValue() throws RecognitionException {
		LiteralValueContext _localctx = new LiteralValueContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_literalValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_la = _input.LA(1);
			if ( !(((((_la - 44)) & ~0x3f) == 0 && ((1L << (_la - 44)) & ((1L << (K_NULL - 44)) | (1L << (NUMERIC_LITERAL - 44)) | (1L << (STRING_LITERAL - 44)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ColumnFullNameContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ColumnFullNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnFullName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnFullName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnFullName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnFullName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnFullNameContext columnFullName() throws RecognitionException {
		ColumnFullNameContext _localctx = new ColumnFullNameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_columnFullName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(431);
				tableName();
				setState(432);
				match(T__4);
				}
				break;
			}
			setState(436);
			columnName();
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

	public static class DatabaseNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public DatabaseNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_databaseName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDatabaseName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDatabaseName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDatabaseName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatabaseNameContext databaseName() throws RecognitionException {
		DatabaseNameContext _localctx = new DatabaseNameContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_databaseName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			match(IDENTIFIER);
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

	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			match(IDENTIFIER);
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

	public static class UserNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public UserNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUserName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUserName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUserName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserNameContext userName() throws RecognitionException {
		UserNameContext _localctx = new UserNameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_userName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(IDENTIFIER);
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

	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(IDENTIFIER);
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

	public static class ViewNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public ViewNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterViewName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitViewName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitViewName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewNameContext viewName() throws RecognitionException {
		ViewNameContext _localctx = new ViewNameContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_viewName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			match(IDENTIFIER);
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

	public static class PasswordContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(SQLParser.STRING_LITERAL, 0); }
		public PasswordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_password; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterPassword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitPassword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitPassword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PasswordContext password() throws RecognitionException {
		PasswordContext _localctx = new PasswordContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_password);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			match(STRING_LITERAL);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 27:
			return multipleCondition_sempred((MultipleConditionContext)_localctx, predIndex);
		case 31:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean multipleCondition_sempred(MultipleConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3G\u01c5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\3\7\3^\n\3\f\3\16\3a\13\3\3\3\3\3\6\3e\n\3\r\3\16"+
		"\3f\3\3\7\3j\n\3\f\3\16\3m\13\3\3\3\7\3p\n\3\f\3\16\3s\13\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\u0089\n\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b"+
		"\u0098\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n\u00a7"+
		"\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00b2\n\13\f\13\16"+
		"\13\u00b5\13\13\3\13\3\13\5\13\u00b9\n\13\3\13\3\13\3\f\3\f\3\f\3\f\7"+
		"\f\u00c1\n\f\f\f\16\f\u00c4\13\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7"+
		"\r\u00cf\n\r\f\r\16\r\u00d2\13\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u00e1\n\17\3\20\3\20\3\20\3\20\5\20\u00e7"+
		"\n\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\7\24\u00fb\n\24\f\24\16\24\u00fe\13\24\3\24"+
		"\3\24\5\24\u0102\n\24\3\24\3\24\3\24\3\24\7\24\u0108\n\24\f\24\16\24\u010b"+
		"\13\24\3\25\3\25\3\25\3\25\7\25\u0111\n\25\f\25\16\25\u0114\13\25\3\25"+
		"\3\25\3\26\3\26\5\26\u011a\n\26\3\26\3\26\3\26\7\26\u011f\n\26\f\26\16"+
		"\26\u0122\13\26\3\26\3\26\3\26\3\26\7\26\u0128\n\26\f\26\16\26\u012b\13"+
		"\26\3\26\3\26\5\26\u012f\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\5\30\u013b\n\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u0147\n\31\3\32\3\32\3\32\7\32\u014c\n\32\f\32\16\32\u014f"+
		"\13\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0159\n\33\3\34\3"+
		"\34\3\34\3\34\5\34\u015f\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\7\35\u016a\n\35\f\35\16\35\u016d\13\35\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\5\37\u0175\n\37\3 \3 \3!\3!\3!\3!\3!\3!\5!\u017f\n!\3!\3!\3!\3!"+
		"\3!\3!\7!\u0187\n!\f!\16!\u018a\13!\3\"\3\"\3\"\3\"\3\"\3\"\7\"\u0192"+
		"\n\"\f\"\16\"\u0195\13\"\3\"\3\"\3#\3#\3#\3#\3#\3#\5#\u019f\n#\3$\3$\3"+
		"$\3$\6$\u01a5\n$\r$\16$\u01a6\3$\3$\3$\5$\u01ac\n$\3%\3%\3&\3&\3\'\3\'"+
		"\3\'\5\'\u01b5\n\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3-\2\4"+
		"8@.\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B"+
		"DFHJLNPRTVX\2\b\4\2\32\32\"\"\3\2\b\r\3\2\20\21\3\2\16\17\7\2!!##))\63"+
		"\6388\5\2..BBDD\2\u01d3\2Z\3\2\2\2\4_\3\2\2\2\6\u0088\3\2\2\2\b\u008a"+
		"\3\2\2\2\n\u008d\3\2\2\2\f\u008f\3\2\2\2\16\u0093\3\2\2\2\20\u009b\3\2"+
		"\2\2\22\u00a2\3\2\2\2\24\u00aa\3\2\2\2\26\u00bc\3\2\2\2\30\u00ca\3\2\2"+
		"\2\32\u00d8\3\2\2\2\34\u00db\3\2\2\2\36\u00e2\3\2\2\2 \u00ea\3\2\2\2\""+
		"\u00ed\3\2\2\2$\u00ef\3\2\2\2&\u00f3\3\2\2\2(\u010c\3\2\2\2*\u0117\3\2"+
		"\2\2,\u0130\3\2\2\2.\u0136\3\2\2\2\60\u013e\3\2\2\2\62\u0148\3\2\2\2\64"+
		"\u0158\3\2\2\2\66\u015e\3\2\2\28\u0160\3\2\2\2:\u016e\3\2\2\2<\u0174\3"+
		"\2\2\2>\u0176\3\2\2\2@\u017e\3\2\2\2B\u018b\3\2\2\2D\u019e\3\2\2\2F\u01ab"+
		"\3\2\2\2H\u01ad\3\2\2\2J\u01af\3\2\2\2L\u01b4\3\2\2\2N\u01b8\3\2\2\2P"+
		"\u01ba\3\2\2\2R\u01bc\3\2\2\2T\u01be\3\2\2\2V\u01c0\3\2\2\2X\u01c2\3\2"+
		"\2\2Z[\5\4\3\2[\3\3\2\2\2\\^\7\3\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`"+
		"\3\2\2\2`b\3\2\2\2a_\3\2\2\2bk\5\6\4\2ce\7\3\2\2dc\3\2\2\2ef\3\2\2\2f"+
		"d\3\2\2\2fg\3\2\2\2gh\3\2\2\2hj\5\6\4\2id\3\2\2\2jm\3\2\2\2ki\3\2\2\2"+
		"kl\3\2\2\2lq\3\2\2\2mk\3\2\2\2np\7\3\2\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2"+
		"qr\3\2\2\2r\5\3\2\2\2sq\3\2\2\2t\u0089\5\24\13\2u\u0089\5\f\7\2v\u0089"+
		"\5\20\t\2w\u0089\5\16\b\2x\u0089\5\22\n\2y\u0089\5\34\17\2z\u0089\5\36"+
		"\20\2{\u0089\5&\24\2|\u0089\5*\26\2}\u0089\5,\27\2~\u0089\5.\30\2\177"+
		"\u0089\5\26\f\2\u0080\u0089\5\30\r\2\u0081\u0089\5\32\16\2\u0082\u0089"+
		"\5 \21\2\u0083\u0089\5$\23\2\u0084\u0089\5\"\22\2\u0085\u0089\5\60\31"+
		"\2\u0086\u0089\5\b\5\2\u0087\u0089\5\n\6\2\u0088t\3\2\2\2\u0088u\3\2\2"+
		"\2\u0088v\3\2\2\2\u0088w\3\2\2\2\u0088x\3\2\2\2\u0088y\3\2\2\2\u0088z"+
		"\3\2\2\2\u0088{\3\2\2\2\u0088|\3\2\2\2\u0088}\3\2\2\2\u0088~\3\2\2\2\u0088"+
		"\177\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0081\3\2\2\2\u0088\u0082\3\2\2"+
		"\2\u0088\u0083\3\2\2\2\u0088\u0084\3\2\2\2\u0088\u0085\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0088\u0087\3\2\2\2\u0089\7\3\2\2\2\u008a\u008b\7>\2\2\u008b"+
		"\u008c\7@\2\2\u008c\t\3\2\2\2\u008d\u008e\7?\2\2\u008e\13\3\2\2\2\u008f"+
		"\u0090\7\36\2\2\u0090\u0091\7\37\2\2\u0091\u0092\5N(\2\u0092\r\3\2\2\2"+
		"\u0093\u0094\7#\2\2\u0094\u0097\7\37\2\2\u0095\u0096\7\'\2\2\u0096\u0098"+
		"\7$\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009a\5N(\2\u009a\17\3\2\2\2\u009b\u009c\7\36\2\2\u009c\u009d\7:\2\2"+
		"\u009d\u009e\5R*\2\u009e\u009f\7(\2\2\u009f\u00a0\7\34\2\2\u00a0\u00a1"+
		"\5X-\2\u00a1\21\3\2\2\2\u00a2\u00a3\7#\2\2\u00a3\u00a6\7:\2\2\u00a4\u00a5"+
		"\7\'\2\2\u00a5\u00a7\7$\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\5R*\2\u00a9\23\3\2\2\2\u00aa\u00ab\7\36\2\2"+
		"\u00ab\u00ac\7\66\2\2\u00ac\u00ad\5P)\2\u00ad\u00ae\7\4\2\2\u00ae\u00b3"+
		"\5\62\32\2\u00af\u00b0\7\5\2\2\u00b0\u00b2\5\62\32\2\u00b1\u00af\3\2\2"+
		"\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b8"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\7\5\2\2\u00b7\u00b9\5B\"\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\7\6"+
		"\2\2\u00bb\25\3\2\2\2\u00bc\u00bd\7&\2\2\u00bd\u00c2\5H%\2\u00be\u00bf"+
		"\7\5\2\2\u00bf\u00c1\5H%\2\u00c0\u00be\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2"+
		"\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c5\u00c6\7/\2\2\u00c6\u00c7\5P)\2\u00c7\u00c8\7\67\2\2\u00c8\u00c9"+
		"\5R*\2\u00c9\27\3\2\2\2\u00ca\u00cb\7\62\2\2\u00cb\u00d0\5H%\2\u00cc\u00cd"+
		"\7\5\2\2\u00cd\u00cf\5H%\2\u00ce\u00cc\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d0\3\2"+
		"\2\2\u00d3\u00d4\7/\2\2\u00d4\u00d5\5P)\2\u00d5\u00d6\7%\2\2\u00d6\u00d7"+
		"\5R*\2\u00d7\31\3\2\2\2\u00d8\u00d9\79\2\2\u00d9\u00da\5N(\2\u00da\33"+
		"\3\2\2\2\u00db\u00dc\7!\2\2\u00dc\u00dd\7%\2\2\u00dd\u00e0\5P)\2\u00de"+
		"\u00df\7=\2\2\u00df\u00e1\58\35\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2"+
		"\2\2\u00e1\35\3\2\2\2\u00e2\u00e3\7#\2\2\u00e3\u00e6\7\66\2\2\u00e4\u00e5"+
		"\7\'\2\2\u00e5\u00e7\7$\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\u00e9\5P)\2\u00e9\37\3\2\2\2\u00ea\u00eb\7\65\2\2"+
		"\u00eb\u00ec\7 \2\2\u00ec!\3\2\2\2\u00ed\u00ee\7\61\2\2\u00ee#\3\2\2\2"+
		"\u00ef\u00f0\7\65\2\2\u00f0\u00f1\7\66\2\2\u00f1\u00f2\5P)\2\u00f2%\3"+
		"\2\2\2\u00f3\u00f4\7)\2\2\u00f4\u00f5\7*\2\2\u00f5\u0101\5P)\2\u00f6\u00f7"+
		"\7\4\2\2\u00f7\u00fc\5T+\2\u00f8\u00f9\7\5\2\2\u00f9\u00fb\5T+\2\u00fa"+
		"\u00f8\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2"+
		"\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0100\7\6\2\2\u0100"+
		"\u0102\3\2\2\2\u0101\u00f6\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0104\7;\2\2\u0104\u0109\5(\25\2\u0105\u0106\7\5\2\2\u0106"+
		"\u0108\5(\25\2\u0107\u0105\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2"+
		"\2\2\u0109\u010a\3\2\2\2\u010a\'\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d"+
		"\7\4\2\2\u010d\u0112\5J&\2\u010e\u010f\7\5\2\2\u010f\u0111\5J&\2\u0110"+
		"\u010e\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2"+
		"\2\2\u0113\u0115\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0116\7\6\2\2\u0116"+
		")\3\2\2\2\u0117\u0119\7\63\2\2\u0118\u011a\t\2\2\2\u0119\u0118\3\2\2\2"+
		"\u0119\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0120\5D#\2\u011c\u011d"+
		"\7\5\2\2\u011d\u011f\5D#\2\u011e\u011c\3\2\2\2\u011f\u0122\3\2\2\2\u0120"+
		"\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2"+
		"\2\2\u0123\u0124\7%\2\2\u0124\u0129\5F$\2\u0125\u0126\7\5\2\2\u0126\u0128"+
		"\5F$\2\u0127\u0125\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u012e\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d\7="+
		"\2\2\u012d\u012f\58\35\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"+\3\2\2\2\u0130\u0131\7\36\2\2\u0131\u0132\7<\2\2\u0132\u0133\5V,\2\u0133"+
		"\u0134\7\33\2\2\u0134\u0135\5*\26\2\u0135-\3\2\2\2\u0136\u0137\7#\2\2"+
		"\u0137\u013a\7<\2\2\u0138\u0139\7\'\2\2\u0139\u013b\7$\2\2\u013a\u0138"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\5V,\2\u013d"+
		"/\3\2\2\2\u013e\u013f\78\2\2\u013f\u0140\5P)\2\u0140\u0141\7\64\2\2\u0141"+
		"\u0142\5T+\2\u0142\u0143\7\b\2\2\u0143\u0146\5@!\2\u0144\u0145\7=\2\2"+
		"\u0145\u0147\58\35\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\61"+
		"\3\2\2\2\u0148\u0149\5T+\2\u0149\u014d\5\64\33\2\u014a\u014c\5\66\34\2"+
		"\u014b\u014a\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e"+
		"\3\2\2\2\u014e\63\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0159\7\24\2\2\u0151"+
		"\u0159\7\25\2\2\u0152\u0159\7\26\2\2\u0153\u0159\7\27\2\2\u0154\u0155"+
		"\7\30\2\2\u0155\u0156\7\4\2\2\u0156\u0157\7B\2\2\u0157\u0159\7\6\2\2\u0158"+
		"\u0150\3\2\2\2\u0158\u0151\3\2\2\2\u0158\u0152\3\2\2\2\u0158\u0153\3\2"+
		"\2\2\u0158\u0154\3\2\2\2\u0159\65\3\2\2\2\u015a\u015b\7\60\2\2\u015b\u015f"+
		"\7,\2\2\u015c\u015d\7-\2\2\u015d\u015f\7.\2\2\u015e\u015a\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015f\67\3\2\2\2\u0160\u0161\b\35\1\2\u0161\u0162\5:\36"+
		"\2\u0162\u016b\3\2\2\2\u0163\u0164\f\4\2\2\u0164\u0165\7\22\2\2\u0165"+
		"\u016a\58\35\5\u0166\u0167\f\3\2\2\u0167\u0168\7\23\2\2\u0168\u016a\5"+
		"8\35\4\u0169\u0163\3\2\2\2\u0169\u0166\3\2\2\2\u016a\u016d\3\2\2\2\u016b"+
		"\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c9\3\2\2\2\u016d\u016b\3\2\2\2"+
		"\u016e\u016f\5@!\2\u016f\u0170\5> \2\u0170\u0171\5@!\2\u0171;\3\2\2\2"+
		"\u0172\u0175\5L\'\2\u0173\u0175\5J&\2\u0174\u0172\3\2\2\2\u0174\u0173"+
		"\3\2\2\2\u0175=\3\2\2\2\u0176\u0177\t\3\2\2\u0177?\3\2\2\2\u0178\u0179"+
		"\b!\1\2\u0179\u017f\5<\37\2\u017a\u017b\7\4\2\2\u017b\u017c\5@!\2\u017c"+
		"\u017d\7\6\2\2\u017d\u017f\3\2\2\2\u017e\u0178\3\2\2\2\u017e\u017a\3\2"+
		"\2\2\u017f\u0188\3\2\2\2\u0180\u0181\f\5\2\2\u0181\u0182\t\4\2\2\u0182"+
		"\u0187\5@!\6\u0183\u0184\f\4\2\2\u0184\u0185\t\5\2\2\u0185\u0187\5@!\5"+
		"\u0186\u0180\3\2\2\2\u0186\u0183\3\2\2\2\u0187\u018a\3\2\2\2\u0188\u0186"+
		"\3\2\2\2\u0188\u0189\3\2\2\2\u0189A\3\2\2\2\u018a\u0188\3\2\2\2\u018b"+
		"\u018c\7\60\2\2\u018c\u018d\7,\2\2\u018d\u018e\7\4\2\2\u018e\u0193\5T"+
		"+\2\u018f\u0190\7\5\2\2\u0190\u0192\5T+\2\u0191\u018f\3\2\2\2\u0192\u0195"+
		"\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2\2\2\u0195"+
		"\u0193\3\2\2\2\u0196\u0197\7\6\2\2\u0197C\3\2\2\2\u0198\u019f\7\20\2\2"+
		"\u0199\u019a\5P)\2\u019a\u019b\7\7\2\2\u019b\u019c\7\20\2\2\u019c\u019f"+
		"\3\2\2\2\u019d\u019f\5L\'\2\u019e\u0198\3\2\2\2\u019e\u0199\3\2\2\2\u019e"+
		"\u019d\3\2\2\2\u019fE\3\2\2\2\u01a0\u01ac\5P)\2\u01a1\u01a4\5P)\2\u01a2"+
		"\u01a3\7+\2\2\u01a3\u01a5\5P)\2\u01a4\u01a2\3\2\2\2\u01a5\u01a6\3\2\2"+
		"\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01a9"+
		"\7/\2\2\u01a9\u01aa\58\35\2\u01aa\u01ac\3\2\2\2\u01ab\u01a0\3\2\2\2\u01ab"+
		"\u01a1\3\2\2\2\u01acG\3\2\2\2\u01ad\u01ae\t\6\2\2\u01aeI\3\2\2\2\u01af"+
		"\u01b0\t\7\2\2\u01b0K\3\2\2\2\u01b1\u01b2\5P)\2\u01b2\u01b3\7\7\2\2\u01b3"+
		"\u01b5\3\2\2\2\u01b4\u01b1\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\3\2"+
		"\2\2\u01b6\u01b7\5T+\2\u01b7M\3\2\2\2\u01b8\u01b9\7A\2\2\u01b9O\3\2\2"+
		"\2\u01ba\u01bb\7A\2\2\u01bbQ\3\2\2\2\u01bc\u01bd\7A\2\2\u01bdS\3\2\2\2"+
		"\u01be\u01bf\7A\2\2\u01bfU\3\2\2\2\u01c0\u01c1\7A\2\2\u01c1W\3\2\2\2\u01c2"+
		"\u01c3\7D\2\2\u01c3Y\3\2\2\2\'_fkq\u0088\u0097\u00a6\u00b3\u00b8\u00c2"+
		"\u00d0\u00e0\u00e6\u00fc\u0101\u0109\u0112\u0119\u0120\u0129\u012e\u013a"+
		"\u0146\u014d\u0158\u015e\u0169\u016b\u0174\u017e\u0186\u0188\u0193\u019e"+
		"\u01a6\u01ab\u01b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}