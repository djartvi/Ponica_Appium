package pom;

import apprunner.Ads;
import apprunner.App;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class AdsPage extends CommonFunctions {

    private final By interLocator, interAfterSplashLocator, nativeAdLocator, openAdLocator, closeAdLocator;
    private final int WAIT_DURATION = 6000;

    public AdsPage(App app, Ads ads, AndroidDriver driver) {
        super(app, driver);

        interLocator = ads.getInterLocator();
        interAfterSplashLocator = ads.getInterAfterSplashLocator();
        nativeAdLocator = ads.getNativeAdLocator();
        openAdLocator = ads.getOpenAdLocator();
        closeAdLocator = ads.getCloseAdLocator();
    }

    public boolean isInterOnScreen() {
        return isElementOnScreen(interLocator);
    }

    public boolean isInterAfterSplashOnScreen() {
        return isElementOnScreen(interAfterSplashLocator);
    }

    public boolean isNativeAdOnScreen() {
        return isElementOnScreen(nativeAdLocator);
    }

    public void clickCloseButton() throws InterruptedException {
        Thread.sleep(WAIT_DURATION);
        clickButton(closeAdLocator);
    }

    public void openAdd() {
        clickButton(openAdLocator);
    }
}
