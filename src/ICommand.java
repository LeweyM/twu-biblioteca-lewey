public interface ICommand<T> {

    public void execute();

    public T result();
}
