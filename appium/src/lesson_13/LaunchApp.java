package lesson_13;

import caps.Ex;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchApp {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = null;

        // Setup DesiredCapabilities;
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            desiredCapabilities.setCapability(Ex.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(Ex.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(Ex.NEW_COMMAND_TIMEOUT, 120);

            // Connect to Appium Server
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
        } catch (Exception ignored) {

        }
        //Interact
        if(appiumDriver != null){
            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

            System.out.println("Success");

            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

    }else {
            System.out.println("Error");
        }
}
}
