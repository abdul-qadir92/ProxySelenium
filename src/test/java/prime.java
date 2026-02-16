import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class prime {

    public static final String AUTOMATE_USERNAME = "abdulqadirkhande_5QjKSy";
    public static final String AUTOMATE_KEY = "qUepq4NFxRaVHxuxJQAo";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    static WebDriver driver;
    public static void main(String[] args) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("os", "OS X");
        browserstackOptions.put("osVersion", "Ventura");
        browserstackOptions.put("projectName", "Bstack-[Java] Action-API");
        browserstackOptions.put("buildName", "Swipe Action");
        browserstackOptions.put("geoLocation", "IN");
        capabilities.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL(URL), capabilities);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
       // WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get("https://www.primevideo.com/storefront/ref=atv_hom_pri_c_9zZ8D2_hm_mv?contentType=movie&contentId=home");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1355);","");
        Thread.sleep(3000);

       // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Thriller movies']")));
        WebElement ul = driver.findElement(By.xpath("//p[text()='Thriller movies']"));
        Actions act = new Actions(driver);
        //act.moveToElement(ul).perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",ul);

        //#region working
        /*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Thriller movies']/../../../../../div/ul/li[4]")));
        int item = 4;

        WebElement drag = driver.findElement(By.xpath("//p[text()='Thriller movies']/../../../../../div/ul/li["+item+"]"));
        int xoffset = 0-2*drag.getSize().width;

        boolean found = false;


        while(item!=20){
           //act.clickAndHold(drag).release(drag).perform();
            //https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html#moveToElement(org.openqa.selenium.WebElement)
            act.dragAndDropBy(drag,xoffset,0).perform();
            Thread.sleep(2000);
            item+=2;
            drag = driver.findElement(By.xpath("//p[text()='Thriller movies']/../../../../../div/ul/li["+item+"]"));
        }

         */
        //endregion

     //   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Thriller movies']/../../../../../div/ul/li[4]")));

        WebElement item = driver.findElement(By.xpath("//p[text()='Thriller movies']/../../../../../div/ul/li[4]"));
        int xoffset = 0-2*item.getSize().width;
        //Building a drag and drop action
        Actions Actions = new Actions(driver);
        int num = 4;
        while(num!=20) {
            Action dragAndDrop = Actions.clickAndHold(item)
                    .pause(200)
                    .moveByOffset(xoffset, 0)
                    .release()
                    .build();

            //Performing the drag and drop action
            dragAndDrop.perform();
            num += 2;
            item = driver.findElement(By.xpath("//p[text()='Thriller movies']/../../../../../div/ul/li[" + num + "]"));
        }

        driver.quit();
    }
}
