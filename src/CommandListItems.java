public class CommandListItems implements ICommand {

    private Session session;

    public CommandListItems(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        session.listItems();
    }

    @Override
    public Object result() {
        return null;
    }
}
