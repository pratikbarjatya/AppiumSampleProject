package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.AbstractScreen;
import utilities.Log;

import java.util.List;

/**
 * Created by PratikB on 17-07-2015.
 */
public class SplashScreen extends AbstractScreen {

    public SplashScreen(AndroidDriver driver) {
        super(driver);
        loadPage();
    }

    @AndroidFindBy(className = "android.widget.TextView")
    private List<WebElement> textViews;

    public void verifySplashScreenUi () throws InterruptedException {

        Assert.assertTrue(textViews.get(0).getText().contains("Easiest way to order food") && textViews.get(1).getText().contains("New User?") && textViews.get(2).getText().contains("Join Now") && textViews.get(3).getText().contains("Already have an account?") && textViews.get(4).getText().contains("Sign In"));
        Assert.assertTrue(textViews.size() == 5);
        Log.info("index 0 : " + textViews.get(0).getText() + "\n" + "index 1 :" + textViews.get(1).getText() + "\n" + "index 2 :" + textViews.get(2).getText() + "\n" + "index 3 :" +textViews.get(3).getText() + "\n" + "index 4 :" + textViews.get(4).getText() + "\n" + "TextView Size : " + textViews.size());
    }

    public SignIn clickingOnSignIn(){
        Log.info("clicking on Sign In link");
        textViews.get(4).click();
        return new SignIn(driver);
    }

    public JoinNow clickingOnJoinNow() throws InterruptedException{
        Log.info("clicking on Join Now link");
        textViews.get(2).click();
        return new JoinNow(driver);
    }

}

