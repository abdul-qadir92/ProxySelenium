import com.browserstack.local.Local;
import com.google.gson.Gson;
import io.percy.selenium.Percy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

public class HMPO {
    Local l;
    WebDriver driver;
    Percy percy;
    WebDriverWait wait;


    By btnStart = By.xpath("//span[contains(text(),'Start now')]");
    By rduk = By.xpath("//div[@class='govuk-radios__item'][1]/input");

    @Test
    public void safariiOS() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "qUepq4NFxRaVHxuxJQAo");
        //l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:qUepq4NFxRaVHxuxJQAo@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        //browserstackOptions.put("os", "Windows");
        //browserstackOptions.put("osVersion", "16"); //12
        //browserstackOptions.put("browserVersion", "latest");
        //browserstackOptions.put("deviceName", "iPhone 14"); //Google Pixel 5
        //browserstackOptions.put("local", "false");
        //browserstackOptions.put("seleniumVersion", "4.6.0");
        //browserstackOptions.put("geoLocation", "ES");
        //browserstackOptions.put("interactiveDebugging", "true");
       // browserstackOptions.put("safariAllowPopups", "true");
       // browserstackOptions.put("autoAcceptAlerts", "true");
       // browserstackOptions.put("appiumVersion", "1.22.0");
       // browserstackOptions.put("buildName", "HMPO" + d.getTime());
       // caps.setCapability("bstack:options", browserstackOptions);
        FirefoxOptions opt = new FirefoxOptions(); driver = new ChromeDriver();//RemoteWebDriver(new URL(URL), caps);
        //wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.passport.service.gov.uk/filter/overseas");
        WebElement start = wait.until(ExpectedConditions.elementToBeClickable(btnStart));
        js.executeScript("arguments[0].click();", start);

        WebElement UK = wait.until(ExpectedConditions.presenceOfElementLocated(rduk));
        js.executeScript("arguments[0].scrollIntoView(true);", UK);
        UK.click();

        WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        cont.click();
        WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='date-of-birth-day']")));
        day.sendKeys("1");
        WebElement month = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='date-of-birth-month']")));
        month.sendKeys("1");
        WebElement year = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='date-of-birth-year']")));
        year.sendKeys("1999");
        cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        cont.click();

        WebElement pass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='govuk-radios__item'][1]/input")));
        js.executeScript("arguments[0].scrollIntoView(true);", pass);
        pass.click();
        cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        cont.click();

        Thread.sleep(2000);
        cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form/button[contains(text(),'Continue')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", cont);
        cont.click();

        Thread.sleep(2000);
        cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form/button[contains(text(),'Continue')]")));
        try{
            js.executeScript("arguments[0].scrollIntoView(true);", cont);
            cont.click();
        }catch (StaleElementReferenceException e){
            cont = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/button[contains(text(),'Continue')]")));
            cont.click();
        }

        WebElement photo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='govuk-radios__item'][1]/input")));
        js.executeScript("arguments[0].scrollIntoView(true);", photo);
        photo.click();
        String parent = driver.getWindowHandle();
        WebElement code = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='photo with a code']")));
        code.click();

        try {
            driver.switchTo().alert().accept();  //ios safari
        }catch(Exception e){
        }
        Thread.sleep(3000);

        Set<String> s = driver.getWindowHandles();
        // Now iterate using Iterator
        System.out.println("Total Windows:"+s.size());
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parent);
        System.out.println(driver.getTitle());
    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
        if(l != null) {
            l.stop();
        }
    }
}
