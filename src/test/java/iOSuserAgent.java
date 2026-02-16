//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.ios.IOSElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.safari.SafariOptions;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class iOSuserAgent {
//    public static String userName = "abdulqadirkhan_8lR39E";
//    public static String accessKey = "cq5VhvHUmwoxFPKHyhCa";
//    public static void main(String args[]) throws MalformedURLException, InterruptedException {
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("browserstack.headerParams", "{ \"User-Agent\":\"Mozilla/5.0 (Linux; Android 9.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Mobile Safari/537.36 Frontend-Automation\"}");
//        //caps.setCapability("browserstack.local", "true");
//        caps.setCapability("browserstack.idleTimeout",300);
//        caps.setCapability("browserstack.networkLogs",true);
//        caps.setCapability("device", "Samsung Galaxy S22");
//        caps.setCapability("os_version", "12");
//        caps.setCapability("real_mobile", "true");
//        caps.setCapability("project", "iOS UserAgent");
//        caps.setCapability("build", "My First Build");
//        caps.setCapability("name", "Bstack-[Java] Sample Test");
//        caps.setCapability("nativeWebTap", "true");
//        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
//        driver.get("https://bstackdemo.com/");
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        String agent = (String) jse.executeScript("return navigator.userAgent");
//        driver.quit();
//    }
//}
