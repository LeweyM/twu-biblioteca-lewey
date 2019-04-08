import java.util.Date;

public class Book {

    private int id;
    private String title;
    private String author;
    private Date datePublished;
    private boolean isCheckedOut = false;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public String getYearPublished() {
        String dateString = datePublished.toString();
        return dateString.substring(dateString.length() - 4);
    }

    public Book(String title, int id, String author, Date datePublished) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
    }

    public void checkout() {
        this.isCheckedOut = true;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void returnBook() {
        this.isCheckedOut = false;
    }
}
