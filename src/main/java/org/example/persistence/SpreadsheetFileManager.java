
package org.example.persistence;

import org.example.model.Spreadsheet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpreadsheetFileManager {

    // Convert column index to column name
    // 0 -> A, 1 -> B, 26 -> AA
    private static String columnName(int index) {

        StringBuilder name = new StringBuilder();
        int number = index + 1;

        while (number > 0) {
            number--;
            name.insert(0, (char) ('A' + number % 26));
            number /= 26;
        }

        return name.toString();
    }

    private static String escapeContent(String content) {

        if (content.contains(";") ||
                content.contains("\"") ||
                content.contains("\n") ||
                content.contains("\r")) {

            return "\"" + content.replace("\"", "\"\"") + "\"";
        }

        return content;
    }

    private static String[] parseLine(String line) {

        java.util.List<String> values = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();

        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"') {
                if (insideQuotes &&
                        i + 1 < line.length() &&
                        line.charAt(i + 1) == '"') {

                    current.append('"');
                    i++;

                } else {
                    insideQuotes = !insideQuotes;
                }

            } else if (c == ';' && !insideQuotes) {

                values.add(current.toString());
                current.setLength(0);

            } else {
                current.append(c);
            }
        }

        values.add(current.toString());

        return values.toArray(new String[0]);
    }


    // Save spreadsheet to an S2V file
    public static void save(Spreadsheet sheet, String filename)
            throws IOException {

        try (BufferedWriter writer =
                     Files.newBufferedWriter(Path.of(filename))) {

            // Write the content to the file

            for (int row = 0; row < sheet.getRowCount(); row++) {
                for (int col = 0; col < sheet.getColumnCount(); col++) {


                    if (col > 0) {
                        writer.write(";");
                    }

                    String address = columnName(col) + (row + 1);
                    String content = sheet.getCellContent(address);

                    // Convert formula argument separators
                    if (content.startsWith("=")) {
                        content = content.replace(';', ',');
                    }

                    writer.write(escapeContent(content));
                }

                writer.newLine();
            }
        }
    }

    public static Spreadsheet load(String filename)
            throws IOException {

        // Step 1: Read all lines from the file
        java.util.List<String> lines =
                Files.readAllLines(Path.of(filename));

        // Step 2: Determine spreadsheet dimensions
        int rows = Math.max(1, lines.size());
        int columns = 1;

        for (String line : lines) {
            String[] values = parseLine(line);
            columns = Math.max(columns, values.length);
        }

        // Step 3: Create a new spreadsheet
        Spreadsheet sheet = new Spreadsheet(rows, columns);

        // Step 4: Fill spreadsheet cells
        for (int row = 0; row < lines.size(); row++) {

            String[] values = parseLine(lines.get(row));

            for (int col = 0; col < values.length; col++) {

                String content = values[col];

                // Restore formula argument separators
                if (content.startsWith("=")) {
                    content = content.replace(',', ';');
                }

                String address = columnName(col) + (row + 1);

                sheet.setCellContent(address, content);
            }
        }

        return sheet;
    }

}
