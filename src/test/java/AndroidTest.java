import apprunner.App;
import apprunner.Console;
import apprunner.Setup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pom.Action;
import pom.OnboardingPage;
import pom.PaywallPage;

import java.io.IOException;

public class AndroidTest {

    static App app = new App();

    @BeforeAll
    public static void setup() throws IOException, InterruptedException {
//        Console.runAppium();
//        Console.setFirebaseDebugView(app, true);
        Setup.setDeviceCapabilities();
        Setup.runDriver();
    }

    @AfterEach
    public void tearDown() {
        Setup.getDriver().quit();
    }

    @AfterAll
    public static void stopAppium() throws IOException, InterruptedException {
//        Console.stopAppium();
//        Console.setFirebaseDebugView(app, false);
    }

    @Test
    public void simpleTest() throws IOException, InterruptedException {
        Console.clearCache(app);
        Console.runApp(app);

//        new OnboardingPage(app)
//                .clickAcceptButton()
//                .clickContinueButton()
//                .clickContinueButton()
//                .clickContinueButton();
        System.out.println(new OnboardingPage(app).isValidAcceptButtonText());

//        new PaywallPage(app).clickGooglePlay();
//
//        new Action().slowSwipeUp();

        Console.clearCache(app);

        Console.closeApp(app);
    }
}


