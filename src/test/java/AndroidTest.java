import apprunner.App;
import apprunner.Console;
import apprunner.Setup;
import io.appium.java_client.TouchAction;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.io.input.TaggedReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pom.MainMenu;
import pom.Onboarding;
import pom.Paywall;

import java.io.IOException;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class AndroidTest {

    App app = new App();

    @BeforeAll
    public static void setup() {
        Console.runAppium();
        Setup.setDeviceCapabilities();
        Setup.runDriver();
    }

    @AfterEach
    public void tearDown() {
        Setup.getDriver().quit();
    }

    @AfterAll
    public static void stopAppium() {
        Console.stopAppium();
    }

    @Test
    public void sampleTest() throws IOException, InterruptedException {
//        Console.clearCache(app);
        Console.runApp(app);

//        Onboarding onboarding = new Onboarding(app);
//        onboarding
//                .clickAcceptButton()
//                .clickContinueButton()
//                .clickContinueButton()
//                .clickContinueButton();


        new MainMenu(app).clickCrownButton();
        new Paywall(app).clickGooglePlay();



//        Console.clearCache(app);

//        Console.closeApp();

//        new WebDriverWait(driver, Duration.ofSeconds(7000))
//                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("com.android.chrome:id/search_box_text"))));
//        driver.findElement(By.id("com.android.chrome:id/search_box_text")).sendKeys("какие ставки");
//        Thread.sleep(5000);

    }
}


