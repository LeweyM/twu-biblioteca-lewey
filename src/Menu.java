import java.util.List;
public abstract class Menu {

    public Menu(Session session) {
    }

    public List<MenuItem> getMenuItems() {
        return null;
    }

    public ICommand getCommand(String key) {
        return null;
    }
}
