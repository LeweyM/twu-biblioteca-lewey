import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    private DataInjector dataInjector = new DataInjector();
    Movie lordOfTheRingsMovie = dataInjector.getLordOfTheRingsMovie();
    Movie amelieMovie = dataInjector.getAmelieMovie();
    Book lordOfTheRingsBook = dataInjector.getLordOfTheRingsBook();
    Book bambieBook = dataInjector.getBambieBook();
    Book hamletBook = dataInjector.getHamletBook();
    User lewey = dataInjector.getLewey();
    User pau = dataInjector.getPau();
    List<User> users = dataInjector.getUsers();
    List<LibraryItem> items = dataInjector.getItems();

    private List<LibraryItem> getBooks() {
        List<LibraryItem> books;
        books = new ArrayList<>();
        books.add(lordOfTheRingsBook);
        books.add(bambieBook);
        books.add(hamletBook);
        return books;
    }

    private List<LibraryItem> getMovies() {
        List<LibraryItem> movies;
        movies = new ArrayList<>();
        movies.add(lordOfTheRingsMovie);
        movies.add(amelieMovie);
        return movies;
    }

    @Before
    public void setUp() {
    }

    @Test
    public void shouldCheckoutBook() {
        List<LibraryItem> books = getBooks();
        biblioteca = new Biblioteca(books);

        boolean checkout = biblioteca.checkoutItem(lordOfTheRingsBook);

        Assert.assertTrue(checkout);
        Assert.assertTrue(biblioteca.getItemsCheckedOut().contains(lordOfTheRingsBook));
    }

    @Test
    public void shouldReturnCheckedOutBook() {
        List<LibraryItem> books = getBooks();
        biblioteca = new Biblioteca(books);
        biblioteca.checkoutItem(lordOfTheRingsBook);

        boolean hasReturned = biblioteca.returnItem(lordOfTheRingsBook);

        Assert.assertTrue(hasReturned);
    }

    @Test
    public void shouldNotReturnBookIfAvailable() {
        List<LibraryItem> books = getBooks();
        biblioteca = new Biblioteca(books);

        boolean hasReturned = biblioteca.returnItem(lordOfTheRingsBook);

        Assert.assertFalse(hasReturned);
    }

    @Test
    public void shouldNotCheckoutWhenBookDoesNotExist() {
        biblioteca = new Biblioteca(getBooks());

        LibraryItem harryPotter = new Book(1, "Harry Potter", "Rowling", new Date());
        boolean checkout = biblioteca.checkoutItem(harryPotter);

        Assert.assertFalse(checkout);
        Assert.assertFalse(biblioteca.getItemsCheckedOut().contains(harryPotter));
    }

    @Test
    public void shouldGetAllBooks() {
        List<LibraryItem> items = getBooks();
        items.addAll(getMovies());
        biblioteca = new Biblioteca(items);

        List<LibraryItem> books = biblioteca.getAvailableItemsByType(Book.class);

        Assert.assertTrue(books.contains(hamletBook));
        Assert.assertTrue(books.contains(bambieBook));
        Assert.assertTrue(books.contains(lordOfTheRingsBook));
        Assert.assertFalse(books.contains(lordOfTheRingsMovie));
        Assert.assertFalse(books.contains(amelieMovie));
    }

    @Test
    public void shouldGetAllMovies() {
        List<LibraryItem> items = getBooks();
        items.addAll(getMovies());
        biblioteca = new Biblioteca(items);

        List<LibraryItem> movies = biblioteca.getAvailableItemsByType(Movie.class);

        Assert.assertTrue(movies.contains(amelieMovie));
        Assert.assertTrue(movies.contains(lordOfTheRingsMovie));
        Assert.assertFalse(movies.contains(lordOfTheRingsBook));
    }

    @Test
    public void shouldGetAllAvailableMovies() {
        List<LibraryItem> items = getBooks();
        items.addAll(getMovies());
        biblioteca = new Biblioteca(items);

        biblioteca.checkoutItem(amelieMovie);
        List<LibraryItem> movies = biblioteca.getAvailableItemsByType(Movie.class);

        Assert.assertFalse(movies.contains(amelieMovie));
        Assert.assertTrue(movies.contains(lordOfTheRingsMovie));
        Assert.assertFalse(movies.contains(lordOfTheRingsBook));
    }

    @Test
    public void shouldFindABookByTitle() {
        List<LibraryItem> items = getBooks();
        biblioteca = new Biblioteca(items);

        Optional<LibraryItem> foundItem = biblioteca.findByTitleAndType("bambi", Book.class);

        Assert.assertTrue(foundItem.isPresent());
        Assert.assertThat(foundItem.get(), is(bambieBook));
    }

    @Test
    public void shouldBeEmptyIfNotFound() {
        List<LibraryItem> items = getBooks();
        biblioteca = new Biblioteca(items);

        Optional<LibraryItem> foundItem = biblioteca.findByTitleAndType("bambi", Movie.class);

        Assert.assertFalse(foundItem.isPresent());
    }

    @Test
    public void shouldFindMovieByTitle() {
        List<LibraryItem> items = getBooks();
        items.addAll(getMovies());
        biblioteca = new Biblioteca(items);

        Optional<LibraryItem> foundItem = biblioteca.findByTitleAndType("lord of the rings", Movie.class);

        Assert.assertTrue(foundItem.isPresent());
        Assert.assertThat(foundItem.get(), is(lordOfTheRingsMovie));
    }
}



