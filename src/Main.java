import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    static List<LibraryItem> items = new ArrayList<>();
    static Movie lordOfTheRingsMovie = new Movie(0, "Lord of the Rings", "Tolkien", new Date());
    static Movie amelieMovie = new Movie(8, "Amelie", "Bonjour", new Date());
    static Book lordOfTheRingsBook = new Book(0, "Lord of the Rings", "Tolkien", new Date());
    static Book bambieBook = new Book(1, "Bambi", "Disney", new Date());
    static Book hamletBook = new Book(2, "Hamlet", "Shakespeare", new Date());

    public static void main(String[] args) {
        items.add(lordOfTheRingsMovie);
        items.add(lordOfTheRingsBook);
        items.add(amelieMovie);
        items.add(bambieBook);
        items.add(hamletBook);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInterface userInterface = new UserInterface(bufferedReader);

        Printer printer = new Printer(System.out);
        Biblioteca biblioteca = new Biblioteca(items);

        Session session = new Session(biblioteca, printer, userInterface);

        session.start();
    }
}
