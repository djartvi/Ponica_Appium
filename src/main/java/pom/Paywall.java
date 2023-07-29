package pom;

import apprunner.App;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

public class Paywall extends Actions {

    private final PointOption verticalPaywallGooglePlayLink;

    public Paywall(App app) {
        this.verticalPaywallGooglePlayLink = app.verticalPaywallGooglePlayLink;
    }

    public void clickGooglePlay() {
        clickPoint(verticalPaywallGooglePlayLink);
    }
}
