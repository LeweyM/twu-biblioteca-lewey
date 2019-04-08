import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Column {

    private final List <List <String>> lines;
    private int currentLineIndex;

    private final List<Integer> columnWidths;
    private int colsTotal;

    public Column() {
        this.lines = new ArrayList<>();
        this.currentLineIndex = 0;
        this.columnWidths = new ArrayList<>();
        this.colsTotal = -1;
    }

    public void addLine(String... strings) {

        for (int colIndex = 0; colIndex < strings.length; colIndex++) {
            adjustColumnTotals(colIndex);
            adjustColumnSize(colIndex, strings[colIndex]);
        }
        lines.add(currentLineIndex, Arrays.asList(strings));
        currentLineIndex++;
    }

    private void adjustColumnTotals(int columnIndex) {
        if (columnIndex > colsTotal) {
            columnWidths.add(0);
            colsTotal++;
        }
    }

    private void adjustColumnSize(int columnIndex, String string) {
        int colWidth = columnWidths.get(columnIndex);
        if (string.length() > colWidth) {
            columnWidths.set(columnIndex, string.length());
        }
    }

    private String pad(String string, int max) {
        StringBuilder workingString = new StringBuilder(string);
        while (workingString.length() < max) {
            workingString.append(" ");
        }
        return workingString.toString();
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder(512);
        for (List line: lines) {
            stringBuilder.append(getLine(line));
        }
        return stringBuilder.toString();
    }

    private String getLine(List<String> line) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int colIndex = 0; colIndex < line.size(); colIndex++) {
            int colWidth = this.columnWidths.get(colIndex);
            stringBuilder.append(pad(line.get(colIndex), colWidth));
            if (colIndex != line.size() - 1) stringBuilder.append(" | ");
        }

        return stringBuilder + "\n";
    }
}
