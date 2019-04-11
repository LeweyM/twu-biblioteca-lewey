import java.util.List;
import java.util.Optional;

public class UserManager {

    List<User> users;
    // Passwords should not be passed to session and handled only in the userManager class.
//    HashMap<User, String> passwords;

    public UserManager(List<User> users) {
        this.users = users;
    }

    public Optional<User> getUserByLibraryNumber(String libraryNumber) {
        return users.stream()
                .filter(user -> user.getLibraryNumber().equals(libraryNumber))
                .findFirst();
    };

    public boolean validatePassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
