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
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Zenni {

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
        browserstackOptions.put("deviceName", "iPhone 13");
        browserstackOptions.put("osVersion", "13"); //12
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("local", "true");
        browserstackOptions.put("interactiveDebugging", "true");
        browserstackOptions.put("video", "true");
        browserstackOptions.put("buildName", "Zenni" + d.getTime());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), caps);
        percy = new Percy(driver);
        //wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://ca.zennioptical.com/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='switch-country']/div/div[2]/div/div[2]/div[2]/button")));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        }catch(Exception e){

        }
        WebElement country = driver.findElement(By.xpath("//*[@id='switch-country']/div/div[2]/div/div[2]/div[2]/button"));
        js.executeScript("arguments[0].click();",country);
        Thread.sleep(2000);
        js.executeScript("window.scrollTo({ top:document.body.scrollHeight, behavior: 'smooth' })");
        percy.snapshot("Home");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[5]/span")));
        WebElement menu = driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[5]/span"));
        js.executeScript("arguments[0].click();",menu);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='zenni-header-menu-item-MEN-1']")));
        driver.findElement(By.xpath("//*[@id='zenni-header-menu-item-MEN-1']")).click();
        Thread.sleep(2000);
        WebElement help = driver.findElement(By.xpath("//*[@id='content-title-HELP-1']/span"));
        //js.executeScript("arguments[0].scrollIntoView();",help);
        Map<String, Object> opt = new HashMap<String, Object>();
        opt.put("scope", "#zenni-header-menu-1");
        Map<String, Object> scopeOptions = new HashMap<String, Object>();
        scopeOptions.put("scroll", true);
        opt.put("scopeOptions", scopeOptions);
            opt.put("percyCSS", "#zenni-header-menu-1 { position: fixed !important; top: 1px; height: calc(100% - 1px) !important; display: block; }");
        percy.snapshot("Menu",opt);
    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
        if(l != null) {
            l.stop();
        }
    }
}
