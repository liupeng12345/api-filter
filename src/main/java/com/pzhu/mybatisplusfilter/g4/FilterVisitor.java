// Generated from /Users/liupeng/dev/mybatis-plus-filter/src/main/java/com/pzhu/mybatisplusfilter/g4/Filter.g4 by ANTLR
// 4.10.1
package com.pzhu.mybatisplusfilter.g4;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FilterParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FilterVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link FilterParser#filter}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFilter(FilterParser.FilterContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#expression}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitExpression(FilterParser.ExpressionContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#factor}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFactor(FilterParser.FactorContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#term}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitTerm(FilterParser.TermContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#simple}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSimple(FilterParser.SimpleContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#restriction}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRestriction(FilterParser.RestrictionContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#comparator}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparator(FilterParser.ComparatorContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#isComparator}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIsComparator(FilterParser.IsComparatorContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#composite}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComposite(FilterParser.CompositeContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#value}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitValue(FilterParser.ValueContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#string}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitString(FilterParser.StringContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#field}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitField(FilterParser.FieldContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#number}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNumber(FilterParser.NumberContext ctx);
    /**
     * Visit a parse tree produced by {@link FilterParser#connection}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConnection(FilterParser.ConnectionContext ctx);
}