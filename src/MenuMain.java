import java.util.ArrayList;
import java.util.List;

public class MenuMain extends Menu {

    private final ICommand defaultCommand;
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuMain(Session session) {
        super(session);

        MenuItem quit = new MenuItem("q", "Quit", new CommandQuit(session));
        MenuItem listBooks = new MenuItem("1", "List Items", new CommandListItems(session));
        MenuItem checkoutBook = new MenuItem("2", "Checkout Items", new CommandCheckoutItem(session));
        MenuItem returnBook = new MenuItem("3", "Return Item", new CommandReturnItem(session));

        defaultCommand = new CommandInvalidInput(session);

        menuItems.add(quit);
        menuItems.add(listBooks);
        menuItems.add(checkoutBook);
        menuItems.add(returnBook);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public ICommand getCommand(String key) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getKey().equals(key))
                .map(menuItem -> menuItem.getCommand())
                .findAny()
                .orElse(defaultCommand);
    }


}
