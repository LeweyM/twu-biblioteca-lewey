import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataInjector {

    List<LibraryItem> items = new ArrayList<>();

    Movie lordOfTheRingsMovie = new Movie(0, "Lord of the Rings", "Tolkien", new Date(), 4);
    Movie amelieMovie = new Movie(8, "Amelie", "Bonjour", new Date(), 8);
    Book lordOfTheRingsBook = new Book(0, "Lord of the Rings", "Tolkien", new Date());
    Book bambieBook = new Book(1, "Bambi", "Disney", new Date());
    Book hamletBook = new Book(2, "Hamlet", "Shakespeare", new Date());

    User lewey = new User("Lewey", "1114444");
    User pau = new User("Pau", "9995555");

    public List<User> getUsers() {
        return users;
    }

    List<User> users = new ArrayList<>();

    public DataInjector() {
        items.add(lordOfTheRingsMovie);
        items.add(lordOfTheRingsBook);
        items.add(amelieMovie);
        items.add(bambieBook);
        items.add(hamletBook);

        users.add(lewey);
        users.add(pau);

    }

    public Movie getLordOfTheRingsMovie() {
        return lordOfTheRingsMovie;
    }

    public Movie getAmelieMovie() {
        return amelieMovie;
    }

    public Book getLordOfTheRingsBook() {
        return lordOfTheRingsBook;
    }

    public Book getBambieBook() {
        return bambieBook;
    }

    public Book getHamletBook() {
        return hamletBook;
    }

    public User getLewey() {
        return lewey;
    }

    public User getPau() {
        return pau;
    }

    public List<LibraryItem> getItems() {
        return items;
    }
}
