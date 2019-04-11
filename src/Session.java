import java.util.Optional;

public class Session {

    private final Biblioteca biblioteca;
    private final Printer printer;
    private final UserInterface userInterface;
    private UserManager userManager;

    private final Menu mainMenu = new MenuMain(this);
    private final Menu menuType = new MenuType(this);

    private boolean isAppRunning = true;
    private boolean userLoggedIn = false;
    private User user;

    public Session(Biblioteca biblioteca, Printer printer, UserInterface userInterface, UserManager userManager) {
        this.biblioteca = biblioteca;
        this.printer = printer;
        this.userInterface = userInterface;
        this.userManager = userManager;
    }

    public void start() {
        printer.welcome();
        while (!userLoggedIn) {
            login();
        }
        while (isAppRunning) {
            printer.menuOptions(mainMenu.getMenuItems());
            ICommand cmd = getMainMenuCommand();
            cmd.execute();
        }
    }

    public void login() {
        printer.enterLibraryNumber();
        Optional<User> userOptional = userManager.getUserByLibraryNumber(userInterface.getUserInputString());
        if (userOptional.isPresent()) {
            printer.enterPassword();
            String password = userInterface.getUserInputString();
            processLoginWithPassword(userOptional.get(), password);
        } else {
            userLoggedIn = false;
            printer.loginFailure();
        }

    }

    public void checkoutItem() {
        Class itemType = getItemTypeCommand().result();
        if (itemType != null) {
            printer.checkoutInstructions();
            Optional<LibraryItem> item = biblioteca.findByTitleAndType(userInterface.getUserInputString(), itemType);
            if (libraryHasItem(item)) attemptCheckout(item.get());
        };
    }

    public void returnItem() {
        Class itemType = getItemTypeCommand().result();
        if (itemType != null) {
            printer.returnInstructions();
            Optional<LibraryItem> item = biblioteca.findByTitleAndType(userInterface.getUserInputString(), itemType);
            if (libraryHasItem(item)) attemptReturn(item.get());
        };
    }

    public void listItems() {
        Class itemType = getItemTypeCommand().result();
        if (itemType != null) {
            printer.printItems(biblioteca.getAvailableItemsByType(itemType), biblioteca.getHeader(itemType));
        };
    }

    public void quit() {
        printer.quitMessage();
        this.isAppRunning = false;
    }

    private void processLoginWithPassword(User user, String password) {
        if (userManager.validatePassword(user, password)) {
            userLoggedIn = true;
            this.user = user;
            printer.loginSuccess();
        } else {
            userLoggedIn = false;
            printer.passwordFailure();
        }
        ;
    }

    private ICommand getMainMenuCommand() {
        return mainMenu.getCommand(userInterface.getUserInputString());
    };

    private ICommand<Class> getItemTypeCommand() {
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

    public boolean isAppRunning() {
        return isAppRunning;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

}

