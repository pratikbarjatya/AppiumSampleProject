import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MealsListingScreen;
import utilities.*;

import java.net.MalformedURLException;

/**
 * Created by PratikB on 13-04-2015.
 */

public class Test1 extends TestSetup {
    private AndroidDriver driver;
    private String sTestCaseName;
    MealsListingScreen mealsListingScreen;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DOMConfigurator.configure("log4j.xml");
        driver = getDriver();
        Log.info("Appium driver instantiated");
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        sTestCaseName = this.toString();
        sTestCaseName = Utils.getTestCaseName(this.toString());
        Log.startTestCase(sTestCaseName);
    }

    @AfterMethod
    public void afterMethod() {

        driver.resetApp();
        Log.endTestCase(sTestCaseName);
    }

    @Test(priority = 1)
    public void VerifyScreen() throws Exception {

        Thread.sleep(3000);
        mealsListingScreen = new MealsListingScreen(driver);
        Assert.assertTrue(mealsListingScreen.isMealsListingScreenDisplayed());

    }
}


