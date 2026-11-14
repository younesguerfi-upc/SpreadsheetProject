
package org.example.model;

public class Spreadsheet {

    private final Cell[][] cells;

    public Spreadsheet(int rows, int columns) {

        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException(
                    "Rows and columns must be positive");
        }

        cells = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setCellContent(String address, String content) {

        CellAddress cellAddress = new CellAddress(address);

        int row = cellAddress.getRow();
        int column = cellAddress.getColumn();

        cells[row][column].setContent(content);
    }

    public String getCellContent(String address) {

        CellAddress cellAddress = new CellAddress(address);

        int row = cellAddress.getRow();
        int column = cellAddress.getColumn();

        return cells[row][column].getContent();
    }

    public int getRowCount() {
        return cells.length;
    }

    public int getColumnCount() {
        return cells[0].length;
    }

}
