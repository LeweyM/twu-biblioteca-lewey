public class MenuItem {

    private String key;
    private String label;
    private ICommand command;

    public MenuItem(String key, String label, ICommand command) {
        this.key = key;
        this.label = label;
        this.command = command;
    }

    public String getLabel() {
        return label;
    }

    public ICommand getCommand() {
        return command;
    }

    public String getKey() {
        return this.key;
    }
}
