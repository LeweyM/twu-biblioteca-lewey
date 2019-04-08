import java.io.PrintStream;
import java.util.Arrays;

public class Printer {

    PrintStream out;

    public Printer(PrintStream baos) {
        this.out = baos;
    }


    public void welcome() {
        String welcomeString = "Welcome to Biblioteca, your one-stop-shop for great books in Bangalor!";
        this.out.println(welcomeString);
    }

    public void printBooks(Book[] books) {
        Column column = new Column();
        column.addLine("Title", "Author", "ID", "Year Published");
        for (Book book : Arrays.stream(books).filter(book -> !book.isCheckedOut()).toArray(Book[]::new)) {
            column.addLine(book.getTitle(), book.getAuthor(), Integer.toString(book.getId()), book.getYearPublished());
        }
        this.out.println(column.getString());
    }

    public void printMenuChoices() {
        this.out.println("Choose an option:");
        this.out.println("1: List of Books");
        this.out.println("2: Checkout a Book");
        this.out.println("3: Return a Book");
        this.out.println("Q: Quit");
    }

    public void printCheckoutByIdInstructions() {
        this.out.println("Enter id to checkout book:");
    }

    public void printCheckoutByTitleInstructions() {
        this.out.println("Enter title to checkout book:");
    }

    public void printCheckoutSuccess() {
        this.out.println("Thank you! Enjoy the book.");
    }

    public void bookAlreadyCheckedOut(Book book) {
        this.out.println("Sorry, \"" + book.getTitle() +  "\" is not available!");
    }

    public void printInvalidInput() {
        this.out.println("Please select a valid option!");
    }

    public void printReturnBookSuccess() {
        this.out.println("Thanks for returning the book!");
   }

    public void printReturnByTitleInstructions() {
        this.out.println("Enter title to return book:");
    }

    public void printCannotFindBook() {
        this.out.println("Sorry, we cannot find that book");
    }

    public void printReturnBookFailure() {
        this.out.println("That is not a valid book to return");
    }
}
