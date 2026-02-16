import com.browserstack.local.Local;
import io.percy.selenium.Percy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Kainos {

    Local l;
    WebDriver driver;
    Percy percy;
    WebDriverWait wait;

    @Test
    public void edgecrash() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "1tqwytqwEwJSAvksxn7y");
        //l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:1tqwytqwEwJSAvksxn7y@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("resolution","3840x2160");
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("local", "false");
        //browserstackOptions.put("seleniumVersion", "4.5.3");
        browserstackOptions.put("buildName", "Kainos" + d.getTime());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), caps);
        //wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //driver.manage().window().maximize();
        driver.getCurrentUrl();
    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
        if(l != null) {
            l.stop();
        }
    }
}
