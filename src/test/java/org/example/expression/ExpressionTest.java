
package org.example.expression;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTest {

    @Test
    void testAddition() {

        // Create the expression: 5 + 3
        Expression expression = new Addition(
                new NumberExpression(5),
                new NumberExpression(3)
        );

        // Verify the result
        assertEquals(8.0, expression.evaluate());
    }

    @Test
    void testSubtraction() {

        Expression expression = new Subtraction(
                new NumberExpression(10),
                new NumberExpression(4)
        );

        assertEquals(6.0, expression.evaluate());
    }

    @Test
    void testMultiplication() {

        Expression expression = new Multiplication(
                new NumberExpression(3),
                new NumberExpression(5)
        );

        assertEquals(15.0, expression.evaluate());
    }

    @Test
    void testDivision() {

        Expression expression = new Division(
                new NumberExpression(20),
                new NumberExpression(4)
        );

        assertEquals(5.0, expression.evaluate());
    }

    @Test
    void testNestedAdditionAndMultiplication() {

        // (5 + 3) * 2 = 16
        Expression expression = new Multiplication(
                new Addition(
                        new NumberExpression(5),
                        new NumberExpression(3)
                ),
                new NumberExpression(2)
        );

        assertEquals(16.0, expression.evaluate());
    }

    @Test
    void testNestedSubtractionAndMultiplication() {

        // 10 - (2 * 3) = 4
        Expression expression = new Subtraction(
                new NumberExpression(10),
                new Multiplication(
                        new NumberExpression(2),
                        new NumberExpression(3)
                )
        );

        assertEquals(4.0, expression.evaluate());
    }

    @Test
    void testComplexDivision() {

        // (20 + 10) / (2 + 3) = 6
        Expression expression = new Division(
                new Addition(
                        new NumberExpression(20),
                        new NumberExpression(10)
                ),
                new Addition(
                        new NumberExpression(2),
                        new NumberExpression(3)
                )
        );

        assertEquals(6.0, expression.evaluate());
    }


    @Test
    void testDivisionByZero() {

        Expression expression = new Division(
                new NumberExpression(10),
                new NumberExpression(0)
        );

        assertThrows(
                ArithmeticException.class,
                () -> expression.evaluate()
        );
    }


}
