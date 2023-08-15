package apprunner;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class Setup {

    private static final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void setDeviceCapabilities() {
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
    }

    public static void runDriver() {
        URL remoteUrl;

        try {
            remoteUrl = new URL("http://0.0.0.0:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

}
