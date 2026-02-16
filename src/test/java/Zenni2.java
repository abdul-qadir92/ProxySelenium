import com.browserstack.local.Local;
import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Zenni2 {

    Local l;
    WebDriver driver;
    Percy percy;
    WebDriverWait wait;

    @Test
    public void ZenniPercy() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "qUepq4NFxRaVHxuxJQAo");
        l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:qUepq4NFxRaVHxuxJQAo@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("deviceName", "Samsung Galaxy S21");
        browserstackOptions.put("osVersion", "12"); //12
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("local", "true");
        browserstackOptions.put("interactiveDebugging", "true");
        browserstackOptions.put("video", "true");
        browserstackOptions.put("buildName", "Zenni2");
        Date date = new Date(d.getTime() + 24 * 60 * 60 * 1000);
        browserstackOptions.put("sessionName", date.toString() );
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), caps);
        percy = new Percy(driver);
        //wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.zennioptical.com/p/womens-memory-titanium-eyeglass-frames/2105?skuId=210519");
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
            driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
        }catch(Exception e){

        }
        Thread.sleep(5000);
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Privacy Policy']")));
        Thread.sleep(10000);
        WebElement policy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
        js.executeScript("arguments[0].scrollIntoView();",policy);
        Thread.sleep(3000);
        js.executeScript("window.scrollTo(0,0)");
        Thread.sleep(3000);
        js.executeScript("window.scrollTo({ top:document.body.scrollHeight, behavior: 'smooth' })");
        Thread.sleep(3000);
        Map<String, Object> opt = new HashMap<String, Object>();
        opt.put("percyCSS", "#mobile-buttons > div { display: none; }!important");
        percy.snapshot("Home",opt);
    }

    @AfterTest
    public void cleanUP() throws Exception {
        try{
            driver.quit();
        }catch(Exception e){}
        finally{
            if(l != null) {
                l.stop();
            }
        }
    }
}
