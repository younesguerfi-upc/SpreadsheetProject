
package org.example.model;

public class CellAddress {

    private final int row;
    private final int column;

    public CellAddress(String address) {

        if (address == null ||
                !address.matches("[A-Za-z]+[1-9][0-9]*")) {
            throw new IllegalArgumentException(
                    "Invalid cell address: " + address);
        }

        address = address.toUpperCase();

        int i = 0;

        while (i < address.length() &&
                Character.isLetter(address.charAt(i))) {
            i++;
        }

        String columnLetters = address.substring(0, i);
        String rowNumbers = address.substring(i);

        int col = 0;

        for (char c : columnLetters.toCharArray()) {
            col = col * 26 + (c - 'A' + 1);
        }

        this.column = col - 1;
        this.row = Integer.parseInt(rowNumbers) - 1;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
