package lesson_14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public class DriverFactoryTest {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            //talking about relative Xpath
            List<MobileElement> credsFormElems = androidDriver.findElementsByXPath("//android.widget.EditText");
            final int EMAIL_INPUT_INDEX = 0;
            final int PASSWORD_INPUT_INDEX = 1;
            credsFormElems.get(EMAIL_INPUT_INDEX).sendKeys("Teo@sth.com");
            credsFormElems.get(PASSWORD_INPUT_INDEX).sendKeys("12345678");

//            MobileElement emailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
//            MobileElement passwordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");

            // Input username and password
//            emailInputElem.sendKeys("Teo@sth.com");
//            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            MobileElement loginFeatureDescElem = androidDriver.findElementByXPath("//*[contains(@text, 'When the device')]");
            MobileElement loginFeatureDescElemUiSel = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");

            System.out.println(loginFeatureDescElem.getText());
            System.out.println(loginFeatureDescElem.getText());


            WebDriverWait wait = new WebDriverWait(androidDriver, 45);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));

            MobileElement loginResultDialogElem = androidDriver.findElementById("android:id/alertTitle");
            System.out.println(loginFeatureDescElem.getText());
        } catch (Exception ignored) {
        } finally {
            DriverFactory.stopAppiumServer();
        }

    }
}
