package pom;

import apprunner.App;
import apprunner.Setup;
import org.openqa.selenium.By;

public class MainMenu {

    App app;

    private By crownButton = By.id(app.crownButton);

    public MainMenu(App app) {
        this.app = app;
    }

    private void clickButton(By by) {
        Setup.getDriver().findElement(by).click();
    }

    public void clickCrownButton() {
        clickButton(crownButton);
    }

}
