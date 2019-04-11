import com.sun.tools.javac.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class SessionTest {

    private UserInterface userInterface;
    private UserManager userManager;
    private Biblioteca biblioteca;
    private Printer printer;
    private Session session;

    private DataInjector dataInjector = new DataInjector();
    Movie lordOfTheRingsMovie = dataInjector.getLordOfTheRingsMovie();
    Movie amelie = dataInjector.getAmelieMovie();
    Book lordOfTheRingsBook = dataInjector.getLordOfTheRingsBook();
    Book bambie = dataInjector.getBambieBook();
    Book hamlet = dataInjector.getHamletBook();
    User lewey = dataInjector.getLewey();
    User pau = dataInjector.getPau();
    java.util.List<User> users = dataInjector.getUsers();
    java.util.List<LibraryItem> items = dataInjector.getItems();

    @Before
    public void setUp() {
        userInterface = mock(UserInterface.class);
        printer = mock(Printer.class);
        biblioteca = mock(Biblioteca.class);
        userManager = mock(UserManager.class);
        session = new Session(biblioteca, printer, userInterface, userManager);
    }

    @Test
    public void shouldQuitWithQuitCommand() {

        session.quit();

        verify(printer).quitMessage();
        Assert.assertFalse(session.isAppRunning());
    }

    @Test
    public void shouldPrintListItems() {
        String[] headers = {"Title", "Author", "Date Published"};
        Mockito.when(userInterface.getUserInputString())
                .thenReturn("b");
        Mockito.when(biblioteca.getAvailableItemsByType(Book.class))
                .thenReturn(List.of(hamlet));
        Mockito.when(biblioteca.getHeader(Book.class))
                .thenReturn(headers);

        session.listItems();

        verify(printer).printItems(List.of(hamlet), headers);
    }

    @Test
    public void callsCheckoutSuccess() {
        when(userInterface.getUserInputString())
                .thenReturn("b")
                .thenReturn("hamlet");
        when(biblioteca.findByTitleAndType("hamlet" ,Book.class))
                .thenReturn(Optional.of(hamlet));
        when(biblioteca.checkoutItem(hamlet))
                .thenReturn(true);

        session.checkoutItem();

        verify(printer).checkoutInstructions();
        verify(printer).checkoutSuccess();
    }

    @Test
    public void callsCheckoutFailure() {
        when(userInterface.getUserInputString())
                .thenReturn("b")
                .thenReturn("hamlet");
        when(biblioteca.findByTitleAndType("hamlet", Book.class))
                .thenReturn(Optional.of(hamlet));
        when(biblioteca.checkoutItem(hamlet))
                .thenReturn(false);

        session.checkoutItem();

        verify(printer).checkoutInstructions();
        verify(printer).checkoutFailure();
    }

    @Test
    public void callsBookNotFound() {
        when(userInterface.getUserInputString())
                .thenReturn("b")
                .thenReturn("hamlet");
        when(biblioteca.findByTitleAndType("hamlet", Book.class))
                .thenReturn(Optional.empty());

        session.checkoutItem();

        verify(printer).checkoutInstructions();
        verify(printer).itemNotFound();
    }


    @Test
    public void callsCheckoutSuccessForMovie()  {
        when(userInterface.getUserInputString())
                .thenReturn("m")
                .thenReturn("amelie");
        when(biblioteca.findByTitleAndType("amelie", Movie.class))
                .thenReturn(Optional.of(amelie));
        when(biblioteca.checkoutItem(amelie))
                .thenReturn(true);

        session.checkoutItem();

        verify(printer).checkoutInstructions();
        verify(printer).checkoutSuccess();
    }

    @Test
    public void callsReturnSuccess() {
        when(userInterface.getUserInputString())
                .thenReturn("b")
                .thenReturn(("hamlet"));
        when(biblioteca.findByTitleAndType("hamlet", Book.class))
                .thenReturn(Optional.of(hamlet));
        when(biblioteca.returnItem(hamlet))
                .thenReturn(true);

        session.returnItem();

        verify(printer).returnInstructions();
        verify(printer).returnSuccess();
    }

    @Test
    public void callsReturnFailure() {
        when(userInterface.getUserInputString())
                .thenReturn("b")
                .thenReturn(("hamlet"));
        when(biblioteca.findByTitleAndType("hamlet", Book.class))
                .thenReturn(Optional.of(hamlet));
        when(biblioteca.returnItem(hamlet))
                .thenReturn(false);

        session.returnItem();

        verify(printer).returnInstructions();
        verify(printer).returnFailure();
    }

    @Test
    public void loginShouldLoginWithGoodPw() {
        when(userInterface.getUserInputString())
                .thenReturn("0001111");
        when(userManager.getUserByPassword(any()))
                .thenReturn(Optional.of(lewey));

        session.login();

        Assert.assertTrue(session.isUserLoggedIn());
        verify(printer).loginSuccess();
    }

    @Test
    public void loginShouldNotLoginWithBadPw() {
        when(userInterface.getUserInputString())
                .thenReturn("9998888");
        when(userManager.getUserByPassword(any()))
                .thenReturn(Optional.empty());

        session.login();

        Assert.assertFalse(session.isUserLoggedIn());
        verify(printer).loginFailure();
    }
}