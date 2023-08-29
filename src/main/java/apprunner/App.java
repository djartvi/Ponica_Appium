package apprunner;

import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;

import static io.appium.java_client.touch.offset.PointOption.point;

@Getter
public class App {

    private String packageName;
    private String buttonAcceptLocator, buttonAcceptText;
    private String buttonContinueLocator, buttonContinueText;
    private String buttonCloseLocator;
    private String privacyPolicyLocator, privacyPolicyText;
    private String progressBar1Locator, progressBar2Locator, progressBar3Locator, progressBar4Locator;
    private String product1TrialLocator, product1PriceLocator;
    private String paywallFeature1Locator, paywallFeature2Locator, paywallFeature3Locator;
    private String paywallProgressBarLocator, footerPaywallTextLocator;
    private String crownButtonLocator;
    PointOption verticalPaywallGooglePlayLinkLocator;

    public App() {
        setLocators(System.getProperty("appName"));
    }

    public App(String anotherApp) {
        setLocators(anotherApp);
    }

    // выбираем приложение и задаём локаторы
    private void selectApp(String appName) {
        switch(appName) {
            case "XLSX":
                selectXlsxReader();
                break;
            case "PDF":
                selectPdfReader();
                break;
            case "VoiceChangerOld":
                selectOldVoiceChanger();
                break;
            case "VoiceChangerNew":
                selectNewVoiceChanger();
                break;
            case "ClickerOld":
                selectClickerOld();
                break;
            default:
                System.out.println("000");
//                throw new IllegalStateException("Приложения нет в списке: " + System.getProperty("appName"));
        }
    }

    private void setLocators(String appName) {
        // первый раз вызываем, чтобы определить packageName
        selectApp(appName);
        setDefaultLocators();
        // ещё раз вызываем, чтобы переопределить некоторые локаторы
        selectApp(appName);
    }

    // локаторы, которые повторяются в большинстве приложений
    public void setDefaultLocators() {
        crownButtonLocator = packageName + ":id/btn_purchase_premium";
        buttonAcceptLocator = packageName + ":id/buttonContinue";
        privacyPolicyLocator = packageName + ":id/privacy";
        buttonContinueLocator = packageName + ":id/buttonContinue";
        buttonCloseLocator = packageName + ":id/buttonClose";
        progressBar1Locator = packageName + ":id/page2Indicator1";
        progressBar2Locator = packageName + ":id/page2Indicator2";
        progressBar3Locator = packageName + ":id/page2Indicator3";
        progressBar4Locator = packageName + ":id/page2Indicator4";
        paywallProgressBarLocator = packageName + ":id/progressDots";
        product1TrialLocator = packageName + ":id/product0Button";
        product1PriceLocator = packageName + ":id/priceText";
        paywallFeature1Locator = packageName + ":id/feature1";
        paywallFeature2Locator = packageName + ":id/feature2";
        paywallFeature3Locator = packageName + ":id/feature3";
    }

    public void selectOldVoiceChanger() {
        packageName = Packages.VOICE_CHANGER_OLD;
        buttonAcceptText = "Согласен";
        buttonContinueText = "Продолжить";
        footerPaywallTextLocator = "//*[@text='Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    public void selectClickerOld() {
        packageName = Packages.CLICKER_OLD;
        buttonAcceptText = "ПРИНЯТЬ";
        privacyPolicyText = "Политикой конфиденциальности";
        footerPaywallTextLocator = "//*[@text='Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    public void selectNewVoiceChanger() {
        packageName = Packages.VOICE_CHANGER_NEW;
        privacyPolicyLocator = packageName + ":id/subtitle";
        buttonAcceptText = "ПРИНЯТЬ";
        privacyPolicyText = "Политикой конфиденциальности";
        buttonContinueText = "Продолжить";
        footerPaywallTextLocator = "//*[@text='Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    public void selectXlsxReader() {
        packageName = Packages.XLSX_READER;
    }

    public void selectPdfReader() {
        packageName = Packages.PDF_READER;
    }

}
