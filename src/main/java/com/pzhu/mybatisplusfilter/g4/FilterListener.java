// Generated from /Users/liupeng/dev/mybatis-plus-filter/src/main/java/com/pzhu/mybatisplusfilter/g4/Filter.g4 by ANTLR
// 4.10.1
package com.pzhu.mybatisplusfilter.g4;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FilterParser}.
 */
public interface FilterListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link FilterParser#filter}.
     * @param ctx the parse tree
     */
    void enterFilter(FilterParser.FilterContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#filter}.
     * @param ctx the parse tree
     */
    void exitFilter(FilterParser.FilterContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#expression}.
     * @param ctx the parse tree
     */
    void enterExpression(FilterParser.ExpressionContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#expression}.
     * @param ctx the parse tree
     */
    void exitExpression(FilterParser.ExpressionContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#factor}.
     * @param ctx the parse tree
     */
    void enterFactor(FilterParser.FactorContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#factor}.
     * @param ctx the parse tree
     */
    void exitFactor(FilterParser.FactorContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#term}.
     * @param ctx the parse tree
     */
    void enterTerm(FilterParser.TermContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#term}.
     * @param ctx the parse tree
     */
    void exitTerm(FilterParser.TermContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#simple}.
     * @param ctx the parse tree
     */
    void enterSimple(FilterParser.SimpleContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#simple}.
     * @param ctx the parse tree
     */
    void exitSimple(FilterParser.SimpleContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#restriction}.
     * @param ctx the parse tree
     */
    void enterRestriction(FilterParser.RestrictionContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#restriction}.
     * @param ctx the parse tree
     */
    void exitRestriction(FilterParser.RestrictionContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#comparator}.
     * @param ctx the parse tree
     */
    void enterComparator(FilterParser.ComparatorContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#comparator}.
     * @param ctx the parse tree
     */
    void exitComparator(FilterParser.ComparatorContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#isComparator}.
     * @param ctx the parse tree
     */
    void enterIsComparator(FilterParser.IsComparatorContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#isComparator}.
     * @param ctx the parse tree
     */
    void exitIsComparator(FilterParser.IsComparatorContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#composite}.
     * @param ctx the parse tree
     */
    void enterComposite(FilterParser.CompositeContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#composite}.
     * @param ctx the parse tree
     */
    void exitComposite(FilterParser.CompositeContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#value}.
     * @param ctx the parse tree
     */
    void enterValue(FilterParser.ValueContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#value}.
     * @param ctx the parse tree
     */
    void exitValue(FilterParser.ValueContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#string}.
     * @param ctx the parse tree
     */
    void enterString(FilterParser.StringContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#string}.
     * @param ctx the parse tree
     */
    void exitString(FilterParser.StringContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#field}.
     * @param ctx the parse tree
     */
    void enterField(FilterParser.FieldContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#field}.
     * @param ctx the parse tree
     */
    void exitField(FilterParser.FieldContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#number}.
     * @param ctx the parse tree
     */
    void enterNumber(FilterParser.NumberContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#number}.
     * @param ctx the parse tree
     */
    void exitNumber(FilterParser.NumberContext ctx);
    /**
     * Enter a parse tree produced by {@link FilterParser#connection}.
     * @param ctx the parse tree
     */
    void enterConnection(FilterParser.ConnectionContext ctx);
    /**
     * Exit a parse tree produced by {@link FilterParser#connection}.
     * @param ctx the parse tree
     */
    void exitConnection(FilterParser.ConnectionContext ctx);
}