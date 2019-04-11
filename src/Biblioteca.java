import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {

    public List<LibraryItem> getItemsCheckedOut() {
        return itemsCheckedOut;
    }

    private List<LibraryItem> itemsCheckedOut;
    private List<LibraryItem> items;
    private final HashMap<Class, String[]> headers = new HashMap<>();

    public Biblioteca(List<LibraryItem> items) {
        this.itemsCheckedOut = new ArrayList<>();
        this.items = items;
        headers.put(Book.class, new String[]{"Title", "Author", "Date Published"});
        headers.put(Movie.class, new String[]{"Title", "Director", "Date Published", "Rating"});
    }

    public Optional<LibraryItem> findByTitleAndType(String title, Class c) {
        return items.stream()
                .filter(i -> i.getTitle().toLowerCase().equals(title.toLowerCase()))
                .filter(i -> i.getClass() == c)
                .findAny();
    }

    public boolean checkoutItem(LibraryItem item) throws NullPointerException {
        return items.contains(item) ? itemsCheckedOut.add(item) : false;
    }

    public boolean returnItem(LibraryItem item) {
        return itemsCheckedOut.remove(item);
    }

    public List<LibraryItem> getAvailableItemsByType(Class ItemClass) {
        return items
                .stream()
                .filter(item -> item.getClass() == ItemClass)
                .filter(item -> !itemsCheckedOut.contains(item))
//                .map(item -> (ItemClass) item)
                .collect(Collectors.toList());
    }

    public String[] getHeader(Class type) {
        return headers.get(type);
    }

}

