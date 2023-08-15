package pom;

import apprunner.App;
import org.openqa.selenium.By;

public class MainMenuPage extends Action {

    private final By crownButton;

    public MainMenuPage(App app) {
        crownButton = By.id(app.getCrownButtonLocator());
    }

    public void clickCrownButton() {
        clickButton(crownButton);
    }

}
