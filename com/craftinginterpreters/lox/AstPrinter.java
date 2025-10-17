package com.craftinginterpreters.lox;

public class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary binary) {
        return parenthesize(binary.operator.lexeme, binary.left, binary.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping grouping) {
        return parenthesize("group", grouping.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal literal) {
        if (literal.value == null)
            return "nil";
        return literal.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary unary) {
        return parenthesize(unary.operator.lexeme, unary.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            // recursive step on each subexpression
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression));
    }
}
