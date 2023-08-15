package apprunner.apps;

import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;

import static io.appium.java_client.touch.offset.PointOption.point;
@Getter
public class App {

    String packageName;
    String buttonAcceptLocator;
    String buttonContinueLocator;
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
                this.selectXlsxReader();
                break;
            case "PDF":
                this.selectPdfReader();
                break;
            case "OldVoiceChanger":
                new OldVoiceChanger().selectOldVoiceChanger();
                break;
            case "NewVoiceChanger":
                new NewVoiceChanger().selectNewVoiceChanger();
                break;
            default:
                throw new IllegalStateException("Приложения нет в списке: " + System.getProperty("appName"));
        }
    }

    public void selectXlsxReader() {
        packageName = "xlsx.editor.xls.excel.viewer.office";
        buttonAcceptLocator = "//*[@text='ПРИНЯТЬ']";
        buttonContinueLocator = "//*[@text='ПРОДОЛЖИТЬ']";
        buttonCloseLocator = packageName + ":id/buttonClose";
    }

    public void selectPdfReader() {
        packageName = "xlsx.editor.xls.excel.viewer.office";
        buttonAcceptLocator = "//*[@text='ПРИНЯТЬ']";
        buttonContinueLocator = "//*[@text='ПРОДОЛЖИТЬ']";
        buttonCloseLocator = packageName + ":id/buttonClose";
    }

}
