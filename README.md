# Spreadsheet Project

## software engineering – Deliverable 2

This project implements a spreadsheet application in Java as part of the Software Architecture course.

### Implemented Features

- Create a spreadsheet with a specified number of rows and columns.
- Set and retrieve cell contents using addresses such as A1, B2, and AA10.
- Save and load spreadsheets using the S2V file format.
- Preserve empty cells and spreadsheet dimensions.
- Store formulas as text without evaluating them during spreadsheet operations.
- Support quoted fields containing semicolons.

### Project Structure

- `model` – Cell, CellAddress, and Spreadsheet classes.
- `persistence` – SpreadsheetFileManager for saving and loading spreadsheets.
- `expression` – Arithmetic expression classes using the Composite Design Pattern.
- `ExpressionTest` – JUnit tests for arithmetic expressions.

### Technologies

- Java
- Maven
- JUnit 5

### How to Run

1. Open the project in IntelliJ IDEA.
2. Load the Maven dependencies.
3. Run `org.example.Main`.
4. The program demonstrates spreadsheet saving and loading, followed by arithmetic expression tests.

### Testing

Run `ExpressionTest.java` to execute the JUnit tests.

The `Main.java` class also demonstrates saving and loading spreadsheet contents, including semicolons and empty cells.

### Note

Formula evaluation is not required for Deliverable 2. The expression classes are additional functionality developed separately.
