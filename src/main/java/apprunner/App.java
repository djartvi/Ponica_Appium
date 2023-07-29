package apprunner;

import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.offset.PointOption.point;

public class App {

    public String packageName;
    public String buttonAccept;
    public String buttonContinue;
    public String buttonClose;
    public String privacyPolicy;
    public String progressBar1;
    public String progressBar2;
    public String progressBar3;
    public String progressBar4;
    public String footerPaywallText;
    public String crownButton;
    public PointOption verticalPaywallGooglePlayLink;

    public App() {
        selectApp(System.getProperty("appName"));
    }

    public App(String anotherApp) {
        selectApp(anotherApp);
    }

    public void selectApp(String appName) {
        switch(appName) {
            case "xlsx":
                this.selectXlsxReader();
                break;
            case "pdf":
                this.selectPdfReader();
                break;
            case "oldVoiceChanger":
                this.selectOldVoiceChanger();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + System.getProperty("appName"));
        }
    }

    public void selectXlsxReader() {
        packageName = "xlsx.editor.xls.excel.viewer.office";
        buttonAccept = "//*[@text='ПРИНЯТЬ']";
        buttonContinue = "//*[@text='ПРОДОЛЖИТЬ']";
        buttonClose = packageName + ":id/buttonClose";
    }

    public void selectPdfReader() {
        packageName = "xlsx.editor.xls.excel.viewer.office";
        buttonAccept = "//*[@text='ПРИНЯТЬ']";
        buttonContinue = "//*[@text='ПРОДОЛЖИТЬ']";
        buttonClose = packageName + ":id/buttonClose";
    }

    public void selectOldVoiceChanger() {
        packageName = "com.ponicamedia.voicechanger";
        buttonAccept = "//*[@text='Согласен']";
        buttonContinue = "//*[@text='продолжить']";
        buttonClose = packageName + ":id/buttonClose";
        progressBar1 = packageName + ":id/page2Indicator1";
        progressBar2 = packageName + ":id/page2Indicator2";
        progressBar3 = packageName + ":id/page2Indicator3";
        progressBar4 = packageName + ":id/page2Indicator4";
        privacyPolicy = "//*[@text='Политика конфиденциальности']";
        footerPaywallText = "//*[@text='Подписка будет продлеваться автоматически по той же цене и на тот же период. Отменить подписку вы можете в любой момент в Google Play']";
        crownButton = packageName + ":id/btn_purchase_premium";
        verticalPaywallGooglePlayLink = point(919,2210);
    }

}
