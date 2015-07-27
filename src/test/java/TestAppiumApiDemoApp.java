import pages.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.TestSetup;

import java.io.IOException;

public class TestAppiumApiDemoApp {
    private AndroidDriver driver;
    private HomeScreen homeScreen;
    private AppMenuScreen appMenuScreen;
    private AppActivityScreen appActivityScreen;
    private ScreenOrientationScreen screenOrientationScreen;

    @AfterMethod
    public void afterMethod() {
    }

    @BeforeClass(alwaysRun = true)
    public void initAutomation() throws IOException {
        TestSetup.loadConfigProp("config_apidemo_test_app.properties");
        TestSetup.setCapabilities();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = TestSetup.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test(groups = { "Smoke" }, enabled = true)
    public void testAppActivity() {
        homeScreen = new HomeScreen(driver);
        appMenuScreen = homeScreen.getAppMenuPage();
        appActivityScreen = appMenuScreen.getActivityPage();
        // screenOrientationPage = appActivityPage.browseAppActivityScreen()
        // .getScreenOrientationPage();
        screenOrientationScreen = appActivityScreen.getScreenOrientationPage();
        Assert.assertEquals(
                screenOrientationScreen.isItValidScreenOrientationPage(), true);
        screenOrientationScreen.changeScreenOrientation("USER");
        Assert.assertEquals(screenOrientationScreen.checkOrientationType(),
                true);
    }
}