import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class Printer {

    PrintStream out;

    public Printer(PrintStream printStream) {
        this.out = printStream;
    }


    public void welcome() {
        this.out.println("Welcome to Biblioteca, your one-stop-shop for great books in Bangalor!");
    }

    public void printBooks(List<Book> books) {
        Column column = new Column();
        column.addLine("Title", "Author", "ID", "Year Published");
        for (Book book : books) {
            column.addLine(book.getTitle(), book.getAuthor(), Integer.toString(book.getId()), book.getYearPublished());
        }
        this.out.println(column.getString());
    }

    public void menuOptions() {
        this.out.println("Choose an option:");
        this.out.println("1: List of Books");
        this.out.println("2: Checkout a Book");
        this.out.println("3: Return a Book");
        this.out.println("Q: Quit");
    }

    public void printCheckoutByIdInstructions() {
        this.out.println("Enter id to checkout book:");
    }

    public void checkoutInstructions() {
        this.out.println("Enter title to checkout book:");
    }

    public void checkoutSuccess() {
        this.out.println("Thank you! Enjoy the book.");
    }

    public void checkoutFailure() { this.out.print("Sorry, can't checkout that book"); }

    public void bookAlreadyCheckedOut(Book book) {
        this.out.println("Sorry, \"" + book.getTitle() +  "\" is not available!");
    }

    public void invalidInput() {
        this.out.println("Please select a valid option!");
    }

    public void returnSuccess() {
        this.out.println("Thanks for returning the book!");
   }

    public void returnInstructions() {
        this.out.println("Enter title to return book:");
    }

    public void bookNotFound() {
        this.out.println("Sorry, we cannot find that book");
    }

    public void returnFailure() {
        this.out.println("That is not a valid book to return");
    }

}
