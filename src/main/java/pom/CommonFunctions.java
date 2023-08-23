package pom;

import apprunner.App;
import apprunner.Console;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
public class CommonFunctions {

    App app;
    AndroidDriver driver;
    int rightX;
    int downY;
    int leftX;
    int upY;
    TouchAction touchAction;

    public CommonFunctions(App app, AndroidDriver driver) {
        this.app = app;
        this.driver = driver;
        int windowWidth = driver.manage().window().getSize().getWidth();
        int windowHeight = driver.manage().window().getSize().getHeight();

        // Определить начальные и конечные координаты для свайпа (относительно экрана)
        rightX = (int) (windowWidth * 0.7);
        downY = (int) (windowHeight * 0.6);
        leftX = (int) (windowWidth * 0.2);
        upY = (int) (windowHeight * 0.2);

        touchAction = new TouchAction(driver);
    }

    void clickButton(By by) {
        driver.findElement(by).click();
    }

    public void navigateBack() {
        driver.navigate().back();
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

    void slowSwipeUp() {
        swipe(rightX, downY, rightX, upY, 1000);
    }

    void slowSwipeDown() {
        swipe(rightX, upY, rightX, downY, 1000);
    }

    void fastSwipeUp() {
        swipe(rightX, downY, rightX, upY, 200);
    }

    void fastSwipeDown() {
        swipe(rightX, upY, rightX, downY, 200);
    }

    boolean isElementContainsText(By by, String text) {
        WebElement parentElement = driver.findElement(by);
        String extractText = parentElement.getText();
        System.err.println(extractText);

        if (extractText.isBlank()) {
            By childElement = By.xpath("//*[contains(@text, '" + text + "')]");
            System.out.println("текст в дочернем");
            return parentElement.findElements(childElement).size() > 0;
        } else {
            String[] splitted = text.split(" ");
            boolean check = true;
            for (String s : splitted) {
                if (!extractText.contains(s)) {
                    check = false;
                    System.out.println("не прошли проверку");
                    break;
                }
            }
            return check;
        }

    }

    public boolean isAppOnScreen(App app) throws IOException, InterruptedException {
        Process process = Console.getCurrentActivity();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            process.waitFor();

            return reader.lines()
                    .anyMatch(line -> line.contains(app.getPackageName()));
        }
    }

}
