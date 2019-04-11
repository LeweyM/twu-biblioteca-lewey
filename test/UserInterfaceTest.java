import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserInterfaceTest {

    private BufferedReader bufferedReader;
    private UserInterface userInterface;

    @Before
    public void setUp() throws Exception {
        bufferedReader = mock(BufferedReader.class);
        userInterface = new UserInterface(bufferedReader);
    }

    @Test
    public void shouldReturnAString() throws IOException {
        when(bufferedReader.readLine()).thenReturn("testString");

        String userString = userInterface.getUserInputString();

        Assert.assertThat(userString, is("testString"));
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfNotInput() throws IOException {
        when(bufferedReader.readLine()).thenReturn(null);

        String userString = userInterface.getUserInputString();
    }

}