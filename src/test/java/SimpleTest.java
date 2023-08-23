import apprunner.App;
import apprunner.Console;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SimpleTest {

    App app = new App();

    @Test
    public void simpleTest() throws IOException, InterruptedException {

        System.out.println(app.getPackageName());
//
        System.out.println(Console.getVersionName(app));
    }

}
