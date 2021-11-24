package lesson_14;

import caps.Ex;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Server_Test {
    public static void main(String[] args) {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();

        AppiumDriverLocalService appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(Ex.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(Ex.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(Ex.NEW_COMMAND_TIMEOUT, 120);

        // Connect to Appium Server
//        URL appiumServer = new URL("http://localhost:4723/wd/hub");
//        appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
        AppiumDriver<MobileElement> appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
        loginLabel.click();
        // appiumServer.stop();
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
}
