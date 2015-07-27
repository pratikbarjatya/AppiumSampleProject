package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractScreen {

    @AndroidFindBy(className = "android.webkit.WebView")
    private WebElement androidWebView;

    public AndroidDriver driver;

    public AbstractScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public void loadPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void rotateScreen() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void switchToWebView() {
        driver.manage()
                .timeouts()
                .implicitlyWait(TestSetup.DEFAULT_WAIT_TIME,
                        TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,
                TestSetup.EXPLICIT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(androidWebView));
        driver.manage()
                .timeouts()
                .implicitlyWait(TestSetup.EXPLICIT_WAIT_TIME,
                        TimeUnit.SECONDS);

        Set<String> contextSet = driver.getContextHandles();
        for (String contextName : contextSet) {
            System.out.println(contextName);
            if (!contextName.contains("NATIVE_APP")) {
                driver.context(contextName);
                break;
            }
        }
    }

    public void takeScreenShot(String fileName) {
        String path = "./screenshots/" + fileName + ".jpeg";
        File tmpFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tmpFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}