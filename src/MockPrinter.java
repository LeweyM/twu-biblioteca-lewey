import java.io.PrintStream;

public class MockPrinter extends Printer {

    private int printBooksCalledCount;
    private int welcomeCalledCount;


    private int printInvalidInputCalledCount;
    private int printMenuChoicesCalledCount;
    private int printCheckoutSuccessCalledCount;
    private int getBookAlreadyCheckedOutCalledCount;
    private int printReturnBookSuccessCalledCount;

    public MockPrinter(PrintStream printStream) {
        super(printStream);
        this.printBooksCalledCount = 0;
        this.welcomeCalledCount = 0;
        this.printInvalidInputCalledCount = 0;
        this.printMenuChoicesCalledCount = 0;
        this.printCheckoutSuccessCalledCount = 0;
        this.getBookAlreadyCheckedOutCalledCount = 0;
        this.printReturnBookSuccessCalledCount = 0;
    }

    public int getPrintReturnBookSuccessCalledCount() { return printReturnBookSuccessCalledCount; }

    public int getGetBookAlreadyCheckedOutCalledCount() { return getBookAlreadyCheckedOutCalledCount; }

    public int getPrintMenuChoicesCalledCount() { return printMenuChoicesCalledCount; }

    public int getPrintInvalidInputCalledCount() { return printInvalidInputCalledCount; }

    public int getPrintBooksCalledCount() { return printBooksCalledCount; }

    public int getWelcomeCalledCount() { return welcomeCalledCount; }

    public int getPrintCheckoutSuccessCalledCount() { return printCheckoutSuccessCalledCount; }

    @Override
    public void printBooks(Book[] books) {
        this.printBooksCalledCount++;
    }

    @Override
    public void welcome() {
        this.welcomeCalledCount++;
    }

    @Override
    public void printReturnBookSuccess() {
        this.printReturnBookSuccessCalledCount++;
    }

    @Override
    public void printMenuChoices() {
        this.printMenuChoicesCalledCount++;
    }

    @Override
    public void printCheckoutByIdInstructions() {
        super.printCheckoutByIdInstructions();
    }

    @Override
    public void printCheckoutSuccess() {
        this.printCheckoutSuccessCalledCount++;
    }

    @Override
    public void printInvalidInput() {
        this.printInvalidInputCalledCount++;
    }

    @Override
    public void bookAlreadyCheckedOut(Book book) {
        this.getBookAlreadyCheckedOutCalledCount++;
    }

}
