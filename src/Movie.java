import java.util.Date;

public class Movie extends LibraryItem implements IColumnPrintable {

    private String director;
    private final Integer rating;

    public Integer getRating() {
        return rating;
    }

    public Movie(int id, String title, String director, Date datePublished, Integer rating) {
        super(id, title, datePublished);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }


    @Override
    public void addColumnLine(Column column) {
        column.addLine(super.getTitle(), getDirector(), super.getYearPublished(), getRating().toString());
    }

}
