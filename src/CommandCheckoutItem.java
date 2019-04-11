public class CommandCheckoutItem implements ICommand {

    private final Session session;

    public CommandCheckoutItem(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        this.session.checkoutItem();
    }

    @Override
    public Object result() {
        return null;
    }
}
