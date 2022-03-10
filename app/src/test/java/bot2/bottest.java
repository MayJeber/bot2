package bot2;

import org.junit.Test;

public class bottest {
    @Test
    public void shouldDOSomething() throws Exception {
        new Commands();
    }


    @Test
    public void shouldGiveMeJoke() throws Exception {
        Commands aCommand = new Commands();
        aCommand.myJoke();
    }
    @Test
    public void shouldNotGiveMeTheSameJok() throws Exception{
        Commands aCommand = new Commands();

    }

}
