package apprunner;

import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;

import static io.appium.java_client.touch.offset.PointOption.point;

@Getter
@Setter
public class App {

    private By acceptButtonLocator,
            closeButtonLocator,
            continueButtonLocator,
            crownButtonLocator,
            paywallFeature1Locator, paywallFeature2Locator, paywallFeature3Locator, paywallProgressBarLocator,
            privacyPolicyLocator,
            product1TrialLocator, product1PriceLocator,
            progressBar1Locator, progressBar2Locator, progressBar3Locator, progressBar4Locator,
            titleLocator, subtitleLocator,
            tutorialLocator;
    private PointOption verticalPaywallGooglePlayLinkLocator;
    private String packageName, buttonAcceptText, buttonContinueText,
            page1Title, page2Title, page3Title, page4Title,
            paywallFooterText,
            privacyPolicyText;

    public App() {
        selectApp(System.getProperty("appName"));
    }

    public App(String anotherApp) {
        selectApp(anotherApp);
    }

    // выбираем приложение и задаём локаторы
    private void selectApp(String appName) {
        switch(appName) {
            case "XLSX":
                packageName = Packages.XLSX_READER;
                setDefaultLocators();
                selectXlsxReader();
                break;
            case "PDF":
                packageName = Packages.PDF_READER;
                setDefaultLocators();
                selectPdfReader();
                break;
            case "VoiceChangerOld":
                packageName = Packages.VOICE_CHANGER_OLD;
                setDefaultLocators();
                selectOldVoiceChanger();
                break;
            case "VoiceChangerNew":
                packageName = Packages.VOICE_CHANGER_NEW;
                setDefaultLocators();
                selectNewVoiceChanger();
                break;
            case "ClickerOld":
                packageName = Packages.CLICKER_OLD;
                setDefaultLocators();
                selectClickerOld();
                break;
            default:
                System.err.println("Приложения нет в списке: " + appName);
        }
    }

    private By buildLocator(String locator) {
        return By.id(packageName + ":id/" + locator);
    }

    // локаторы, которые повторяются в большинстве приложений
    private void setDefaultLocators() {
        acceptButtonLocator = buildLocator("buttonContinue");
        closeButtonLocator = buildLocator("buttonClose");
        continueButtonLocator = buildLocator("buttonContinue");
        crownButtonLocator = buildLocator("btn_purchase_premium");
        paywallFeature1Locator = buildLocator("feature1");
        paywallFeature2Locator = buildLocator("feature2");
        paywallFeature3Locator = buildLocator("feature3");
        paywallProgressBarLocator = buildLocator("progressDots");
        privacyPolicyLocator = buildLocator("privacy");
        product1PriceLocator = buildLocator("priceText");
        product1TrialLocator = buildLocator("product0Button");
        progressBar1Locator = buildLocator("page2Indicator1");
        progressBar2Locator = buildLocator("page2Indicator2");
        progressBar3Locator = buildLocator("page2Indicator3");
        progressBar4Locator = buildLocator("page2Indicator4");
        subtitleLocator = buildLocator("subtitle");
        titleLocator = buildLocator("title");
        tutorialLocator = buildLocator("tutorialText");
        tutorialLocator = buildLocator("tutorialTextContainer");
    }

    public void selectNewVoiceChanger() {
        buttonAcceptText = "ПРИНЯТЬ";
        buttonContinueText = "ПРОДОЛЖИТЬ";
        paywallFooterText = "Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play";
        page1Title = "ДОБРО ПОЖАЛОВАТЬ В ИЗМЕНИТЕЛЬ ГОЛОСА";
        page2Title = "БОЛЕЕ 20 ГОЛОСОВЫХ ЭФФЕКТОВ";
        page3Title = "ЗАПИСЬ ГОЛОСА";
        page4Title = "ОБРАБОТКА ГОЛОСА";
        privacyPolicyLocator = buildLocator("subtitle");
        privacyPolicyText = "Политикой конфиденциальности";
        tutorialLocator = buildLocator("tutorialTextContainer");
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    private void selectOldVoiceChanger() {
        buttonAcceptText = "Согласен";
        buttonContinueText = "Продолжить";
        paywallFooterText = "Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        tutorialLocator = buildLocator("tutorialTextContainer");
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    private void selectClickerOld() {
        buttonAcceptText = "ПРИНЯТЬ";
        paywallFooterText = "Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play";
        privacyPolicyText = "Политикой конфиденциальности";
        verticalPaywallGooglePlayLinkLocator = point(919,2210);
    }

    private void selectXlsxReader() {
    }

    private void selectPdfReader() {
    }

}
