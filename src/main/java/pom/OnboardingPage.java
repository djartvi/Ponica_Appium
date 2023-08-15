package pom;

import apprunner.App;
import org.openqa.selenium.By;

public class OnboardingPage extends Action {

    private final By acceptButton;
    private final By buttonClose;
    private final By continueButton;
    private final By privacyPolicy;

    public OnboardingPage(App app) {
        acceptButton = By.id(app.getButtonAcceptLocator());
        buttonClose = By.id(app.getButtonCloseLocator());
        continueButton = By.id(app.getButtonContinueLocator());
        privacyPolicy = By.xpath(app.getPrivacyPolicyLocator());
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
        clickButton(buttonClose);
        return this;
    }

    public OnboardingPage clickContinueButton() {
        clickButton(continueButton);
        return this;
    }

    public boolean isValidAcceptButtonText() {
        return isElementContainsText(acceptButton, "Согласен");
    }
}
