import apprunner.App;
import apprunner.Console;
import apprunner.Packages;

import apprunner.Setup;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pom.AdsPage;

import java.io.IOException;

import static apprunner.Packages.PLAY_MARKET;

public class SimpleTest {

    static App app = new App();
    AndroidDriver driver;

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
        Console.runAppium();
//        Console.setFirebaseDebugView(app, true);
        Setup.setDeviceCapabilities();
        System.out.println("Пакет: " + app.getPackageName());
        System.out.println("Версия: " + Console.getVersionName(app));
    }

    @BeforeEach
    public void runDriver() throws IOException, InterruptedException {
        driver = new Setup().runAndroidDriver();
    }

//    @Test
//    public void simpleTest() throws IOException, InterruptedException, ExecutionException {
//
//        RemoteConfig.initialize();
//        Template template = RemoteConfig.getCurrentTemplate();
//        RemoteConfig.setConditionAppVersion(template, "1.0");
//        RemoteConfig.setTemplateValues(template, "number_ads_impressions", "16");
//        RemoteConfig.publishConfig(template);
//}

    @Test
    public void simple2() throws InterruptedException {
//        AdsPage adsPage = new AdsPage(app, ads, driver);
//        boolean isInterOnScreen = adsPage.isInterOnScreen();
//
////        adsPage.openAdd();
//        adsPage.clickCloseButton();
//
//        System.out.println(isInterOnScreen);

    }

}
