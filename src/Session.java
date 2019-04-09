import java.io.IOException;
import java.util.Optional;

public class Session {

    private final Biblioteca biblioteca;
    private boolean isAppRunning;
    private Printer printer;
    private UserInterface userInterface;

    public Session(Biblioteca biblioteca, Printer printer, UserInterface userInterface) {
        this.biblioteca = biblioteca;
        this.printer = printer;
        this.userInterface = userInterface;
    }

    public void start() {
            isAppRunning = true;
            printer.welcome();
            printer.menuOptions();
            try {
                String userInput = userInterface.getUserInputString();
                while (isAppRunning) {
                    switch (userInput) {
                        case "1":
                            printer.printBooks(biblioteca.getAvailableBooks());
                            break;
                        case "2":
                            checkoutBook();
                            break;
                        case "3":
                            returnBook();
                            break;
                        case "q":
                            isAppRunning = false;
                            return;
                        default:
                            printer.invalidInput();
                    }
                    printer.menuOptions();
                    userInput = userInterface.getUserInputString();
                }

            } catch (IOException e) {
//            e.printStackTrace();
            }
    }

    private void returnBook() throws IOException {
        printer.returnInstructions();
        Optional<Book> bookOptional = findBookByTitle();
        if (bookOptional.isPresent()) {
            attemptReturnBook(bookOptional);
        }
    }

    private void attemptReturnBook(Optional<Book> bookOptional) {
        boolean success = biblioteca.returnBook(bookOptional.get());
        if (success) {
            printer.returnSuccess();
        } else {
            printer.returnFailure();
        }
    }

    private Optional<Book> findBookByTitle() throws IOException {
        Optional<Book> bookOptional = biblioteca.findBookByTitle(userInterface.getUserInputString());

        if (!bookOptional.isPresent()) {
            printer.bookNotFound();
        }
        return bookOptional;
    }

    private void checkoutBook() throws IOException {
        printer.checkoutInstructions();
        Optional<Book> bookOptional = findBookByTitle();
        if (bookOptional.isPresent()) {
            if (biblioteca.checkout(bookOptional.get())) {
                printer.checkoutSuccess();
            } else {
                printer.checkoutFailure();
            }
        }
    }

}

