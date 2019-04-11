import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {

    public List<LibraryItem> getItemsCheckedOut() {
        return itemsCheckedOut;
    }

    private List<LibraryItem> itemsCheckedOut;
    private List<LibraryItem> items;

    public Biblioteca(List<LibraryItem> items) {
        this.itemsCheckedOut = new ArrayList<>();
        this.items = items;
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

}

