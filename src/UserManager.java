import java.util.List;
import java.util.Optional;

public class UserManager {

    List<User> users;

    public UserManager(List<User> users) {
        this.users = users;
    }

    public Optional<User> getUserByPassword(String password) {
        return users.stream()
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    };
}
