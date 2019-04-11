import java.util.Date;

public class Book extends LibraryItem implements IColumnPrintable {

    private String author;

    public Book(int id, String title, String author, Date datePublished) {
        super(id, title, datePublished);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void addColumnLine(Column column) {
        column.addLine(super.getTitle(), getAuthor(), super.getYearPublished());
    }

}
