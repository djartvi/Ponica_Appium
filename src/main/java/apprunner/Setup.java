package apprunner;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class Setup {

    private static final byte WAITING = 10;
    private static final DesiredCapabilities DESIRED_CAPABILITIES = new DesiredCapabilities();

    public static void setDeviceCapabilities() {
        DESIRED_CAPABILITIES.setCapability("platformName", "Android");
        DESIRED_CAPABILITIES.setCapability("appium:ensureWebviewsHavePages", true);
        DESIRED_CAPABILITIES.setCapability("appium:nativeWebScreenshot", true);
        DESIRED_CAPABILITIES.setCapability("appium:connectHardwareKeyboard", true);
//        DESIRED_CAPABILITIES.setCapability("appium:automationName", "UiAutomator2");
    }

    public AndroidDriver runAndroidDriver() {
        URL remoteUrl;

        try {
            remoteUrl = new URL("http://0.0.0.0:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        AndroidDriver driver = new AndroidDriver(remoteUrl, DESIRED_CAPABILITIES);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAITING));

        return driver;
    }

}
