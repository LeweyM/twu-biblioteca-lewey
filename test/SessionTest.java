import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SessionTest {

    private static Book[] BOOKS = {
            new Book("Lord of the Rings", 0, "Tolkien", new Date()),
            new Book("Bambi", 1, "Disney", new Date()),
            new Book("Hamlet", 2, "Shakespeare", new Date()),
    };

    private UserInterface userInterface;
    private Biblioteca biblioteca;
    private Printer printer;
    private Session session;

    @Before
    public void setUp() throws Exception {
        userInterface = mock(UserInterface.class);
        printer = mock(Printer.class);
        biblioteca = mock(Biblioteca.class);
        session = new Session(biblioteca, printer, userInterface);
    }

    @Test
    public void shouldCallWelcome() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("q");

        session.start();

        verify(printer).welcome();
    }

    @Test
    public void shouldCallCheckoutSuccess() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("2", BOOKS[2].getTitle(), "q");
        Mockito.when(biblioteca.findBookByTitle(BOOKS[2].getTitle())).thenReturn(Optional.of(BOOKS[2]));
        Mockito.when(biblioteca.checkout(BOOKS[2])).thenReturn(true);

        session.start();

        verify(printer).checkoutSuccess();
    }

    @Test
    public void shouldCallCheckoutFailure() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("2", BOOKS[2].getTitle(), "q");
        Mockito.when(biblioteca.findBookByTitle(BOOKS[2].getTitle())).thenReturn(Optional.of(BOOKS[2]));
        Mockito.when(biblioteca.checkout(BOOKS[2])).thenReturn(false);

        session.start();

        verify(printer).checkoutFailure();
    }

    @Test
    public void shouldCallBookNotFound() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("2", "book-title-we-do-not-have", "q");
        Mockito.when(biblioteca.findBookByTitle("book-title-we-do-not-have")).thenReturn(Optional.empty());

        session.start();

        verify(printer).bookNotFound();
    }

    @Test
    public void shouldCallReturnSuccess() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("3", BOOKS[0].getTitle(), "q");
        Mockito.when(biblioteca.findBookByTitle(BOOKS[0].getTitle())).thenReturn(Optional.of(BOOKS[0]));
        Mockito.when(biblioteca.returnBook(BOOKS[0])).thenReturn(true);

        session.start();

        verify(printer).returnSuccess();
    }

    @Test
    public void shouldCallReturnFailure() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("3", BOOKS[0].getTitle(), "q");
        Mockito.when(biblioteca.findBookByTitle(BOOKS[0].getTitle())).thenReturn(Optional.of(BOOKS[0]));
        Mockito.when(biblioteca.returnBook(BOOKS[2])).thenReturn(false);

        session.start();

        verify(printer).returnFailure();
    }

    @Test
    public void shouldGetBooksAndCallPrint() throws IOException {
        Mockito.when(userInterface.getUserInputString()).thenReturn("1", "q");
        Mockito.when(biblioteca.getAvailableBooks()).thenReturn(Arrays.asList(BOOKS));

        session.start();

        verify(printer).printBooks(Arrays.asList(BOOKS));
    }

}