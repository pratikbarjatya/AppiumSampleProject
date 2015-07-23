package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.WebElement;

import utilities.AbstractScreen;

public class AppMenuScreen extends AbstractScreen {

    @AndroidFindBy(accessibility = "Activity")
    private WebElement appActivity;

    public AppMenuScreen(AndroidDriver driver) {
        super(driver);
        loadPage();
    }

    public AppActivityScreen getActivityPage() {
        appActivity.click();
        return new AppActivityScreen(driver);
    }

}