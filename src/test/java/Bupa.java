import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.net.URL;
import java.util.HashMap;

public class Bupa {

        public static final String AUTOMATE_USERNAME = "abdulqadirkhande_5QjKSy";
        public static final String AUTOMATE_KEY = "qUepq4NFxRaVHxuxJQAo";
        public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

        public static void main(String[] args) throws Exception {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "Chrome");
            capabilities.setCapability("browserVersion", "latest");
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("deviceName", "Samsung Galaxy S23 Ultra");
            browserstackOptions.put("osVersion", "13.0");
            browserstackOptions.put("projectName", "Bstack-[Java] Sample file download");
            capabilities.setCapability("bstack:options", browserstackOptions);
            WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            // Navigate to the link
            driver.get("https://www.browserstack.com/test-on-the-right-mobile-devices");
            Thread.sleep(2000);

            // Accept the cookie popup
            driver.findElement(By.id("accept-cookie-notification")).click();

            // Find element by class name and store in variable "element"
            WebElement Element = driver.findElement(By.className("icon-csv"));

            // This will scroll the page till the element is found
            jse.executeScript("arguments[0].scrollIntoView();", Element);
            jse.executeScript("window.scrollBy(0,-100)");
            Thread.sleep(1000);

            // Click on the element to download the file
            Element.click();
            Thread.sleep(2000);

            // Check if file exists
            System.out.println(jse.executeScript("browserstack_executor: {\"action\": \"fileExists\", \"arguments\": {\"fileName\": \"BrowserStack - List of devices to test on.csv\"}}"));

            // Get file properties
            System.out.println(jse.executeScript("browserstack_executor: {\"action\": \"getFileProperties\", \"arguments\": {\"fileName\": \"BrowserStack - List of devices to test on.csv\"}}"));

            // Get file content. The content is Base64 encoded
            String base64EncodedFile = (String) jse.executeScript("browserstack_executor: {\"action\": \"getFileContent\", \"arguments\": {\"fileName\": \"BrowserStack - List of devices to test on.csv\"}}");

            // Decode the content to Base64
            byte[] data = Base64.getDecoder().decode(base64EncodedFile);
            OutputStream stream = new FileOutputStream("BrowserStack%20-%20List%20of%20devices%20to%20test%20on.csv");
            stream.write(data);
            stream.close();

            driver.quit();
        }

}