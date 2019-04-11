import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

public class PrinterTest {

    private List<LibraryItem> books = new ArrayList<>();
    private List<LibraryItem> movies = new ArrayList<>();
    private Printer printer;
    private String welcomeString = "Welcome to Biblioteca, your one-stop-shop for great books in Bangalor!\n";
    private ByteArrayOutputStream byteArrayOutputStreamSpy;
    Book lotrBook = new Book(0, "Lord of the Rings", "Tolkien", new Date());
    Book bambieBook = new Book(1, "Bambi", "Disney", new Date());
    Book hamletBook = new Book(2, "Hamlet", "Shakespear", new Date());
    Movie amelieMovie = new Movie(8, "Amelie", "Bonjour", new Date(), 4);
    Movie lotrMovie = new Movie(9, "Lord of the Rings", "Tolkien", new Date(), 8);

    @Before
    public void setUp() {
        this.byteArrayOutputStreamSpy = new ByteArrayOutputStream();
        this.printer = new Printer(new PrintStream(byteArrayOutputStreamSpy));
        this.books.add(lotrBook);
        this.books.add(bambieBook);
        this.books.add(hamletBook);
        this.movies.add(lotrMovie);
        this.movies.add(amelieMovie);
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
        printer.printItems(this.books);
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrBook.getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrBook.getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrBook.getYearPublished()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(bambieBook.getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(hamletBook.getTitle()));
    }

    @Test
    public void printBooksShouldPrintMovies() {
        printer.printItems(this.movies);
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrMovie.getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrMovie.getDirector()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrMovie.getYearPublished()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(amelieMovie.getTitle()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(amelieMovie.getDirector()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(amelieMovie.getYearPublished()));

    }

    @Test
    public void printBooksShouldPrintDateAndAuthor() {
        printer.printItems(this.books);

        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrBook.getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(lotrBook.getYearPublished()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(bambieBook.getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(bambieBook.getYearPublished()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(hamletBook.getAuthor()));
        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString(hamletBook.getYearPublished()));
    }

//
//    @Test
//    public void printMenuChoicesShouldPrintMenu() {
//        printer.menuOptions();
//        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString("Choose an option"));
//        Assert.assertThat(byteArrayOutputStreamSpy.toString(), containsString("1: List of Books"));
//    }

}