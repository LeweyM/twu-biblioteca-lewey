public class CommandQuit implements ICommand {

    private Session session;

    public CommandQuit(Session session) {
        this.session = session;
    }


    @Override
    public void execute() {
        this.session.quit();
    }

    @Override
    public Object result() {
        return null;
    }
}
