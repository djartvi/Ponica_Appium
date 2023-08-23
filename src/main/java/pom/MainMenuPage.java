package pom;

import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainMenuPage extends CommonFunctions {

    private final By crownButton;

    public MainMenuPage(App app, AndroidDriver driver) {
        super(app, driver);
        crownButton = By.id(app.getCrownButtonLocator());
    }

    public void clickCrownButton() {
        clickButton(crownButton);
    }

}
