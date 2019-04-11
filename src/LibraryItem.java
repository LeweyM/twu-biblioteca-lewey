import java.util.Date;

public abstract class LibraryItem implements IColumnPrintable {

    private int id;
    private String title;
    private Date datePublished;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYearPublished() {
        String dateString = datePublished.toString();
        return dateString.substring(dateString.length() - 4);
    }

    public LibraryItem(int id, String title, Date datePublished) {
        this.id = id;
        this.title = title;
        this.datePublished = datePublished;
    }

}
