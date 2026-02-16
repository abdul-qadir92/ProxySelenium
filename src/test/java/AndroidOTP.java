//import io.appium.java_client.MobileBy;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.ios.IOSElement;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.Test;
//
//import java.net.URL;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//
//public class AndroidOTP {
//    AndroidDriver<AndroidElement> driver;
//
//    @Test
//    public void Android() throws Exception {
//
//        String username = System.getenv("BROWSERSTACK_USERNAME");
//        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
//        final String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
//        bstackOptions.put("userName", username);
//        bstackOptions.put("accessKey", accessKey);
//        bstackOptions.put("buildName", "OTP Checks");
//        bstackOptions.put("sessionName", "Android");
//        bstackOptions.put("enableSim", "true");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("appium:platformVersion", "13.0");
//        capabilities.setCapability("appium:deviceName", "Samsung Galaxy S23 Ultra");
//        capabilities.setCapability("appium:app", "bs://c3fb127153e6999104240ba06e00c08b27c15c19");
//        capabilities.setCapability("bstack:options", bstackOptions);
//
//        driver = new AndroidDriver(new URL(URL), capabilities);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        AndroidElement txtBox = (AndroidElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//                ExpectedConditions.elementToBeClickable(driver.findElement(MobileBy.id("org.wikipedia.alpha:id/fragment_feed_header"))));
//        txtBox.click();
//
//        Map<String, Object> simDetails = (Map<String, Object>) js.executeScript("browserstack_executor:{\"action\": \"deviceInfo\", \"arguments\" : {\"deviceProperties\" : [\"simOptions\"]}}");
//        System.out.println(simDetails.get("Phone Number"));
//
//        Map<String, Object> sms = (Map<String, Object>) driver.executeScript("mobile:listSms");
//        ArrayList msg = (ArrayList) sms.get("items");
//        Map<String, Object> body = (Map<String, Object>) msg.get(0);
//        int index = body.get("body").toString().indexOf("OTP:")+5;
//        AndroidElement Searchbox = (AndroidElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//                ExpectedConditions.elementToBeClickable(driver.findElement(MobileBy.id("org.wikipedia.alpha:id/search_src_text"))));
//        Searchbox.sendKeys(body.get("body").toString().substring(index,index+5));
//
//        assertEquals(Searchbox.getText(),body.get("body").toString().substring(index,index+5));
//
//    }
//
//    @AfterTest
//    public void cleanUP() throws Exception {
//        driver.quit();
//    }
//
//}
