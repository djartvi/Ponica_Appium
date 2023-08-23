import apprunner.App;
import apprunner.Console;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class InstallTest {

    App app = new App();

    @Test
    public void installApp() throws IOException, InterruptedException {
        Console.installApp(app);
    }
}
