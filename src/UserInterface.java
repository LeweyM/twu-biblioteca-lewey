import java.io.BufferedReader;
import java.io.IOException;

public class UserInterface {

    private BufferedReader bufferedReader;

    public UserInterface(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getUserInputString()  {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
