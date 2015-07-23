package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.WebElement;

import utilities.AbstractScreen;

public class HomeScreen extends AbstractScreen {

    @AndroidFindBy(accessibility = "App")
    private WebElement appMenuItem;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
        loadPage();
    }

    public AppMenuScreen getAppMenuPage() {
        // driver.findElement(By.name("App")).click();
        appMenuItem.click();
        return new AppMenuScreen(driver);
    }
}