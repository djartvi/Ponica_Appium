import apprunner.App;
import apprunner.Console;
import apprunner.Packages;

import apprunner.Setup;
import com.google.firebase.remoteconfig.Template;
import firebase.RemoteConfig;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pom.AdsPage;
import pom.MainMenuPage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static apprunner.Packages.PLAY_MARKET;

public class SimpleTest {

    static App app = new App();
    AndroidDriver driver;

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
        Console.runAppium();
//        Console.setFirebaseDebugView(app, true);
        System.out.println("Пакет: " + app.getPackageName());
        System.out.println("Версия: " + Console.getVersionName(app));
    }

    @BeforeEach
    public void runDriver() {
        driver = new Setup().runAndroidDriver();
    }

    @Test
    public void simpleTest() throws IOException, InterruptedException, ExecutionException {

        RemoteConfig remoteConfig = new RemoteConfig();
        Template template = remoteConfig.getCurrentTemplate();

        remoteConfig
                .setConditionAppVersion(template, "1.0")
                .setTemplateValues(template, "number_ads_impressions", "16")
                .publishConfig(template);
    }

    @Test
    public void simple2() {
        MainMenuPage mainMenuPage = new MainMenuPage(app, driver);
        mainMenuPage.skipTutorial();
    }


}
