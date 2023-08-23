import apprunner.App;
import apprunner.Console;
import apprunner.Setup;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class Base {

    static App app = new App();
    AndroidDriver driver;

    boolean isTrue(boolean isTrue) {
        return isTrue;
    }

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
        Console.runAppium();
        Console.setFirebaseDebugView(app, true);
        Setup.setDeviceCapabilities();
        System.out.println(Console.getVersionName(app));
    }

    @BeforeEach
    public void runDriver() throws IOException, InterruptedException {
        driver = new Setup().runAndroidDriver();
        Console.clearCache(app);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void stopAppium() throws IOException, InterruptedException {
        Console.stopAppium();
        Console.setFirebaseDebugView(app, false);
    }
}
