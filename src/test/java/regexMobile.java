import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class regexMobile {

    public static final String AUTOMATE_USERNAME = "";
    public static final String AUTOMATE_KEY = "";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    static WebDriver driver;
    public static void main(String[] args) throws Exception {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "safari");
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("os", "iOS");
            browserstackOptions.put("deviceName", "iPhone 1*");
            browserstackOptions.put("osVersion", "[14151617]");
            browserstackOptions.put("projectName", "zz-Bstack");
            browserstackOptions.put("realMobile", true);
            browserstackOptions.put("idleTimeout", 300);
            browserstackOptions.put("interactiveDebugging", true);
            capabilities.setCapability("bstack:options", browserstackOptions);
            driver = new RemoteWebDriver(new URL(URL), capabilities);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            driver.manage().window().maximize();
            driver.get("https://www.browserstack.com/docs/automate/selenium/getting-started/java");
            String title = driver.getTitle();
            System.out.println(title);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            Thread.sleep(3000);
            WebElement a = driver.findElement(By.xpath("//li/a[contains(text(),'Capabilities builder')]"));
            js.executeScript("arguments[0].click();", a);
            Thread.sleep(3000);
            Set<String> windows = driver.getWindowHandles();
            System.out.println(windows);
            for (String window : windows) {
                driver.switchTo().window(window);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }finally {
            //driver.quit();
        }
    }
}
