import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;


public class UserManagerTest {

    @Test
    public void shouldGetUserOptionalWithPassword() {
        List<User> users = new ArrayList<>();
        User lewey = new User("Lewey", "1114444");
        User pau = new User("Pau", "9995555");
        users.add(lewey);
        users.add(pau);
        UserManager userManager = new UserManager(users);

        User user = userManager.getUserByPassword("1114444").get();

        Assert.assertThat(user, is(lewey));
    }

    @Test
    public void shouldReturnEmptyOptionalWithBadPassword() {
        List<User> users = new ArrayList<>();
        User lewey = new User("Lewey", "1114444");
        User pau = new User("Pau", "9995555");
        users.add(lewey);
        users.add(pau);
        UserManager userManager = new UserManager(users);

        Optional<User> user = userManager.getUserByPassword("bad9999");

        Assert.assertFalse(user.isPresent());
    }

}