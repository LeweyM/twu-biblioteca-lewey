import java.io.BufferedReader;
import java.io.IOException;

public class UserInterface {

    private BufferedReader bufferedReader;

    public UserInterface(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getUserInputString() throws IOException {
        String inputString = bufferedReader.readLine();
        if (inputString == null) throw new IOException("needs an input");
        return inputString;
    }
}
