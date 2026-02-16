import com.browserstack.local.Local;
import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class layoutPercy {
    Local l;
    WebDriver driver;
    Percy percy;
    WebDriverWait wait;

    @Test
    public void LayoutPercy() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "1tqwytqwEwJSAvksxn7y");
        l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:1tqwytqwEwJSAvksxn7y@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("browserVersion", "112");
        browserstackOptions.put("osVersion", "10"); //12
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("local", "true");
        browserstackOptions.put("interactiveDebugging", "true");
        browserstackOptions.put("video", "true");
        browserstackOptions.put("buildName", "Layout");
        browserstackOptions.put("projectName", "PercyLayout");
        Date date = new Date(d.getTime() + 24 * 60 * 60 * 1000);
        browserstackOptions.put("sessionName", date.toString() );
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), caps);
        percy = new Percy(driver);
       // wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://www.zennioptical.com/");
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='large-content-carousel-widget-child']/div[4]/div[2]/span[4])[1]")));
            driver.findElement(By.xpath("(//*[@id='large-content-carousel-widget-child']/div[4]/div[2]/span[4])[1]")).click();
        }catch(Exception e){

        }
        js.executeScript("window.scrollTo({ top:document.body.scrollHeight, behavior: 'smooth' })");
        Thread.sleep(5000);
        Map<String, Object> opt = new HashMap<String, Object>();opt.put("enableLayout",true);
        percy.snapshot("Home",opt);

        driver.navigate().to("https://ca.zennioptical.com/b/all-men-glasses");
        js.executeScript("window.scrollTo({ top:document.body.scrollHeight, behavior: 'smooth' })");
        Thread.sleep(5000);
        percy.snapshot("Mens",opt);

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
