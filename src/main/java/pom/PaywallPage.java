package pom;

import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

public class PaywallPage extends CommonFunctions {

    private final By buttonClose;
    private final PointOption verticalPaywallGooglePlayLink;
    private final String paywallFooterText;

    public PaywallPage(App app, AndroidDriver driver) {
        super(app, driver);
        buttonClose = app.getCloseButtonLocator();
        verticalPaywallGooglePlayLink = app.getVerticalPaywallGooglePlayLinkLocator();
        paywallFooterText = app.getPaywallFooterText();
    }

    public void clickGooglePlay() {
        clickPoint(verticalPaywallGooglePlayLink);
    }

    public void clickButtonClose() {
        clickButton(buttonClose);
    }

//    public boolean isPaywallDisplayed() {
//        isElementContainsText(paywallFooterText);
//    }

}
