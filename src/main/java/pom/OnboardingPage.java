package pom;

import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class OnboardingPage extends CommonFunctions {

    private final By acceptButton;
    private final By buttonClose;
    private final By continueButton;
    private final By privacyPolicy;

    public OnboardingPage(App app, AndroidDriver driver) {
        super(app, driver);

        acceptButton = By.id(app.getButtonAcceptLocator());
        buttonClose = By.id(app.getButtonCloseLocator());
        continueButton = By.id(app.getButtonContinueLocator());
        privacyPolicy = By.id(app.getPrivacyPolicyLocator());
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
        System.out.println(app.getButtonAcceptText());
        System.out.println(driver.findElement(By.id(app.getButtonAcceptLocator())).getText());
        return isElementContainsText(acceptButton, app.getButtonAcceptText());
    }

    public boolean isValidPrivacyText() {
        return isElementContainsText(privacyPolicy, app.getPrivacyPolicyText()) ;

    }

}
