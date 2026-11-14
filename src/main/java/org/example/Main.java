
package org.example;

import org.example.model.Spreadsheet;
import org.example.persistence.SpreadsheetFileManager;
import org.example.expression.Expression;
import org.example.expression.NumberExpression;
import org.example.expression.Addition;
import org.example.expression.Subtraction;
import org.example.expression.Multiplication;
import org.example.expression.Division;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Create spreadsheet
        Spreadsheet sheet = new Spreadsheet(3, 3);

        sheet.setCellContent("A1", "10");
        sheet.setCellContent("B1", "20");
        sheet.setCellContent("C1", "=A1+B1");

        sheet.setCellContent("A2", "Hello");
        sheet.setCellContent("B2", "32");


        sheet.setCellContent("B3", "TOTAL");
        sheet.setCellContent("C3", "=1+2");

// TEST: Cell content containing a semicolon
        sheet.setCellContent("A3", "Hello;World");


        // Save spreadsheet
        SpreadsheetFileManager.save(sheet, "test.s2v");
        System.out.println("Spreadsheet saved successfully!");

        // Load spreadsheet
        Spreadsheet loaded =
                SpreadsheetFileManager.load("test.s2v");

        System.out.println("Spreadsheet loaded successfully!");

        // Display loaded cell contents
        System.out.println("\n===== LOADED SPREADSHEET =====");

        System.out.println("A1: " + loaded.getCellContent("A1"));
        System.out.println("B1: " + loaded.getCellContent("B1"));
        System.out.println("C1: " + loaded.getCellContent("C1"));

        System.out.println("A2: " + loaded.getCellContent("A2"));
        System.out.println("B2: " + loaded.getCellContent("B2"));

        System.out.println("B3: " + loaded.getCellContent("B3"));
        System.out.println("C3: " + loaded.getCellContent("C3"));
        System.out.println("A3: " + loaded.getCellContent("A3"));

        System.out.println("\nRows: " + loaded.getRowCount());
        System.out.println("Columns: " + loaded.getColumnCount());


        System.out.println("\n===== EMPTY CELLS TEST =====");

        Spreadsheet testSheet = new Spreadsheet(5, 5);
        testSheet.setCellContent("A1", "10");

        SpreadsheetFileManager.save(testSheet, "empty_test.s2v");

        Spreadsheet loadedTest =
                SpreadsheetFileManager.load("empty_test.s2v");

        System.out.println("Rows: " + loadedTest.getRowCount());
        System.out.println("Columns: " + loadedTest.getColumnCount());
        System.out.println("A1: " + loadedTest.getCellContent("A1"));
        System.out.println("E5: " + loadedTest.getCellContent("E5"));


        System.out.println("\n===== EXPRESSION TEST =====");

        NumberExpression num1 = new NumberExpression(5);
        NumberExpression num2 = new NumberExpression(10);
        NumberExpression num3 = new NumberExpression(3.5);

        System.out.println("Number 1: " + num1.evaluate());
        System.out.println("Number 2: " + num2.evaluate());
        System.out.println("Number 3: " + num3.evaluate());

        System.out.println("\n===== ADDITION TEST =====");

// Test 1: Simple addition 5 + 3
        Expression test1 = new Addition(
                new NumberExpression(5),
                new NumberExpression(3)
        );

        System.out.println("5 + 3 = " + test1.evaluate());

// Test 2: Nested addition (1 + 2) + 4
        Expression test2 = new Addition(
                new Addition(
                        new NumberExpression(1),
                        new NumberExpression(2)
                ),
                new NumberExpression(4)
        );

        System.out.println("(1 + 2) + 4 = " + test2.evaluate());


        System.out.println("\n===== SUBTRACTION TEST =====");

// Test 1: Simple subtraction
        Expression test3 = new Subtraction(
                new NumberExpression(10),
                new NumberExpression(4)
        );

        System.out.println("10 - 4 = " + test3.evaluate());

// Test 2: Nested subtraction
        Expression test4 = new Subtraction(
                new Subtraction(
                        new NumberExpression(10),
                        new NumberExpression(4)
                ),
                new NumberExpression(2)
        );

        System.out.println("(10 - 4) - 2 = " + test4.evaluate());

// Test 3: Addition inside subtraction
        Expression test5 = new Subtraction(
                new NumberExpression(10),
                new Addition(
                        new NumberExpression(3),
                        new NumberExpression(2)
                )
        );

        System.out.println("10 - (3 + 2) = " + test5.evaluate());


        System.out.println("\n===== MULTIPLICATION TEST =====");

// Test 1: Simple multiplication
        Expression test6 = new Multiplication(
                new NumberExpression(3),
                new NumberExpression(5)
        );

        System.out.println("3 * 5 = " + test6.evaluate());

// Test 2: Multiplication with addition
// 1 + (2 * 4) = 9
        Expression test7 = new Addition(
                new NumberExpression(1),
                new Multiplication(
                        new NumberExpression(2),
                        new NumberExpression(4)
                )
        );

        System.out.println("1 + 2 * 4 = " + test7.evaluate());

// Test 3: Addition followed by multiplication
// (1 + 2) * 4 = 12
        Expression test8 = new Multiplication(
                new Addition(
                        new NumberExpression(1),
                        new NumberExpression(2)
                ),
                new NumberExpression(4)
        );

        System.out.println("(1 + 2) * 4 = " + test8.evaluate());




        System.out.println("\n===== DIVISION TEST =====");

// Test 1: Simple division
        Expression test9 = new Division(
                new NumberExpression(20),
                new NumberExpression(4)
        );

        System.out.println("20 / 4 = " + test9.evaluate());

// Test 2: Addition inside division
        Expression test10 = new Division(
                new Addition(
                        new NumberExpression(10),
                        new NumberExpression(10)
                ),
                new NumberExpression(4)
        );

        System.out.println("(10 + 10) / 4 = " + test10.evaluate());

// Test 3: Nested multiplication and division
        Expression test11 = new Division(
                new Multiplication(
                        new NumberExpression(3),
                        new NumberExpression(6)
                ),
                new NumberExpression(2)
        );

        System.out.println("(3 * 6) / 2 = " + test11.evaluate());

// Test 4: Division by zero
        try {
            Expression test12 = new Division(
                    new NumberExpression(10),
                    new NumberExpression(0)
            );

            System.out.println(test12.evaluate());

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }





    }
}
