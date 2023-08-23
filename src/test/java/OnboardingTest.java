import apprunner.Console;
import apprunner.Setup;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import pom.CommonFunctions;
import pom.OnboardingPage;

import java.io.IOException;

public class OnboardingTest extends Base {

    @Test
    public void runApp() throws IOException, InterruptedException {
        Console.runApp(app);

        OnboardingPage onboardingPage = new OnboardingPage(app, driver);
        System.out.println(onboardingPage.isValidPrivacyText());
        System.out.println(onboardingPage.isValidAcceptButtonText());

//        onboardingPage.navigateBack();
//        boolean isAppOnScreen = onboardingPage.isAppOnScreen(app);
//
//        Console.runApp(app);
//        System.out.println(onboardingPage.isValidAcceptButtonText());
//
//        Console.closeApp(app);
    }
}


