package pom;

import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainMenuPage extends CommonFunctions {

    private final By crownButtonLocator;
    private final By tutorialLocator;

    public MainMenuPage(App app, AndroidDriver driver) {
        super(app, driver);
        crownButtonLocator = app.getCrownButtonLocator();
        tutorialLocator = app.getTutorialLocator();
    }

    public void clickCrownButton() {
        clickButton(crownButtonLocator);
    }

    public void skipTutorial() {
        while (isElementOnScreen(tutorialLocator)) {
            clickButton(tutorialLocator);
        }
    }
}
