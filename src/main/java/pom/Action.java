package pom;

import apprunner.Setup;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class Action {

    int windowWidth = Setup.getDriver().manage().window().getSize().getWidth();
    int windowHeight = Setup.getDriver().manage().window().getSize().getHeight();

    // Определить начальные и конечные координаты для свайпа (относительно экрана)
    int rightX = (int) (windowWidth * 0.7);
    int downY = (int) (windowHeight * 0.6);
    int leftX = (int) (windowWidth * 0.2);
    int upY = (int) (windowHeight * 0.2);

    TouchAction touchAction = new TouchAction(Setup.getDriver());

    void clickButton(By by) {
        Setup.getDriver().findElement(by).click();
    }

    boolean isElementContainsText(By by, String text) {
        WebElement parentElement = Setup.getDriver().findElement(by);
        By childElement = By.xpath("//*[@text='" + text + "']");

        return parentElement.findElements(childElement).size() > 0;
    }

    void clickPoint(PointOption point) {
        touchAction
                .press(point)
                .waitAction()
                .release()
                .perform();
    }

    private void swipe(int startX, int startY, int endX, int endY, int duration) {
        touchAction
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    public void slowSwipeUp() {
        swipe(rightX, downY, rightX, upY, 1000);
    }

    public void slowSwipeDown() {
        swipe(rightX, upY, rightX, downY, 1000);
    }

    public void fastSwipeUp() {
        swipe(rightX, downY, rightX, upY, 200);
    }

    public void fastSwipeDown() {
        swipe(rightX, upY, rightX, downY, 200);
    }

}
