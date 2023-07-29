package pom;

import apprunner.Setup;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

public class Actions {

    void clickButton(By by) {
        Setup.getDriver().findElement(by).click();
    }

    void clickPoint(PointOption point) {
        new TouchAction(Setup.getDriver())
                .press(point)
                .waitAction()
                .release()
                .perform();
    }

}
