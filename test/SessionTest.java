import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SessionTest {

    private UserInterface userInterface;
    private Biblioteca biblioteca;
    private Printer printer;
    private Session session;

    @Before
    public void setUp() {
        userInterface = mock(UserInterface.class);
        printer = mock(Printer.class);
        biblioteca = mock(Biblioteca.class);
    }

    @Test(expected = NullPointerException.class)
    public void shouldCallWelcome() throws IOException {
        session = new Session(biblioteca, printer, userInterface);

        session.start();

        verify(printer).welcome();
    }

    @Test
    public void shouldQuitWithQuitCommand() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        Mockito.when(userInterface.getUserInputString())
                .thenReturn("q")
                .thenReturn(null);

        session.start();

        Assert.assertFalse(session.isAppRunning());
    }

    @Test(expected = NullPointerException.class)
    public void shouldPrintItemsWithListItemsCommand() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        Mockito.when(userInterface.getUserInputString())
                .thenReturn("1")
                .thenReturn(null);

        session.start();

        verify(printer).printItems(any());
    }

    @Test(expected = NullPointerException.class)
    public void callsCheckoutSuccess() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        when(userInterface.getUserInputString())
                .thenReturn("3")
                .thenReturn("hamlet")
                .thenReturn(null);
        when(biblioteca.checkoutItem(any()))
                .thenReturn(true);

        session.start();

        verify(printer).checkoutSuccess();
    }

    @Test(expected = NullPointerException.class)
    public void callsCheckoutFailure() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        when(userInterface.getUserInputString())
                .thenReturn("3")
                .thenReturn("hamlet")
                .thenReturn(null);
        when(biblioteca.checkoutItem(any()))
                .thenReturn(false);

        session.start();

        verify(printer).checkoutFailure();
    }


    @Test(expected = NullPointerException.class)
    public void callsCheckoutSuccessForMovie() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        when(userInterface.getUserInputString())
                .thenReturn("4")
                .thenReturn("amelie")
                .thenReturn(null);
        when(biblioteca.checkoutItem(any()))
                .thenReturn(true);

        session.start();

        verify(printer).checkoutSuccess();
    }


    @Test(expected = NullPointerException.class)
    public void callsReturnSuccess() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        when(userInterface.getUserInputString())
                .thenReturn("5")
                .thenReturn(("hamlet"))
                .thenReturn(null);
        when(biblioteca.returnItem(any()))
                .thenReturn(true);

        session.start();

        verify(printer).returnSuccess();
    }

    @Test(expected = NullPointerException.class)
    public void callsReturnFailure() throws IOException {
        session = new Session(biblioteca, printer, userInterface);
        when(userInterface.getUserInputString())
                .thenReturn("5")
                .thenReturn(("hamlet"))
                .thenReturn(null);
        when(biblioteca.returnItem(any()))
                .thenReturn(false);

        session.start();

        verify(printer).returnFailure();
    }



}