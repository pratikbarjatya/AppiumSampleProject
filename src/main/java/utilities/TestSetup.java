package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestSetup {

    private static Properties prop = new Properties();
    public static int EXPLICIT_WAIT_TIME;
    public static int IMPLICIT_WAIT_TIME;
    public static int DEFAULT_WAIT_TIME;
    public static String APPLICATION_NAME;
    public static String BASE_PKG;
    public static String APP_ACTIVITY;
    public static String APP_PASSWORD;
    private static String APPIUM_PORT;
    public static String AUTOMATION_INSTRUMENTATION;
    public static String BROWSER_NAME;
    public static String PLATFORM_NAME;
    public static String NEW_COMMAND_TIMEOUT;
    public static String PLATFORM_VERSION;
    public static String DEVICE_READY_TIMEOUT;
    public static String DEVICE_NAME;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static URL serverUrl;
    private static AndroidDriver driver;

    public static void loadConfigProp(String propertyFileName)
            throws IOException {
        prop.load(ClassLoader.getSystemResource(propertyFileName).openStream());

        EXPLICIT_WAIT_TIME = Integer
                .parseInt(prop.getProperty("explicit.wait"));
        IMPLICIT_WAIT_TIME = Integer
                .parseInt(prop.getProperty("implicit.wait"));
        DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
        APPLICATION_NAME = prop.getProperty("application.path");
        BASE_PKG = prop.getProperty("base.pkg");
        APP_ACTIVITY = prop.getProperty("application.activity");
        APP_PASSWORD = prop.getProperty("application.password");
        APPIUM_PORT = prop.getProperty("appium.server.port");
        AUTOMATION_INSTRUMENTATION=prop.getProperty("automation.instumentation");
        DEVICE_NAME=prop.getProperty("device.name");
        BROWSER_NAME=prop.getProperty("browser.name");
        PLATFORM_NAME=prop.getProperty("platform.name");
        PLATFORM_VERSION=prop.getProperty("platform.version");
        NEW_COMMAND_TIMEOUT=prop.getProperty("new.command.timeout");
        DEVICE_READY_TIMEOUT=prop.getProperty("device.ready.timeout");
    }

    public static void setCapabilities() {
        capabilities.setCapability(CapabilityType.BROWSER_NAME,
                TestSetup.BROWSER_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                TestSetup.PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
                TestSetup.PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                TestSetup.DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                TestSetup.AUTOMATION_INSTRUMENTATION);
//        capabilities.setCapability(MobileCapabilityType.APP, new File(
//                ClassLoader.getSystemResource(TestSetup.APPLICATION_NAME)
//                        .getFile()).getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.APP,TestSetup.APPLICATION_NAME);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
                TestSetup.NEW_COMMAND_TIMEOUT);
        capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT,
                TestSetup.DEVICE_READY_TIMEOUT);
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
                TestSetup.APP_ACTIVITY);
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
                TestSetup.BASE_PKG);
    }

    public static AndroidDriver getDriver() throws MalformedURLException {
        serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
        driver = new AndroidDriver(serverUrl, capabilities);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;
    }

//	private void setDriver() throws MalformedURLException {
//
//		System.out.println("Launching Android Driver ..");
//		driver = initAppiumDriver();
//	}
//	private AndroidDriver initAppiumDriver() throws MalformedURLException {
//
//            // Launching the App
//
//            serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
//            driver = new AndroidDriver(serverUrl, capabilities);
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//            return driver;
//	}
//
//	@BeforeClass
//	public void initializeTestSetup() {
//		try {
//			setDriver();
//		} catch (Exception e) {
//			System.out.println("Error....." + e.getStackTrace());
//		}
//	}

//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}
}