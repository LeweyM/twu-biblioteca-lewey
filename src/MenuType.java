import java.util.ArrayList;
import java.util.List;

public class MenuType extends Menu {
    private final ICommand defaultCommand;
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuType(Session session) {
        super(session);

        MenuItem book = new MenuItem("b", "Books", new CommandSelectType(Book.class));
        MenuItem movie = new MenuItem("m", "Movies", new CommandSelectType(Movie.class));

        defaultCommand = new CommandInvalidInput(session);

        menuItems.add(book);
        menuItems.add(movie);
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
