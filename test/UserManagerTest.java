import org.junit.Assert;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;


public class UserManagerTest {

    DataInjector dataInjector = new DataInjector();
    List<User> users = dataInjector.getUsers();
    User lewey = dataInjector.getLewey();
    User pau = dataInjector.getPau();


    @Test
    public void shouldGetUserOptionalWithGoodLibraryNumber() {
        UserManager userManager = new UserManager(users);

        User user = userManager.getUserByLibraryNumber("1114444").get();

        Assert.assertThat(user, is(lewey));
    }

    @Test
    public void shouldReturnEmptyOptionalWithBadLibraryNumber() {
        UserManager userManager = new UserManager(users);

        Optional<User> user = userManager.getUserByLibraryNumber("bad9999");

        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void validatePasswordShouldValidateGoodPassword() {
        UserManager userManager = new UserManager(users);

        boolean passwordSuccess = userManager.validatePassword(lewey, lewey.getPassword());

        Assert.assertTrue(passwordSuccess);
    }

    @Test
    public void validatePasswordShouldNotValidateBadPassword() {
        UserManager userManager = new UserManager(users);

        boolean passwordSuccess = userManager.validatePassword(lewey, "BaDpAsSwOrD");

        Assert.assertFalse(passwordSuccess);
    }
}