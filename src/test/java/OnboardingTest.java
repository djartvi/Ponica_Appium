import apprunner.App;
import apprunner.Console;
import apprunner.Setup;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;

import pom.OnboardingPage;

import java.io.IOException;

public class AndroidTest extends Base {

    @Test
    public void simpleTest() throws IOException, InterruptedException {
        Console.clearCache(app);
        Console.runApp(app);
        AndroidDriver driver = new Setup().runAndroidDriver();

//        new OnboardingPage(app)
//                .clickAcceptButton()
//                .clickContinueButton()
//                .clickContinueButton()
//                .clickContinueButton();
        System.out.println(new OnboardingPage(app, driver).isValidAcceptButtonText());
        System.out.println(new OnboardingPage(app, driver).isValidPrivacyText());

//        new PaywallPage(app).clickGooglePlay();
//
//        new Action().slowSwipeUp();

        Console.clearCache(app);

        Console.closeApp(app);
    }
}


