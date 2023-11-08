import apprunner.Console;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import pom.AdsPage;
import pom.MainMenuPage;
import pom.OnboardingPage;
import pom.PaywallPage;

import java.io.IOException;

public class OnboardingTest extends Base {

    OnboardingPage onboardingPage;
    AdsPage adsPage;
    MainMenuPage mainMenuPage;
    PaywallPage paywallPage;

    @BeforeEach
    public void initialise() {
        onboardingPage = new OnboardingPage(app, driver);
        adsPage = new AdsPage(app, ads, driver);
        mainMenuPage = new MainMenuPage(app, driver);
        paywallPage = new PaywallPage(app, driver);
    }

    @Test
    public void runCollapseCloseTest() throws IOException, InterruptedException {
        boolean PrivacyTextDisplayed = onboardingPage.isValidPrivacyText();

        onboardingPage.navigateBack();
        boolean appOnScreen = onboardingPage.isAppOnScreen(app);

        Console.runApp(app);
        boolean PrivacyTextDisplayed2 = onboardingPage.isValidPrivacyText();

        Console.closeApp(app);
        Console.runApp(app);
        boolean PrivacyTextDisplayed3 = onboardingPage.isValidPrivacyText();

        assertAll(
                () -> assertTrue(PrivacyTextDisplayed, "Отображение политики конфиденциальности при первом запуске"),
                () -> assertFalse(appOnScreen, "Приложение сворачивается, если нажать 'назад'"),
                () -> assertTrue(PrivacyTextDisplayed2, "Повторный показ онбординга после сворачивания"),
                () -> assertTrue(PrivacyTextDisplayed3, "Повторный показ онбординга после закрытия приложения")
                );
    }

    @Test
    public void mainFlowTest() throws IOException, InterruptedException {
        boolean isCloseButtonDisplayed = onboardingPage.isCloseButtonDisplayed();

        // написать метод для пропуска онбординга
        onboardingPage
                .clickAcceptButton()
                .clickContinueButton()
                .clickContinueButton()
                .clickContinueButton();

        paywallPage.clickButtonClose();
        boolean isInterOnScreen = adsPage.isInterOnScreen();

        adsPage.clickCloseButton();
        mainMenuPage.skipTutorial();
        boolean isNativeAdOnScreen = adsPage.isNativeAdOnScreen();

        Console.closeApp(app);
        Console.runApp(app);
        boolean isInterAfterSplashOnScreen = adsPage.isInterAfterSplashOnScreen();

        assertAll(
                () -> assertFalse(isCloseButtonDisplayed, "Крестик 'закрыть' не отображается на первом экране"),
                () -> assertTrue(isInterOnScreen, "После пэйвола отображается интер"),
                () -> assertTrue(isNativeAdOnScreen, "Внутри есть нативный баннер"),
                () -> assertTrue(isInterAfterSplashOnScreen, "Отображается интер после сплеша при повторном запуске")
        );
    }

    @Test
    public void secondTimeShowOnboard() {
        onboardingPage
                .clickAcceptButton()
                .clickContinueButton()
                .clickButtonClose();


    }
}


