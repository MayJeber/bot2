package bot2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void shouldRespondOnFirstMessage() throws Exception {
        User user = new User("any name");
        assertTrue("Should respond on first message!", user.isResponseTime());
    }

    @Test
    public void shouldReactOnStartMessage1() throws Exception {
        User user = new User("any name");
        user.setResponseTime(false);
        user.add("blaahssadfsdfasdf start safsldfk");
        assertTrue("Should not respond to start if not a word!", user.isResponseTime());
    }

    @Test
    public void shouldReactOnStartMessage2() throws Exception {
        User user = new User("any name");
        user.setResponseTime(false);
        user.add("start blaahssadfsdfasdfstarsafsldfk");
        assertTrue("Should not respond to start if not a word!", user.isResponseTime());
    }

    @Test
    public void shouldReactOnStartMessage3() throws Exception {
        User user = new User("any name");
        user.setResponseTime(false);
        user.add("blaahssadfsdfasdfstarsafsldfk start");
        assertTrue("Should not respond to start if not a word!", user.isResponseTime());
    }

    @Test
    public void shouldNotReactOnStartHiddenInMessage() throws Exception {
        User user = new User("any name");
        user.setResponseTime(false);
        user.add("blaahssadfsdfasdfstartsafsldfk");
        assertFalse("Should not respond to start if not a word!", user.isResponseTime());
    }
}
