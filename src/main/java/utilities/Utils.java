package utilities;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    /**
     * <b>Take ScreenShot</b>
     * <p>
     *     Description : To Capture Snapshot
     *     @author Pratik
     *     @since 19-JAN-2015
     * </p>
     */

    	public static void ScreenShot() {

		String path;

		try {
			WebDriver augmentedDriver = new Augmenter().augment(TestSetup.getDriver());
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			path = "./screenshots/" + GetTimeStampValue() + ".jpeg";
			FileUtils.copyFile(source, new File(path));

		} catch (Exception e) {

			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}

	/**
	 * <b> GetTimeStampValue</b>
	 * <p>
	 * Description : To Fetch Timestamp
	 * 
	 * @return systime TimeStamp value in DAY_MON_DD_MM_YY_IST_YYYY format
	 * @throws java.io.IOException
	 *             Signals that an I/O exception of some sort has occurred. This
	 *             class is the general class of exceptions produced by failed
	 *             or interrupted I/O operations.
	 * @author Pratik
	 * @since 19-JAN-2015
	 */

	public static String GetTimeStampValue() throws IOException {

		Calendar cal = Calendar.getInstance();
		Date time = cal.getTime();
		String timestamp = time.toString();
		System.out.println(timestamp);
		String systime = timestamp.replace(":", "-");
		System.out.println(systime);
		return systime;
	}

    /**
     * <b> getTestCaseName</b>
     * <p>
     * Description : To Fetch TestCase Class Name
     *
     * @return Test Class Name
     * @throws java.lang.Exception
     * @author Pratik
     * @since 19-JAN-2015
     */
	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			Log.error("Class utilities | Method getTestCaseName | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

    /**
     * <b> customScroll</b>
     * <p>
     * Description : To scroll the screen
     * @author Pratik
     * @since 19-JAN-2015
     */
    public static void customScroll(){
        AppiumDriver driver = TestSetup.getDriver();
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        driver.swipe(width / 2, height/2 - 10, width / 2, height/2 + 10, 1000);
    }
}
