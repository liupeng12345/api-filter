// Generated from /Users/liupeng/dev/mybatis-plus-filter/src/main/java/com/pzhu/mybatisplusfilter/g4/Filter.g4 by ANTLR
// 4.10.1
package com.pzhu.filter.g4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FilterParser extends Parser {
    public static final int S_W = 1,
            E_W = 2,
            AND = 3,
            OR = 4,
            NOT = 5,
            LIKE = 6,
            NULL = 7,
            NOTNULL = 8,
            MINUS = 9,
            ID = 10,
            LPAREN = 11,
            RPAREN = 12,
            LESS_EQUALS = 13,
            LESS_THAN = 14,
            GREATER_EQUALS = 15,
            GREATER_THAN = 16,
            NOT_EQUALS = 17,
            EQUALS = 18,
            HAS = 19,
            DQ_ = 20,
            SQ_ = 21,
            STRING = 22,
            NUMBER = 23,
            WS = 24;
    public static final int RULE_filter = 0,
            RULE_expression = 1,
            RULE_factor = 2,
            RULE_term = 3,
            RULE_restriction = 4,
            RULE_comparator = 5,
            RULE_isComparator = 6,
            RULE_composite = 7,
            RULE_value = 8,
            RULE_string = 9,
            RULE_field = 10,
            RULE_number = 11,
            RULE_connection = 12;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\u0004\u0001\u0018K\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
                    + "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"
                    + "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"
                    + "\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"
                    + "\f\u0007\f\u0001\u0000\u0003\u0000\u001c\b\u0000\u0001\u0001\u0001\u0001"
                    + "\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"
                    + "%\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003*\b\u0003\u0001"
                    + "\u0003\u0001\u0003\u0003\u0003.\b\u0003\u0001\u0004\u0001\u0004\u0001"
                    + "\u0004\u0001\u0004\u0001\u0004\u0003\u00045\b\u0004\u0001\u0005\u0001"
                    + "\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"
                    + "\u0007\u0001\b\u0001\b\u0003\bA\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"
                    + "\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004"
                    + "\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0004\u0002\u0000"
                    + "\u0005\u0005\t\t\u0003\u0000\u0001\u0002\u0006\u0006\r\u0013\u0001\u0000"
                    + "\u0007\b\u0001\u0000\u0003\u0004D\u0000\u001b\u0001\u0000\u0000\u0000"
                    + "\u0002\u001d\u0001\u0000\u0000\u0000\u0004$\u0001\u0000\u0000\u0000\u0006"
                    + "-\u0001\u0000\u0000\u0000\b/\u0001\u0000\u0000\u0000\n6\u0001\u0000\u0000"
                    + "\u0000\f8\u0001\u0000\u0000\u0000\u000e:\u0001\u0000\u0000\u0000\u0010"
                    + "@\u0001\u0000\u0000\u0000\u0012B\u0001\u0000\u0000\u0000\u0014D\u0001"
                    + "\u0000\u0000\u0000\u0016F\u0001\u0000\u0000\u0000\u0018H\u0001\u0000\u0000"
                    + "\u0000\u001a\u001c\u0003\u0002\u0001\u0000\u001b\u001a\u0001\u0000\u0000"
                    + "\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u0001\u0001\u0000\u0000"
                    + "\u0000\u001d\u001e\u0003\u0004\u0002\u0000\u001e\u0003\u0001\u0000\u0000"
                    + "\u0000\u001f%\u0003\u0006\u0003\u0000 !\u0003\u0006\u0003\u0000!\"\u0003"
                    + "\u0018\f\u0000\"#\u0003\u0004\u0002\u0000#%\u0001\u0000\u0000\u0000$\u001f"
                    + "\u0001\u0000\u0000\u0000$ \u0001\u0000\u0000\u0000%\u0005\u0001\u0000"
                    + "\u0000\u0000&)\u0007\u0000\u0000\u0000\'*\u0003\u0014\n\u0000(*\u0003"
                    + "\u000e\u0007\u0000)\'\u0001\u0000\u0000\u0000)(\u0001\u0000\u0000\u0000"
                    + "*.\u0001\u0000\u0000\u0000+.\u0003\b\u0004\u0000,.\u0003\u000e\u0007\u0000"
                    + "-&\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-,\u0001\u0000\u0000"
                    + "\u0000.\u0007\u0001\u0000\u0000\u0000/4\u0003\u0014\n\u000001\u0003\n"
                    + "\u0005\u000012\u0003\u0010\b\u000025\u0001\u0000\u0000\u000035\u0003\f"
                    + "\u0006\u000040\u0001\u0000\u0000\u000043\u0001\u0000\u0000\u00005\t\u0001"
                    + "\u0000\u0000\u000067\u0007\u0001\u0000\u00007\u000b\u0001\u0000\u0000"
                    + "\u000089\u0007\u0002\u0000\u00009\r\u0001\u0000\u0000\u0000:;\u0005\u000b"
                    + "\u0000\u0000;<\u0003\u0002\u0001\u0000<=\u0005\f\u0000\u0000=\u000f\u0001"
                    + "\u0000\u0000\u0000>A\u0003\u0016\u000b\u0000?A\u0003\u0012\t\u0000@>\u0001"
                    + "\u0000\u0000\u0000@?\u0001\u0000\u0000\u0000A\u0011\u0001\u0000\u0000"
                    + "\u0000BC\u0005\u0016\u0000\u0000C\u0013\u0001\u0000\u0000\u0000DE\u0005"
                    + "\n\u0000\u0000E\u0015\u0001\u0000\u0000\u0000FG\u0005\u0017\u0000\u0000"
                    + "G\u0017\u0001\u0000\u0000\u0000HI\u0007\u0003\u0000\u0000I\u0019\u0001"
                    + "\u0000\u0000\u0000\u0006\u001b$)-4@";
    public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION);
    }

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

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public FilterParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[] {
                "com/pzhu/filter",
            "expression",
            "factor",
            "term",
            "restriction",
            "comparator",
            "isComparator",
            "composite",
            "value",
            "string",
            "field",
            "number",
            "connection"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[] {
            null, "'$sw'", "'$ew'", null, null, null, null, null, null, "'-'", null, "'('", "')'", "'<='", "'<'",
            "'>='", "'>'", "'!='", "'='", "':'", "'\"'", "'''"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[] {
            null,
            "S_W",
            "E_W",
            "AND",
            "OR",
            "NOT",
            "LIKE",
            "NULL",
            "NOTNULL",
            "MINUS",
            "ID",
            "LPAREN",
            "RPAREN",
            "LESS_EQUALS",
            "LESS_THAN",
            "GREATER_EQUALS",
            "GREATER_THAN",
            "NOT_EQUALS",
            "EQUALS",
            "HAS",
            "DQ_",
            "SQ_",
            "STRING",
            "NUMBER",
            "WS"
        };
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
    public String getGrammarFileName() {
        return "Filter.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final FilterContext filter() throws RecognitionException {
        FilterContext _localctx = new FilterContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_filter);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(27);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0
                        && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << ID) | (1L << LPAREN))) != 0)) {
                    {
                        setState(26);
                        expression();
                    }
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExpressionContext expression() throws RecognitionException {
        ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_expression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(29);
                factor();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FactorContext factor() throws RecognitionException {
        FactorContext _localctx = new FactorContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_factor);
        try {
            setState(36);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                    {
                        setState(31);
                        term();
                    }
                    break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                    {
                        setState(32);
                        term();
                        setState(33);
                        connection();
                        setState(34);
                        factor();
                    }
                    break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TermContext term() throws RecognitionException {
        TermContext _localctx = new TermContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_term);
        int _la;
        try {
            setState(45);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case NOT:
                case MINUS:
                    enterOuterAlt(_localctx, 1);
                    {
                        setState(38);
                        _la = _input.LA(1);
                        if (!(_la == NOT || _la == MINUS)) {
                            _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(41);
                        _errHandler.sync(this);
                        switch (_input.LA(1)) {
                            case ID:
                                {
                                    setState(39);
                                    field();
                                }
                                break;
                            case LPAREN:
                                {
                                    setState(40);
                                    composite();
                                }
                                break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    break;
                case ID:
                    enterOuterAlt(_localctx, 2);
                    {
                        setState(43);
                        restriction();
                    }
                    break;
                case LPAREN:
                    enterOuterAlt(_localctx, 3);
                    {
                        setState(44);
                        composite();
                    }
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final RestrictionContext restriction() throws RecognitionException {
        RestrictionContext _localctx = new RestrictionContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_restriction);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(47);
                field();
                setState(52);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case S_W:
                    case E_W:
                    case LIKE:
                    case LESS_EQUALS:
                    case LESS_THAN:
                    case GREATER_EQUALS:
                    case GREATER_THAN:
                    case NOT_EQUALS:
                    case EQUALS:
                    case HAS:
                        {
                            setState(48);
                            comparator();
                            setState(49);
                            value();
                        }
                        break;
                    case NULL:
                    case NOTNULL:
                        {
                            setState(51);
                            isComparator();
                        }
                        break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ComparatorContext comparator() throws RecognitionException {
        ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_comparator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(54);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0
                        && ((1L << _la)
                                        & ((1L << S_W)
                                                | (1L << E_W)
                                                | (1L << LIKE)
                                                | (1L << LESS_EQUALS)
                                                | (1L << LESS_THAN)
                                                | (1L << GREATER_EQUALS)
                                                | (1L << GREATER_THAN)
                                                | (1L << NOT_EQUALS)
                                                | (1L << EQUALS)
                                                | (1L << HAS)))
                                != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IsComparatorContext isComparator() throws RecognitionException {
        IsComparatorContext _localctx = new IsComparatorContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_isComparator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(56);
                _la = _input.LA(1);
                if (!(_la == NULL || _la == NOTNULL)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CompositeContext composite() throws RecognitionException {
        CompositeContext _localctx = new CompositeContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_composite);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(58);
                match(LPAREN);
                setState(59);
                expression();
                setState(60);
                match(RPAREN);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ValueContext value() throws RecognitionException {
        ValueContext _localctx = new ValueContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_value);
        try {
            setState(64);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case NUMBER:
                    enterOuterAlt(_localctx, 1);
                    {
                        setState(62);
                        number();
                    }
                    break;
                case STRING:
                    enterOuterAlt(_localctx, 2);
                    {
                        setState(63);
                        string();
                    }
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StringContext string() throws RecognitionException {
        StringContext _localctx = new StringContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_string);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(66);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FieldContext field() throws RecognitionException {
        FieldContext _localctx = new FieldContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_field);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(68);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final NumberContext number() throws RecognitionException {
        NumberContext _localctx = new NumberContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_number);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(70);
                match(NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ConnectionContext connection() throws RecognitionException {
        ConnectionContext _localctx = new ConnectionContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_connection);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(72);
                _la = _input.LA(1);
                if (!(_la == AND || _la == OR)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FilterContext extends ParserRuleContext {
        public FilterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_filter;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterFilter(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitFilter(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitFilter(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ExpressionContext extends ParserRuleContext {
        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public FactorContext factor() {
            return getRuleContext(FactorContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitExpression(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class FactorContext extends ParserRuleContext {
        public FactorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TermContext term() {
            return getRuleContext(TermContext.class, 0);
        }

        public ConnectionContext connection() {
            return getRuleContext(ConnectionContext.class, 0);
        }

        public FactorContext factor() {
            return getRuleContext(FactorContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_factor;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterFactor(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitFactor(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitFactor(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class TermContext extends ParserRuleContext {
        public TermContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode NOT() {
            return getToken(FilterParser.NOT, 0);
        }

        public TerminalNode MINUS() {
            return getToken(FilterParser.MINUS, 0);
        }

        public FieldContext field() {
            return getRuleContext(FieldContext.class, 0);
        }

        public CompositeContext composite() {
            return getRuleContext(CompositeContext.class, 0);
        }

        public RestrictionContext restriction() {
            return getRuleContext(RestrictionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_term;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterTerm(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitTerm(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitTerm(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class RestrictionContext extends ParserRuleContext {
        public RestrictionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public FieldContext field() {
            return getRuleContext(FieldContext.class, 0);
        }

        public ComparatorContext comparator() {
            return getRuleContext(ComparatorContext.class, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public IsComparatorContext isComparator() {
            return getRuleContext(IsComparatorContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_restriction;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterRestriction(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitRestriction(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitRestriction(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ComparatorContext extends ParserRuleContext {
        public ComparatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode LESS_EQUALS() {
            return getToken(FilterParser.LESS_EQUALS, 0);
        }

        public TerminalNode LESS_THAN() {
            return getToken(FilterParser.LESS_THAN, 0);
        }

        public TerminalNode GREATER_EQUALS() {
            return getToken(FilterParser.GREATER_EQUALS, 0);
        }

        public TerminalNode GREATER_THAN() {
            return getToken(FilterParser.GREATER_THAN, 0);
        }

        public TerminalNode NOT_EQUALS() {
            return getToken(FilterParser.NOT_EQUALS, 0);
        }

        public TerminalNode EQUALS() {
            return getToken(FilterParser.EQUALS, 0);
        }

        public TerminalNode HAS() {
            return getToken(FilterParser.HAS, 0);
        }

        public TerminalNode LIKE() {
            return getToken(FilterParser.LIKE, 0);
        }

        public TerminalNode S_W() {
            return getToken(FilterParser.S_W, 0);
        }

        public TerminalNode E_W() {
            return getToken(FilterParser.E_W, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterComparator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitComparator(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitComparator(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class IsComparatorContext extends ParserRuleContext {
        public IsComparatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode NULL() {
            return getToken(FilterParser.NULL, 0);
        }

        public TerminalNode NOTNULL() {
            return getToken(FilterParser.NOTNULL, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_isComparator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterIsComparator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitIsComparator(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitIsComparator(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class CompositeContext extends ParserRuleContext {
        public CompositeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode LPAREN() {
            return getToken(FilterParser.LPAREN, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(FilterParser.RPAREN, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_composite;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterComposite(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitComposite(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitComposite(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ValueContext extends ParserRuleContext {
        public ValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public NumberContext number() {
            return getRuleContext(NumberContext.class, 0);
        }

        public StringContext string() {
            return getRuleContext(StringContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_value;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitValue(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class StringContext extends ParserRuleContext {
        public StringContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(FilterParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_string;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterString(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitString(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitString(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class FieldContext extends ParserRuleContext {
        public FieldContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ID() {
            return getToken(FilterParser.ID, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_field;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterField(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitField(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitField(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NumberContext extends ParserRuleContext {
        public NumberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode NUMBER() {
            return getToken(FilterParser.NUMBER, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_number;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterNumber(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitNumber(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitNumber(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ConnectionContext extends ParserRuleContext {
        public ConnectionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OR() {
            return getToken(FilterParser.OR, 0);
        }

        public TerminalNode AND() {
            return getToken(FilterParser.AND, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_connection;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).enterConnection(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FilterListener) ((FilterListener) listener).exitConnection(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof FilterVisitor) return ((FilterVisitor<? extends T>) visitor).visitConnection(this);
            else return visitor.visitChildren(this);
        }
    }
}