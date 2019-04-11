public class CommandInvalidInput implements ICommand {

    private Session session;

    public CommandInvalidInput(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        session.invalidInput();
    }

    @Override
    public Object result() {
        return null;
    }
}
