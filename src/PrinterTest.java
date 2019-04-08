import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

public class PrinterTest {

    private Book[] books = new Book[3];
    private Printer printer;
    private String welcomeString = "Welcome to Biblioteca, your one-stop-shop for great books in Bangalor!\n";
    private ByteArrayOutputStream byteArrayOutputStreamSpy;

    @Before
    public void setUp() {
        this.byteArrayOutputStreamSpy = new ByteArrayOutputStream();
        this.printer = new Printer(new PrintStream(byteArrayOutputStreamSpy));
        this.books[0] = new Book("Lord of the Rings", 0, "Tolkien", new Date());
        this.books[1] = new Book("Bambi", 1, "Disney", new Date());
        this.books[2] = new Book("Hamlet", 2, "Shakespear", new Date());
    }

    @Test
    public void welcomeShouldPrintString() {
        printer.welcome();
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), is(not("")));
    }

    @Test
    public void welcomeShouldPrintWelcomeString() {
        printer.welcome();
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), is(welcomeString));
    }

    @Test
    public void printBooksShouldPrintBooks() {
        printer.printBooks(this.books);
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[0].getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[1].getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[2].getTitle()));
    }

    @Test
    public void printBooksShouldPrintDateAndAuthor() {
        printer.printBooks(this.books);

        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[0].getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[0].getYearPublished()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[1].getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[1].getYearPublished()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[2].getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[2].getYearPublished()));
    }

    @Test
    public void checkedOutBooksShouldNotBeDisplayed() {
        this.books[0].checkout();
        printer.printBooks(this.books);

        Assert.assertThat(byteArrayOutputStreamSpy.toString(), not(containsString(this.books[0].getTitle())));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), not(containsString(this.books[0].getAuthor())));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[1].getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[1].getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[2].getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(this.books[2].getAuthor()));
    }

    @Test
    public void printMenuChoicesShouldPrintMenu() {
        printer.printMenuChoices();
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString("Choose an option"));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString("1: List of Books"));
    }

}