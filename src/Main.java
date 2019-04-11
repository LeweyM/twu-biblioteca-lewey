import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    static List<LibraryItem> items = new ArrayList<>();
    static Movie lordOfTheRingsMovie = new Movie(0, "Lord of the Rings", "Tolkien", new Date(), 4);
    static Movie amelieMovie = new Movie(8, "Amelie", "Bonjour", new Date(), 8);
    static Book lordOfTheRingsBook = new Book(0, "Lord of the Rings", "Tolkien", new Date());
    static Book bambieBook = new Book(1, "Bambi", "Disney", new Date());
    static Book hamletBook = new Book(2, "Hamlet", "Shakespeare", new Date());

    public static void main(String[] args) {
        items.add(lordOfTheRingsMovie);
        items.add(lordOfTheRingsBook);
        items.add(amelieMovie);
        items.add(bambieBook);
        items.add(hamletBook);

        List<User> users = new ArrayList<>();
        User lewey = new User("Lewey", "1114444");
        User pau = new User("Pau", "9995555");
        users.add(lewey);
        users.add(pau);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInterface userInterface = new UserInterface(bufferedReader);

        UserManager userManager = new UserManager(users);

        Printer printer = new Printer(System.out);
        Biblioteca biblioteca = new Biblioteca(items);

        Session session = new Session(biblioteca, printer, userInterface, userManager);

        session.start();
    }
}
