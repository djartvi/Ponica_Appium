package pom;

import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class OnboardingPage extends CommonFunctions {

    private final By acceptButton, closeButton, continueButton, privacyPolicy;

    public OnboardingPage(App app, AndroidDriver driver) {
        super(app, driver);

        acceptButton = app.getAcceptButtonLocator();
        closeButton = app.getCloseButtonLocator();
        continueButton = app.getContinueButtonLocator();
        privacyPolicy = app.getPrivacyPolicyLocator();
    }

    public OnboardingPage clickAcceptButton() {
        clickButton(acceptButton);
        return this;
    }

    public OnboardingPage clickPrivacyPolicy() {
        clickButton(privacyPolicy);
        return this;
    }

    public OnboardingPage clickButtonClose() {
        clickButton(closeButton);
        return this;
    }

    public OnboardingPage clickContinueButton() {
        clickButton(continueButton);
        return this;
    }

    public boolean isValidAcceptButtonText() {
        return isElementContainsText(acceptButton, app.getButtonAcceptText());
    }

    public boolean isCloseButtonDisplayed() {
        return isElementOnScreen(closeButton);
    }

    public boolean isValidPrivacyText() {
        return isElementContainsText(privacyPolicy, app.getPrivacyPolicyText()) ;
    }

}
