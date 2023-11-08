import apprunner.Ads;
import apprunner.App;
import apprunner.Console;
import apprunner.Setup;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static apprunner.Packages.PLAY_MARKET;

public class Base {

    static App app = new App();
    static Ads ads = new Ads(app);
    Setup setup = new Setup();
    AndroidDriver driver;

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
        Console.runAppium();
//        Console.setFirebaseDebugView(app, true);
        System.out.println("Пакет: " + app.getPackageName());
        System.out.println("Версия: " + Console.getVersionName(app));
    }

    @BeforeEach
    public void runDriver() throws IOException, InterruptedException {
        driver = setup.runAndroidDriver();
        Console.clearCache(app.getPackageName());
        Console.clearCache(PLAY_MARKET);
        Console.runApp(app);
    }

    @AfterEach
    public void tearDown() {
        setup.stopDriver();
    }

    @AfterAll
    public static void stopAppium() throws IOException, InterruptedException {
        Console.stopAppium();
//        Console.setFirebaseDebugView(app, false);
    }
}
