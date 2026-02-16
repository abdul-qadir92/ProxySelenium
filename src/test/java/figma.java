import com.browserstack.local.Local;
import io.percy.selenium.Percy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.*;

public class figma {

    Local l;
    WebDriver driver;
    Percy percy;
    WebDriverWait wait;

    @Test
    public void FigmaPercy() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "qUepq4NFxRaVHxuxJQAo");
        l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:qUepq4NFxRaVHxuxJQAo@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("local", "true");
        //browserstackOptions.put("interactiveDebugging", "true");
        //browserstackOptions.put("video", "true");
        browserstackOptions.put("buildName", "Figma" + d.getTime());
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("start-maximized");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), caps);
        percy = new Percy(driver);
        //wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println(js.executeScript("return [window.outerWidth - window.innerWidth + 1280,\n" + "window.outerHeight - window.innerHeight + 625];"));
        driver.manage().window().setSize(new Dimension(1296,772));
        driver.get("http://localhost:3001/?img=demopercy.png");
        percy.screenshot("Home");
    }

    @Test
    public void FigmaScope() throws Exception{
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "qUepq4NFxRaVHxuxJQAo");
        l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:qUepq4NFxRaVHxuxJQAo@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("local", "true");
        //browserstackOptions.put("interactiveDebugging", "true");
        //browserstackOptions.put("video", "true");
        browserstackOptions.put("buildName", "Figma" + d.getTime());
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-maximized");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), caps);
        percy = new Percy(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println(js.executeScript("return [window.outerWidth - window.innerWidth + 1280,\n" + "window.outerHeight - window.innerHeight + 625];"));
        driver.manage().window().setSize(new Dimension(1296,772));
        driver.get("https://bstackdemo.com/signin");
        js.executeScript("document.querySelector('#login-btn').innerText='Sign In'");
        Map<String, Object> opt = new HashMap<String, Object>();
        opt.put("scope", "#__next > div.login_wrapper > div");
        opt.put("percyCSS", "#__next > div.login_wrapper > div");
        List<Integer> list=new ArrayList<Integer>();
        list.add(1280);
        opt.put("widths", list);
        percy.snapshot("home_1280",opt);
    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
        if(l != null) {
            l.stop();
        }
    }
}
