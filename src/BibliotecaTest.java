import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

public class BibliotecaTest {
    private BufferedReader bufferedInput;
    private ByteArrayInputStream byteArrayInputStream;
    private MockPrinter mockPrinter;
    private Biblioteca biblioteca;
    private PrintStream outputSpy;

    private Book[] books;

    private void initData() {
        books = new Book[3];
        books[0] = new Book("Lord of the Rings", 0, "Tolkien", new Date());
        books[1] = new Book("Bambi", 1, "Disney", new Date());
        books[2] = new Book("Hamlet", 2, "Shakespeare", new Date());
    }

    @Before
    public void setUp() {
        outputSpy = new PrintStream(new ByteArrayOutputStream());
        mockPrinter = new MockPrinter(outputSpy);
        initData();
    }

    private void setupInputStream(String ...mockUserInput) {
        String joinedInput = String.join("\n", mockUserInput);
        byteArrayInputStream = new ByteArrayInputStream(joinedInput.getBytes());
        bufferedInput = new BufferedReader(new InputStreamReader(byteArrayInputStream));

        biblioteca = new Biblioteca(bufferedInput, mockPrinter, books);
    }

    @Test
    public void InputOneShouldCallPrintBooks() {
        setupInputStream("1");
        biblioteca.start();

        Assert.assertThat(mockPrinter.getPrintBooksCalledCount(), is(1));

    }

    @Test
    public void InputStringXShouldCallPrintInvalidOption() {
        setupInputStream("x");
        biblioteca.start();

        Assert.assertThat(mockPrinter.getPrintInvalidInputCalledCount(), is(1));
        Assert.assertThat(mockPrinter.getPrintMenuChoicesCalledCount(), is(2));
    }

    @Test
    public void InputInvalidStringShouldCallPrintInvalidOptionMultipleTimes() {
        setupInputStream("x", "p", "1");
        biblioteca.start();

        Assert.assertThat(mockPrinter.getPrintInvalidInputCalledCount(), is(2));
        Assert.assertThat(mockPrinter.getPrintMenuChoicesCalledCount(), is(4));
        Assert.assertThat(mockPrinter.getPrintBooksCalledCount(), is(1));
    }

    @Test
    public void InputThreeShouldExitApplication() {
        setupInputStream("q");
        biblioteca.start();

        Assert.assertThat(biblioteca.isAppRunning(), is(false));
    }

    @Test
    public void InputTwoAndBookTitleShouldCheckoutBook() {
        String bookTitle = books[2].getTitle();
        setupInputStream("2", bookTitle);
        biblioteca.start();

        Assert.assertThat(books[2].isCheckedOut(), is(true));
        Assert.assertThat(mockPrinter.getPrintCheckoutSuccessCalledCount(), is(1));
    }

    @Test
    public void checkedOutBookShouldCallBookAlreadyCheckedOut() {
        books[0].checkout();
        String bookTitle = books[0].getTitle();
        setupInputStream("2", bookTitle);
        biblioteca.start();

        Assert.assertThat(books[0].isCheckedOut(), is(true));
        Assert.assertThat(mockPrinter.getGetBookAlreadyCheckedOutCalledCount(), is(1));
    }

    @Test
    public void checkoutShouldNotBeCaseSensitive() {
        books[0].checkout();
        String upperCaseTitle = books[0].getTitle().toUpperCase();
        setupInputStream("2", upperCaseTitle);
        biblioteca.start();

        Assert.assertThat(books[0].isCheckedOut(), is(true));
        Assert.assertThat(mockPrinter.getGetBookAlreadyCheckedOutCalledCount(), is(1));
    }

    @Test
    public void inputThreeAndBookTitleShouldReturnCheckedOutBook() {
        books[0].checkout();
        setupInputStream("3", books[0].getTitle());
        biblioteca.start();

        Assert.assertThat(books[0].isCheckedOut(), is(false));
        Assert.assertThat(mockPrinter.getPrintReturnBookSuccessCalledCount(), is(1));
    }
}



