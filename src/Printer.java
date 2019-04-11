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

    public void printItems(List<LibraryItem> items, String... headers) {
        Column column = new Column();
        column.addLine(headers);
        for (LibraryItem item : items) {
            item.addColumnLine(column);
        }
        this.out.println(column.getString());
    }

    public void menuOptions(List<MenuItem> menuItems) {
        this.out.println("Choose an option:");
        for (MenuItem menuItem : menuItems) {
            this.out.println(menuItem.getKey() + ": " + menuItem.getLabel());
        }
    }

    public void checkoutInstructions() {
        this.out.println("Enter title to checkoutItem item:");
    }

    public void checkoutSuccess() {
        this.out.println("Thank you! Enjoy the book.");
    }

    public void checkoutFailure() { this.out.print("Sorry, can't checkoutItem that book"); }

    public void bookAlreadyCheckedOut(Book book) {
        this.out.println("Sorry, \"" + book.getTitle() +  "\" is not available!");
    }

    public void invalidInput() {
        this.out.println("Please select a valid option!");
    }

    public void returnSuccess() {
        this.out.println("Thanks for returning the book!");
   }

    public void returnFailure() { this.out.println("That is not a valid book to return"); }

    public void returnInstructions() { this.out.println("Enter title to return book:"); }

    public void itemNotFound() { this.out.println("Sorry, we cannot find that item"); }

    public void quitMessage() { this.out.print("Thanks for using the biblioteca!"); }

}
