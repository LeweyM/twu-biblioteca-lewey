import java.util.Date;

public class Movie extends LibraryItem implements IColumnPrintable {

    private String director;

    public Movie(int id, String title, String director, Date datePublished) {
        super(id, title, datePublished);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }


    @Override
    public void addColumnLine(Column column) {
        column.addLine(super.getTitle(), getDirector(), super.getYearPublished());
    }

}
