
package org.example.expression;

public class Division implements Expression {

    private final Expression left;
    private final Expression right;

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {

        double numerator = left.evaluate();
        double denominator = right.evaluate();

        if (denominator == 0) {
            throw new ArithmeticException(
                    "Division by zero is not allowed");
        }

        return numerator / denominator;
    }
}
