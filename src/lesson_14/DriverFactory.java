package lesson_14;

import caps.Ex;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    // SOLID
    public static void startAppiumServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();

        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();

    }

    public static void stopAppiumServer() {
        String killNodeWindowCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";
        // Ternary operator | Toan tu ba ngoi
        String currentOS = System.getProperty("os.name").toLowerCase();
        String killNodeCmd = currentOS.startsWith("windows") ? killNodeWindowCmd : killNodeLinuxCmd;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
// Khoi tao android Driver
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(Ex.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(Ex.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(Ex.NEW_COMMAND_TIMEOUT, 120);

        // Connect to Appium Server
//        URL appiumServer = new URL("http://localhost:4723/wd/hub");
//        appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
        AndroidDriver<MobileElement> androidDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        return androidDriver;
    }


    public static void setAndroidDriver(AndroidDriver<MobileElement> androidDriver) {
        DriverFactory.androidDriver = androidDriver;
    }
}
