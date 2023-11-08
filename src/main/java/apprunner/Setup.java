package apprunner;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class Setup {

    private static final byte WAITING = 10;
    private AndroidDriver driver;
    private AppiumDriverLocalService service;

    public static UiAutomator2Options setUiAutomator2Options() {
        return new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2");
    }

    public AndroidDriver runAndroidDriver() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        try {
            URL remoteUrl = URI.create("http://0.0.0.0:4723").toURL();

            driver = new AndroidDriver(remoteUrl, setUiAutomator2Options());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAITING));

            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopDriver() {
        driver.quit();
        service.stop();
    }
}
