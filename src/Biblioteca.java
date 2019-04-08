import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public class Biblioteca {
    private static Book[] BOOKS = {
            new Book("Lord of the Rings", 0, "Tolkien", new Date()),
            new Book("Bambi", 1, "Disney", new Date()),
            new Book("Hamlet", 2, "Shakespeare", new Date()),
    };

    private final Printer printer;
    private BufferedReader bufferedReader;
    private boolean isAppRunning;
    private Book[] books;

    public static void main(String[] args) {
        Printer printer = new Printer(System.out);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Biblioteca biblioteca = new Biblioteca(bf, printer, BOOKS);

        biblioteca.start();
    }

    public Biblioteca(BufferedReader bufferedReader, Printer printer, Book[] books) {
        this.bufferedReader = bufferedReader;
        this.printer = printer;
        this.books = books;
    }

    public boolean isAppRunning() {
        return isAppRunning;
    }

    public void start() {
        isAppRunning = true;
        printer.welcome();
        printer.printMenuChoices();
        try {
            String userInput = getUserInputString();
            while (isAppRunning) {
                switch (userInput) {
                    case "1":
                        printer.printBooks(this.books);
                        break;
                    case "2":
                        checkoutByTitle();
                        break;
                    case "3":
                        returnBookByTitle();
                        break;
                    case "q":
                        isAppRunning = false;
                        return;
                    default:
                        printer.printInvalidInput();
                }
                printer.printMenuChoices();
                userInput = getUserInputString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnBookByTitle() throws IOException {
        printer.printReturnByTitleInstructions();

        Optional<Book> book = findBookByTitle(getUserInputString());

        if (book.isPresent()) {
            attemptReturn(book.get());
        } else {
            printer.printCannotFindBook();
        }
    }


    private void checkoutById() throws IOException {
        printer.printCheckoutByIdInstructions();
        Book book = findBookById(getUserInputNumber());

        attemptCheckout(book);
    }

    private void checkoutByTitle() throws IOException {
        printer.printCheckoutByTitleInstructions();

        Optional<Book> book = findBookByTitle(getUserInputString());

        if (book.isPresent()) {
            attemptCheckout(book.get());
        } else {
            printer.printCannotFindBook();
        }

    }

    private String getUserInputString() throws IOException {
        String inputString = bufferedReader.readLine();
        if (inputString == null) throw new IOException("needs an input");
        return inputString.toLowerCase();
    }

    private int getUserInputNumber() throws IOException {
        String s = bufferedReader.readLine();
        if (s == null) throw new IOException("needs an input");
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            printer.printInvalidInput();
            return getUserInputNumber();
        }
    }

    private void attemptReturn(Book book) {
        if (book.isCheckedOut()) {
            book.returnBook();
            printer.printReturnBookSuccess();
        } else {
            printer.printReturnBookFailure();
        }
    }

    private void attemptCheckout(Book book) {
        if (book.isCheckedOut()) {
            printer.bookAlreadyCheckedOut(book);
        } else {
            book.checkout();
            printer.printCheckoutSuccess(); }
    }

    private Book findBookById(int id) {
        return Arrays.stream(books).filter(book -> book.getId() == id).findAny().orElse(null);
    }

    private Optional<Book> findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) { return Optional.of(book); }
        }
        return Optional.empty();
    }



}

