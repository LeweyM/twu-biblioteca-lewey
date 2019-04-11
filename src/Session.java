import java.util.HashMap;
import java.util.Optional;

public class Session {

    private final Biblioteca biblioteca;
    private final Printer printer;
    private final UserInterface userInterface;
    private boolean isAppRunning = true;
    private final HashMap<Class, String[]> headers = new HashMap<>();

    private final Menu mainMenu = new MenuMain(this);
    private final Menu menuType = new MenuType(this);

    public Session(Biblioteca biblioteca, Printer printer, UserInterface userInterface) {
        this.biblioteca = biblioteca;
        this.printer = printer;
        this.userInterface = userInterface;
        headers.put(Book.class, new String[]{"Title", "Author", "Date Published"});
        headers.put(Movie.class, new String[]{"Title", "Director", "Date Published"});
    }

    public void start() {
        printer.welcome();
        while (isAppRunning) {
                printer.menuOptions(mainMenu.getMenuItems());
                ICommand cmd = getMainMenuCommand();
                cmd.execute();
            }
    }

    private ICommand getMainMenuCommand() {
        return mainMenu.getCommand(userInterface.getUserInputString());
    };


    public void checkoutItem() {
        Class itemType = (Class)getItemTypeCommand().result();
        if (itemType != null) {
            printer.checkoutInstructions();
            Optional<LibraryItem> item = biblioteca.findByTitleAndType(userInterface.getUserInputString(), itemType);
            if (libraryHasItem(item)) attemptCheckout(item.get());
        };
    }

    public void returnItem() {
        Class itemType = (Class)getItemTypeCommand().result();
        if (itemType != null) {
            printer.returnInstructions();
            Optional<LibraryItem> item = biblioteca.findByTitleAndType(userInterface.getUserInputString(), itemType);
            if (libraryHasItem(item)) attemptReturn(item.get());
        };
    }

    public void listItems() {
        Class itemType = (Class)getItemTypeCommand().result();
        if (itemType != null) {
            printer.printItems(biblioteca.getAvailableItemsByType(itemType), headers.get(itemType));
        };
    }

    public void quit() {
        printer.quitMessage();
        this.isAppRunning = false;
    }

    public boolean isAppRunning() {
        return isAppRunning;
    }

    private ICommand getItemTypeCommand() {
        printer.menuOptions(menuType.getMenuItems());
        ICommand<Class> command = menuType.getCommand(userInterface.getUserInputString());
        command.execute();
        return command;
    }

    private void attemptCheckout(LibraryItem item) {
        if (biblioteca.checkoutItem(item)) {
            printer.checkoutSuccess();
        } else {
            printer.checkoutFailure();
        }
    }

    private void attemptReturn(LibraryItem item) {
        if (biblioteca.returnItem(item)) {
            printer.returnSuccess();
        } else {
            printer.returnFailure();
        }
    }

    private boolean libraryHasItem(Optional<LibraryItem> item) {
        if (item.isPresent()) {
            return true;
        } else {
            printer.itemNotFound();
            return false;
        }
    }

    public void invalidInput() {
        printer.invalidInput();
    }
}

