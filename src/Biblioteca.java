import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Biblioteca {

    private List<Book> books;
    private HashMap<Integer, Boolean> checkedOutBooks;

    public Biblioteca(Book[] books) {
        this.checkedOutBooks = new HashMap<>();
        this.books = loadBooks(books);
    }

    public Optional<Book> findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) { return Optional.of(book); }
        }
        return Optional.empty();
    }

    public boolean isCheckedOut(Book book) {
        return this.checkedOutBooks.get(book.getId());
    };

    public boolean checkout(Book book) throws NullPointerException {
        if (!bookExists(book)) throw new NullPointerException("Book does not exist in checkedOut hash");
        if (isCheckedOut(book)) {
            return false;
        } else {
            this.checkedOutBooks.put(book.getId(), true);
            return true;
        }
    }

    public boolean returnBook(Book book) {
        if (!bookExists(book)) throw new NullPointerException("Book does not exist in checkedOut hash");
        if (isCheckedOut(book)) {
            this.checkedOutBooks.put(book.getId(), false);
            return true;
        } else {
            return false;
        }
    }

    public List<Book> getAvailableBooks() {
        Stream<Book> bookStream = this.books.stream().filter(book -> !checkedOutBooks.get(book.getId()));
        List<Book> list = bookStream.collect(Collectors.toList());
        return list;
    }

    private List<Book> loadBooks(Book[] books) {
        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(books));
        for (Book book : bookList) {
            this.checkedOutBooks.put(book.getId(), false);
        }
        return bookList;
    }

    private boolean bookExists(Book book) {
        return this.checkedOutBooks.containsKey(book.getId());
    }
}

