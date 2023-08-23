package apprunner;

import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;

import static io.appium.java_client.touch.offset.PointOption.point;

@Getter
public class App {

    String packageName;
    String buttonAcceptLocator;
    String buttonAcceptText;
    String buttonContinueLocator;
    String buttonContinueText;
    String buttonCloseLocator;
    String privacyPolicyLocator;
    String progressBar1Locator;
    String progressBar2Locator;
    String progressBar3Locator;
    String progressBar4Locator;
    String footerPaywallTextLocator;
    String crownButtonLocator;
    PointOption verticalPaywallGooglePlayLinkLocator;

    public App() {
        selectApp(System.getProperty("appName"));
    }

    public App(String anotherApp) {
        selectApp(anotherApp);
    }

    private void selectApp(String appName) {
        switch(appName) {
            case "XLSX":
                selectXlsxReader();
                break;
            case "PDF":
                selectPdfReader();
                break;
            case "OldVoiceChanger":
                selectOldVoiceChanger();
                break;
            case "NewVoiceChanger":
                selectNewVoiceChanger();
                break;
            default:
                throw new IllegalStateException("Приложения нет в списке: " + System.getProperty("appName"));
        }
    }

    public void setDefaultLocators() {
        crownButtonLocator = packageName + ":id/btn_purchase_premium";
        buttonAcceptLocator = packageName + ":id/buttonContinue";
        buttonContinueLocator = packageName + ":id/buttonContinue";
        buttonCloseLocator = packageName + ":id/buttonClose";
        progressBar1Locator = packageName + ":id/page2Indicator1";
        progressBar2Locator = packageName + ":id/page2Indicator2";
        progressBar3Locator = packageName + ":id/page2Indicator3";
        progressBar4Locator = packageName + ":id/page2Indicator4";
    }

    public void selectOldVoiceChanger() {
        setDefaultLocators();
        packageName = Packages.VOICE_CHANGER_OLD;
        privacyPolicyLocator = "//*[@text='Политика конфиденциальности']";
        footerPaywallTextLocator = "//*[@text='Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    public void selectNewVoiceChanger() {
        setDefaultLocators();
        packageName = Packages.VOICE_CHANGER_NEW;
        buttonAcceptText = "//*[@text='Согласен']";
        buttonContinueLocator = "//*[@text='продолжить']";
        privacyPolicyLocator = "//*[@text='Политика конфиденциальности']";
        footerPaywallTextLocator = "//*[@text='Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    public void selectXlsxReader() {
        packageName = Packages.XLSX_READER;
        buttonAcceptLocator = "//*[@text='ПРИНЯТЬ']";
        buttonContinueLocator = "//*[@text='ПРОДОЛЖИТЬ']";
    }

    public void selectPdfReader() {
        packageName = Packages.PDF_READER;
        buttonAcceptLocator = "//*[@text='ПРИНЯТЬ']";
        buttonContinueLocator = "//*[@text='ПРОДОЛЖИТЬ']";
    }

}
