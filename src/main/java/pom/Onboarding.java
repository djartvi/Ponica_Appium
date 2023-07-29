package pom;

import apprunner.App;
import apprunner.Setup;
import org.openqa.selenium.By;

public class Onboarding {

    private final By acceptButton;
    private final By buttonClose;
    private final By continueButton;
//    private final By privacyPolicy;

    public Onboarding(App app) {
        acceptButton = By.xpath(app.buttonAccept);
        buttonClose = By.id(app.buttonClose);
        continueButton = By.xpath(app.buttonContinue);
//        privacyPolicy = By.xpath(app.privacyPolicy);
    }

    public void clickButton(By by) {
        Setup.getDriver().findElement(by).click();
    }

    public Onboarding clickAcceptButton() {
        clickButton(acceptButton);
        return this;
    }

//    public Onboarding clickPrivacyPolicy() {
//        clickButton(privacyPolicy);
//        return this;
//    }

    public Onboarding clickButtonClose() {
        clickButton(buttonClose);
        return this;
    }

    public Onboarding clickContinueButton() {
        clickButton(continueButton);
        return this;
    }
}
