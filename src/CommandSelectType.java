public class CommandSelectType implements ICommand {
    private final Class c;
    private Class selectedType;

    public CommandSelectType(Class c) {
        this.selectedType = null;
        this.c = c;
    }

    @Override
    public void execute() {
        this.selectedType = c;
    }

    public Class result() {
        return this.selectedType;
    }
}
