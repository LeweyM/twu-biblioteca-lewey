import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class BibliotecaTest {

    private Book[] books;
    private Biblioteca biblioteca;

    private void initData() {
        books = new Book[3];
        books[0] = new Book("Lord of the Rings", 0, "Tolkien", new Date());
        books[1] = new Book("Bambi", 1, "Disney", new Date());
        books[2] = new Book("Hamlet", 2, "Shakespeare", new Date());
    }

    @Before
    public void setUp() {
        initData();
        biblioteca = new Biblioteca(books);
    }

    @Test
    public void shouldFindBookByTitle() {
        Optional<Book> bookByTitle = biblioteca.findBookByTitle(books[0].getTitle());
        Assert.assertThat(bookByTitle.get(), is(books[0]));
    }

    @Test
    public void shouldFindByTitleCaseInsensitve() {
        Optional<Book> bookByTitle = biblioteca.findBookByTitle(books[0].getTitle().toUpperCase());
        Assert.assertThat(bookByTitle.get(), is(books[0]));
    }

    @Test
    public void findBookShouldReturnEmptyOptionalIfCannotFind() {
        Optional<Book> bookByTitle = biblioteca.findBookByTitle("some-bad-title");
        Assert.assertThat(bookByTitle.isPresent(), is(false));
    }

    @Test
    public void checkoutShouldReturnTrueIfSuccessful() {
        boolean checkedOutSuccessfully = biblioteca.checkout(books[0]);

        Assert.assertTrue(checkedOutSuccessfully);
    }

    @Test(expected = NullPointerException.class)
    public void checkoutShouldReturnExceptionIfBookNotFound() {
        Book bookNotInList = new Book("testTitle", 99, "testAuthor", new Date());
        biblioteca.checkout(bookNotInList);
    }

    @Test
    public void checkoutShouldReturnFalseIfBookAlreadyCheckedOut() {
        biblioteca.checkout(books[0]);
        boolean checkedOutSuccessfully = biblioteca.checkout(books[0]);

        Assert.assertFalse(checkedOutSuccessfully);
    }

    @Test
    public void isCheckedOutShouldReturnTrue() {
        biblioteca.checkout(books[0]);
        boolean isCheckedOut = biblioteca.isCheckedOut(books[0]);

        Assert.assertTrue(isCheckedOut);
    }

    @Test
    public void isCheckedOutShouldReturnFalse() {
        boolean isCheckedOut = biblioteca.isCheckedOut(books[0]);

        Assert.assertFalse(isCheckedOut);
    }

    @Test
    public void getAvailableBooksShouldReturnListOfNonCheckedOutBooks() {
        biblioteca.checkout(books[0]);
        List<Book> availableBooks = biblioteca.getAvailableBooks();

        Assert.assertTrue(availableBooks.contains(books[1]));
        Assert.assertTrue(availableBooks.contains(books[2]));
        Assert.assertFalse(availableBooks.contains(books[0]));
    }

    @Test
    public void returnBookShouldReturnTrueForCheckedOutBook() {
        biblioteca.checkout(books[0]);
        boolean returnBookSuccess = biblioteca.returnBook(books[0]);

        Assert.assertTrue(returnBookSuccess);
    }

    @Test
    public void returnBookShouldReturnFalseForNotCheckedOutBook() {
        boolean returnBookSuccess = biblioteca.returnBook(books[0]);

        Assert.assertFalse(returnBookSuccess);
    }
}



