package pages;

import io.appium.java_client.android.AndroidDriver;
import utilities.AbstractScreen;

/**
 * Created by PratikB on 23-07-2015.
 */
public class JoinNow extends AbstractScreen {
    public JoinNow(AndroidDriver driver) {
        super(driver);
        loadPage();
    }
}
