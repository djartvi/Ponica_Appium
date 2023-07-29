package pom;

import apprunner.App;
import org.openqa.selenium.By;

public class MainMenu extends Actions {

    private final By crownButton;

    public MainMenu(App app) {
        crownButton = By.id(app.crownButton);
    }

    public void clickCrownButton() {
        clickButton(crownButton);
    }

}
