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

import java.io.IOException;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class AndroidTest {

    Console console = new Console();
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
        console.clearCache(app);
        console.runApp(app);

        Onboarding onboarding = new Onboarding(app);
        onboarding
                .clickAcceptButton()
                    .clickContinueButton()
                        .clickContinueButton()
                            .clickContinueButton();

        MainMenu mainMenu = new MainMenu(app);
        mainMenu.clickCrownButton();


//            new TouchAction(Setup.getDriver())
//                    .press(point(919,2210))
//                    .waitAction(waitOptions(ofSeconds(5)))
//                    .release()
//                    .perform();

        console.clearCache(app);

//        Console.closeApp();

//        new WebDriverWait(driver, Duration.ofSeconds(7000))
//                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("com.android.chrome:id/search_box_text"))));
//        driver.findElement(By.id("com.android.chrome:id/search_box_text")).sendKeys("какие ставки");
//        Thread.sleep(5000);

    }
}


