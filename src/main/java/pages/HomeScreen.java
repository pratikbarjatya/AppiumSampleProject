package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import utilities.AbstractScreen;

import java.util.List;

public class HomeScreen extends AbstractScreen {

    @AndroidFindBy(accessibility = "App")
    private WebElement appMenuItem;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(android.widget.ListView).scrollable(true)")
    private List<WebElement> scrollContainer;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(com.ridlr:id/lyt_drawer_item).index(8)")
    private WebElement feedback;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
        loadPage();
    }

    public AppMenuScreen getAppMenuPage() {
        // driver.findElement(By.name("App")).click();
        appMenuItem.click();
        return new AppMenuScreen(driver);
    }

    public void getFocus(String text) {
        // driver.findElement(By.name("App")).click();
        scrollContainer.size();
        driver.scrollTo(text);

//        driver.elementsByAndroidUIAutomator('new UiSelector().childSelector(new UiSelector().clickable(true)).clickable(true)')
//        UiScrollable settingsList = new UiScrollable(new UiSelector().scrollable(true));
//        UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()),"Bluetooth", true);

//        UiObject btSwitch = btItem.getChild(new UiSelector().className(android.widget.Switch.class.getName()));
//        btSwitch.click();
    }
}