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
        String workingString = string;
        while (workingString.length() < max) {
            workingString += " ";
        }
        return workingString;
    }

    public String getString() {
        String result = "";
        for (List line: lines) {
            result += getLine(line);

        }
        return result;
    }

    private String getLine(List<String> line) {
        String result = "";

        for (int colIndex = 0; colIndex < line.size(); colIndex++) {
            int colWidth = this.columnWidths.get(colIndex);
            result += pad(line.get(colIndex), colWidth);
            if (colIndex != line.size() - 1) result += " ";
        }

        return result + "\n";
    }
}
