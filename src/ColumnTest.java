import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ColumnTest {

    @Test
    public void addLineShouldReturnSpacedStringWithNewLine() {
        Column column = new Column();
        column.addLine("1", "2", "3");
        assertThat(column.getString(), is("1 | 2 | 3\n"));
    }

    @Test
    public void addingTwoLinesShouldReturnEvenlySpacedColumns() {
        Column column = new Column();
        column.addLine("1", "2", "3");
        column.addLine("one", "two", "three");
        assertThat(column.getString(), is("1   | 2   | 3    \none | two | three\n"));
    }
}