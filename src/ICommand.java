public interface ICommand<T> {

    void execute();

    T result();
}
