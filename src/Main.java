import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    static DataInjector dataInjector = new DataInjector();

    static List<LibraryItem> items = dataInjector.getItems();
    static List<User> users = dataInjector.getUsers();

    public static void main(String[] args) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInterface userInterface = new UserInterface(bufferedReader);

        UserManager userManager = new UserManager(users);

        Printer printer = new Printer(System.out);
        Biblioteca biblioteca = new Biblioteca(items);

        Session session = new Session(biblioteca, printer, userInterface, userManager);

        session.start();
    }
}
