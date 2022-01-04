// Generated from C:/Users/75073/IdeaProjects/mybatis-plus/spring-boot-mybatis-plus/src/main/java/com/convertlab/library/mybatisplus/plugins/search/g4\Filter.g4 by ANTLR 4.9.2
package com.pzhu.mybatisplusfilter.g4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FilterParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, NOT=3, LIKE=4, ISNULL=5, NOTNULL=6, CHINESE=7, MINUS=8, ID=9, 
		NUMBER=10, WS=11, DOT=12, COMMA=13, LPAREN=14, RPAREN=15, LESS_EQUALS=16, 
		LESS_THAN=17, GREATER_EQUALS=18, GREATER_THAN=19, NOT_EQUALS=20, EQUALS=21, 
		HAS=22, DQ_=23, SQ_=24, AND_=25, OR_=26, NOT_=27, TILDE_=28, VERTICAL_BAR_=29, 
		AMPERSAND_=30, DOLLAR_=31, YUAN_=32, SIGNED_LEFT_SHIFT_=33, SIGNED_RIGHT_SHIFT_=34, 
		CARET_=35, MOD_=36, PLUS_=37, ASTERISK_=38, SLASH_=39, BACKSLASH_=40, 
		DOT_ASTERISK_=41, SAFE_EQ_=42, DEQ_=43, POUND_=44, LBE_=45, RBE_=46, LBT_=47, 
		RBT_=48, BQ_=49, QUESTION_=50, AT_=51, SEMI_=52;
	public static final int
		RULE_filter = 0, RULE_expression = 1, RULE_factor = 2, RULE_term = 3, 
		RULE_simple = 4, RULE_restriction = 5, RULE_comparator = 6, RULE_isComparator = 7, 
		RULE_composite = 8, RULE_value = 9, RULE_string = 10, RULE_stringinfo = 11, 
		RULE_field = 12, RULE_number = 13, RULE_connection = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"filter", "expression", "factor", "term", "simple", "restriction", "comparator", 
			"isComparator", "composite", "value", "string", "stringinfo", "field", 
			"number", "connection"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'is NULL'", "'is NOT NULL'", null, "'-'", 
			null, null, null, "'.'", "','", "'('", "')'", "'<='", "'<'", "'>='", 
			"'>'", "'!='", "'='", "':'", "'\"'", "'''", "'&&'", "'||'", "'!'", "'~'", 
			"'|'", "'&'", "'$'", "'\u00A5'", "'<<'", "'>>'", "'^'", "'%'", "'+'", 
			"'*'", "'/'", "'\\'", "'.*'", "'<=>'", "'=='", "'#'", "'{'", "'}'", "'['", 
			"']'", "'`'", "'?'", "'@'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "NOT", "LIKE", "ISNULL", "NOTNULL", "CHINESE", "MINUS", 
			"ID", "NUMBER", "WS", "DOT", "COMMA", "LPAREN", "RPAREN", "LESS_EQUALS", 
			"LESS_THAN", "GREATER_EQUALS", "GREATER_THAN", "NOT_EQUALS", "EQUALS", 
			"HAS", "DQ_", "SQ_", "AND_", "OR_", "NOT_", "TILDE_", "VERTICAL_BAR_", 
			"AMPERSAND_", "DOLLAR_", "YUAN_", "SIGNED_LEFT_SHIFT_", "SIGNED_RIGHT_SHIFT_", 
			"CARET_", "MOD_", "PLUS_", "ASTERISK_", "SLASH_", "BACKSLASH_", "DOT_ASTERISK_", 
			"SAFE_EQ_", "DEQ_", "POUND_", "LBE_", "RBE_", "LBT_", "RBT_", "BQ_", 
			"QUESTION_", "AT_", "SEMI_"
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
	public String getGrammarFileName() { return "Filter.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FilterParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FilterContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_filter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << ID) | (1L << LPAREN))) != 0)) {
				{
				setState(30);
				expression();
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

	public static class ExpressionContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<ConnectionContext> connection() {
			return getRuleContexts(ConnectionContext.class);
		}
		public ConnectionContext connection(int i) {
			return getRuleContext(ConnectionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			factor();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(34);
				connection();
				setState(35);
				factor();
				}
				}
				setState(41);
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

	public static class FactorContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<ConnectionContext> connection() {
			return getRuleContexts(ConnectionContext.class);
		}
		public ConnectionContext connection(int i) {
			return getRuleContext(ConnectionContext.class,i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_factor);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			term();
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(43);
					connection();
					setState(44);
					term();
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class TermContext extends ParserRuleContext {
		public SimpleContext simple() {
			return getRuleContext(SimpleContext.class,0);
		}
		public TerminalNode NOT() { return getToken(FilterParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(FilterParser.MINUS, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==MINUS) {
				{
				setState(51);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(54);
			simple();
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

	public static class SimpleContext extends ParserRuleContext {
		public RestrictionContext restriction() {
			return getRuleContext(RestrictionContext.class,0);
		}
		public CompositeContext composite() {
			return getRuleContext(CompositeContext.class,0);
		}
		public SimpleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleContext simple() throws RecognitionException {
		SimpleContext _localctx = new SimpleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_simple);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				restriction();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				composite();
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

	public static class RestrictionContext extends ParserRuleContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public IsComparatorContext isComparator() {
			return getRuleContext(IsComparatorContext.class,0);
		}
		public RestrictionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restriction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterRestriction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitRestriction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitRestriction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestrictionContext restriction() throws RecognitionException {
		RestrictionContext _localctx = new RestrictionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_restriction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			field();
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LIKE:
			case LESS_EQUALS:
			case LESS_THAN:
			case GREATER_EQUALS:
			case GREATER_THAN:
			case NOT_EQUALS:
			case EQUALS:
			case HAS:
				{
				setState(61);
				comparator();
				setState(62);
				value();
				}
				break;
			case ISNULL:
			case NOTNULL:
				{
				setState(64);
				isComparator();
				}
				break;
			case EOF:
			case AND:
			case OR:
			case RPAREN:
				break;
			default:
				break;
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

	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode LESS_EQUALS() { return getToken(FilterParser.LESS_EQUALS, 0); }
		public TerminalNode LESS_THAN() { return getToken(FilterParser.LESS_THAN, 0); }
		public TerminalNode GREATER_EQUALS() { return getToken(FilterParser.GREATER_EQUALS, 0); }
		public TerminalNode GREATER_THAN() { return getToken(FilterParser.GREATER_THAN, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(FilterParser.NOT_EQUALS, 0); }
		public TerminalNode EQUALS() { return getToken(FilterParser.EQUALS, 0); }
		public TerminalNode HAS() { return getToken(FilterParser.HAS, 0); }
		public TerminalNode LIKE() { return getToken(FilterParser.LIKE, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LIKE) | (1L << LESS_EQUALS) | (1L << LESS_THAN) | (1L << GREATER_EQUALS) | (1L << GREATER_THAN) | (1L << NOT_EQUALS) | (1L << EQUALS) | (1L << HAS))) != 0)) ) {
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

	public static class IsComparatorContext extends ParserRuleContext {
		public TerminalNode ISNULL() { return getToken(FilterParser.ISNULL, 0); }
		public TerminalNode NOTNULL() { return getToken(FilterParser.NOTNULL, 0); }
		public IsComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isComparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterIsComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitIsComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitIsComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IsComparatorContext isComparator() throws RecognitionException {
		IsComparatorContext _localctx = new IsComparatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_isComparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if ( !(_la==ISNULL || _la==NOTNULL) ) {
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

	public static class CompositeContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(FilterParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(FilterParser.RPAREN, 0); }
		public CompositeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterComposite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitComposite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitComposite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompositeContext composite() throws RecognitionException {
		CompositeContext _localctx = new CompositeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_composite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(LPAREN);
			setState(72);
			expression();
			setState(73);
			match(RPAREN);
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

	public static class ValueContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				number();
				}
				break;
			case EOF:
			case AND:
			case OR:
			case RPAREN:
			case DQ_:
			case SQ_:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				string();
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

	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> SQ_() { return getTokens(FilterParser.SQ_); }
		public TerminalNode SQ_(int i) {
			return getToken(FilterParser.SQ_, i);
		}
		public StringinfoContext stringinfo() {
			return getRuleContext(StringinfoContext.class,0);
		}
		public List<TerminalNode> DQ_() { return getTokens(FilterParser.DQ_); }
		public TerminalNode DQ_(int i) {
			return getToken(FilterParser.DQ_, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_string);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
			case AND:
			case OR:
			case RPAREN:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case SQ_:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(SQ_);
				setState(81);
				stringinfo();
				setState(82);
				match(SQ_);
				}
				break;
			case DQ_:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				match(DQ_);
				setState(85);
				stringinfo();
				setState(86);
				match(DQ_);
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

	public static class StringinfoContext extends ParserRuleContext {
		public StringinfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringinfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterStringinfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitStringinfo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitStringinfo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringinfoContext stringinfo() throws RecognitionException {
		StringinfoContext _localctx = new StringinfoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stringinfo);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(90);
					matchWildcard();
					}
					} 
				}
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class FieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FilterParser.ID, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ID);
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(FilterParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(NUMBER);
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

	public static class ConnectionContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(FilterParser.OR, 0); }
		public TerminalNode AND() { return getToken(FilterParser.AND, 0); }
		public ConnectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).enterConnection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FilterListener ) ((FilterListener)listener).exitConnection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FilterVisitor ) return ((FilterVisitor<? extends T>)visitor).visitConnection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectionContext connection() throws RecognitionException {
		ConnectionContext _localctx = new ConnectionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_connection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66i\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\5\2\"\n\2\3\3\3\3\3\3"+
		"\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64\13"+
		"\4\3\5\5\5\67\n\5\3\5\3\5\3\6\3\6\5\6=\n\6\3\7\3\7\3\7\3\7\3\7\5\7D\n"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\5\13P\n\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\5\f[\n\f\3\r\7\r^\n\r\f\r\16\ra\13\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\20\3_\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36\2\6\4\2\5\5\n\n\4\2\6\6\22\30\3\2\7\b\3\2\3\4\2d\2!\3\2\2\2\4#\3\2"+
		"\2\2\6,\3\2\2\2\b\66\3\2\2\2\n<\3\2\2\2\f>\3\2\2\2\16E\3\2\2\2\20G\3\2"+
		"\2\2\22I\3\2\2\2\24O\3\2\2\2\26Z\3\2\2\2\30_\3\2\2\2\32b\3\2\2\2\34d\3"+
		"\2\2\2\36f\3\2\2\2 \"\5\4\3\2! \3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#)\5\6\4"+
		"\2$%\5\36\20\2%&\5\6\4\2&(\3\2\2\2\'$\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3"+
		"\2\2\2*\5\3\2\2\2+)\3\2\2\2,\62\5\b\5\2-.\5\36\20\2./\5\b\5\2/\61\3\2"+
		"\2\2\60-\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\7\3\2\2"+
		"\2\64\62\3\2\2\2\65\67\t\2\2\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3\2\2\2"+
		"89\5\n\6\29\t\3\2\2\2:=\5\f\7\2;=\5\22\n\2<:\3\2\2\2<;\3\2\2\2=\13\3\2"+
		"\2\2>C\5\32\16\2?@\5\16\b\2@A\5\24\13\2AD\3\2\2\2BD\5\20\t\2C?\3\2\2\2"+
		"CB\3\2\2\2CD\3\2\2\2D\r\3\2\2\2EF\t\3\2\2F\17\3\2\2\2GH\t\4\2\2H\21\3"+
		"\2\2\2IJ\7\20\2\2JK\5\4\3\2KL\7\21\2\2L\23\3\2\2\2MP\5\34\17\2NP\5\26"+
		"\f\2OM\3\2\2\2ON\3\2\2\2P\25\3\2\2\2Q[\3\2\2\2RS\7\32\2\2ST\5\30\r\2T"+
		"U\7\32\2\2U[\3\2\2\2VW\7\31\2\2WX\5\30\r\2XY\7\31\2\2Y[\3\2\2\2ZQ\3\2"+
		"\2\2ZR\3\2\2\2ZV\3\2\2\2[\27\3\2\2\2\\^\13\2\2\2]\\\3\2\2\2^a\3\2\2\2"+
		"_`\3\2\2\2_]\3\2\2\2`\31\3\2\2\2a_\3\2\2\2bc\7\13\2\2c\33\3\2\2\2de\7"+
		"\f\2\2e\35\3\2\2\2fg\t\5\2\2g\37\3\2\2\2\13!)\62\66<COZ_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}