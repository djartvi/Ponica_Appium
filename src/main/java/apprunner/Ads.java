package apprunner;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class Ads {

    private final String packageName;
    private final App app;

    private By
            interLocator,
            nativeAdLocator,
            openAdLocator, closeAdLocator,
            interAfterSplashLocator,
            closeAdAfterSplashLocator;

    public Ads(App app) {
        this.packageName = app.getPackageName();
        this.app = app;
        selectAdsProvider(System.getProperty("adsProvider"));
    }

    void selectAdsProvider(String provider) {
        switch(provider) {
            case "CAS":
                interLocator = buildLocator("cas_native_media_content");
                openAdLocator = buildLocator("cas_native_cta");
                closeAdLocator = buildLocator("cas_native_cancel");
                interAfterSplashLocator = By.id("app_open");
                closeAdAfterSplashLocator = By.id("close-button");
                break;
            case "AdMob":
                interLocator = By.xpath("//*[@text='Тестовое объявление']");
                openAdLocator = interLocator;
                closeAdLocator = app.getCloseButtonLocator();
                break;
            default:
                System.err.println("Поставщика рекламы нет в списке: " + provider);
        }

        nativeAdLocator = buildLocator("nativeAdView");
    }

    private By buildLocator(String locator) {
        return By.id(packageName + ":id/" + locator);
    }


}
