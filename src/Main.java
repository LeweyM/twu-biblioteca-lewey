import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {

    private static Book[] BOOKS = {
            new Book("Lord of the Rings", 0, "Tolkien", new Date()),
            new Book("Bambi", 1, "Disney", new Date()),
            new Book("Hamlet", 2, "Shakespeare", new Date()),
    };

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInterface userInterface = new UserInterface(bufferedReader);

        Printer printer = new Printer(System.out);
        Biblioteca biblioteca = new Biblioteca(BOOKS);

        Session session = new Session(biblioteca, printer, userInterface);

        session.start();
    }
}
