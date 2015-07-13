package utilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestSetup {

	private static AndroidDriver driver;
    private Reporting Reporter;
    private HashMap <String, String> Environment;
    private HashMapNew Dictionary;

	public static AndroidDriver getDriver() {
		return driver;
	}

	private void setDriver() throws MalformedURLException {

		System.out.println("Launching Android Driver ..");
		driver = initAppiumDriver();

        java.util.Date today = new java.util.Date();
        Timestamp now = new java.sql.Timestamp(today.getTime());
        String tempNow[] = now.toString().split("\\.");
        String timeStamp = tempNow[0].replaceAll(":", ".").replaceAll(" ", "T");

        String HTMPReports = "./HTML_REP_/" + timeStamp;
        String SnapshotsFolder = HTMPReports + "\\Snapshots";
        Environment.put("HTMLREPORTSPATH", HTMPReports);
        Environment.put("SNAPSHOTSFOLDER", SnapshotsFolder);

        Reporter = new Reporting(driver, Dictionary, Environment);
	}
	private AndroidDriver initAppiumDriver() throws MalformedURLException {

        File file = new File("./envConfig.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();

        // load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Setting the desired capabilities
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("deviceName", prop.getProperty("deviceName"));
            capabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));
            capabilities.setCapability("platformName", prop.getProperty("platformName"));

            capabilities.setCapability("appPackage", prop.getProperty("appPackage"));
            capabilities.setCapability("appActivity", prop.getProperty("appActivity"));
            // Launching the App
            driver = new AndroidDriver(new URL(prop.getProperty("serverURL")), capabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver;
        }catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
	}

	@BeforeClass
	public void initializeTestSetup() {
		try {
			setDriver();
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
        Reporter.fnCloseHtmlReport();
	}
}