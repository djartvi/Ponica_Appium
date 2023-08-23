package pom;

import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

public class PaywallPage extends CommonFunctions {

    private final By buttonClose;
    private final PointOption verticalPaywallGooglePlayLink;

    public PaywallPage(App app, AndroidDriver driver) {
        super(app, driver);
        buttonClose = By.id(app.getButtonCloseLocator());
        verticalPaywallGooglePlayLink = app.getVerticalPaywallGooglePlayLinkLocator();
    }

    public void clickGooglePlay() {
        clickPoint(verticalPaywallGooglePlayLink);
    }

    public PaywallPage clickButtonClose() {
        clickButton(buttonClose);
        return this;
    }

}
