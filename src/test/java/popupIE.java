import com.browserstack.local.Local;
import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
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

public class popupIE {
    Local l;
    WebDriver driver;
    Percy percy;
    WebDriverWait wait;

    @Test
    public void edgeIE() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "qUepq4NFxRaVHxuxJQAo");
        //l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:qUepq4NFxRaVHxuxJQAo@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "IE");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("browserVersion", "11.0");
        browserstackOptions.put("edgeVersion", "115.0");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("idleTimeout", "300");
        browserstackOptions.put("machine", "150.129.2.67");
        browserstackOptions.put("buildName", "IEonEdge"); //+ d.getTime());
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.attachToEdgeChrome();
        caps.setCapability("se:ieOptions", ieOptions);
        caps.setCapability("bstack:options", browserstackOptions);
        //caps.setCapability("browserstack.ie.enablePopups", "true");
        driver = new RemoteWebDriver(new URL(URL), caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://www.webroot.com/services/popuptester1.htm?srsltid=AfmBOoqPIB88xJAzLdT2BV7nw0T3G1tdoKySXk4uqArR1XJH8_6q2Y1e");driver.getCurrentUrl();
    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
        if(l != null) {
            l.stop();
        }
    }
}
