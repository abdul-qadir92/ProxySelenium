import com.browserstack.local.Local;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.ios;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertEquals;

public class iOSOTP {
    IOSDriver driver;

    @Test
    public void iOS() throws Exception {

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        final String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        bstackOptions.put("userName", username);
        bstackOptions.put("accessKey", accessKey);
        bstackOptions.put("buildName", "OTP Checks");
        bstackOptions.put("sessionName", "iOS");
        bstackOptions.put("enableSim", "true");
        bstackOptions.put("idleTimeout", 120);
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("appium:platformVersion", "16");
        capabilities.setCapability("appium:deviceName", "iPhone 14");
        capabilities.setCapability("appium:app", "bs://ad5666c27c264710e95da9cd2f831a0840660487");
        capabilities.setCapability("bstack:options", bstackOptions);

        driver = new IOSDriver(new URL(URL), capabilities);
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        org.openqa.selenium.WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
        textButton.click();

        Map<String, Object> simDetails = (Map<String, Object>) js.executeScript("browserstack_executor:{\"action\": \"deviceInfo\", \"arguments\" : {\"deviceProperties\" : [\"simOptions\"]}}");
        System.out.println(simDetails.get("Phone Number"));
        driver.activateApp("com.apple.MobileSMS");
        org.openqa.selenium.WebElement msg = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//XCUIElementTypeCell[contains(@name,'')])[2]")));

        String otp = msg.getAttribute("name");
        driver.terminateApp("com.apple.MobileSMS");
        driver.activateApp("com.browserstack.Sample-iOS");
        org.openqa.selenium.WebElement textInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
        int index = otp.indexOf("OTP:")+5;
        textInput.click();
        textInput.sendKeys(otp.substring(index,index+5)+ "\n");
        org.openqa.selenium.WebElement textOutput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));

        assertEquals(textOutput.getText(),otp.substring(index,index+5));

    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
    }
}
