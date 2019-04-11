
public class CommandReturnItem implements ICommand {

    private final Session session;

    public CommandReturnItem(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        this.session.returnItem();
    }

    @Override
    public Object result() {
        return null;
    }
}
