import apprunner.Console;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import pom.OnboardingPage;

import java.io.IOException;

public class OnboardingTest extends Base {

    @Test
    public void runApp() throws IOException, InterruptedException {
        OnboardingPage onboardingPage = new OnboardingPage(app, driver);
        boolean PrivacyTextDisplayed = onboardingPage.isValidPrivacyText();

        onboardingPage.navigateBack();
        boolean appOnScreen = onboardingPage.isAppOnScreen(app);

        Console.runApp(app);
        boolean PrivacyTextDisplayed2 = onboardingPage.isValidPrivacyText();

        Console.closeApp(app);
        Console.runApp(app);
        boolean PrivacyTextDisplayed3 = onboardingPage.isValidPrivacyText();

        assertAll(
                () -> assertTrue(PrivacyTextDisplayed),
                () -> assertFalse(appOnScreen),
                () -> assertTrue(PrivacyTextDisplayed2),
                () -> assertTrue(PrivacyTextDisplayed3)
                );
    }
}


