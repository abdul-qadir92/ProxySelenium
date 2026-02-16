import com.browserstack.local.Local;
import com.google.gson.Gson;
import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.net.URL;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Accessibility {
    Local l;
    WebDriver driver;
    Percy percy;
    @Test
    public void runAxe() throws Exception {
        l = new Local();
        Map<String, String> localoptions = new HashMap<String, String>();
        localoptions.put("key", "qUepq4NFxRaVHxuxJQAo");
        l.start(localoptions);
        final String URL = "https://abdulqadirkhande_5QjKSy:qUepq4NFxRaVHxuxJQAo@hub-cloud.browserstack.com/wd/hub";
        Date d = new Date();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "Chrome");
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                //browserstackOptions.put("os", "Windows");
                browserstackOptions.put("osVersion", "10");
                //browserstackOptions.put("browserVersion", "latest");
                browserstackOptions.put("deviceName", "Samsung Galaxy Note 20");
                browserstackOptions.put("local", "true");
                //browserstackOptions.put("seleniumVersion", "4.6.0");
                //browserstackOptions.put("geoLocation", "ES");
                browserstackOptions.put("buildName", "Core Axe Accessibility"+d.getTime());
            caps.setCapability("bstack:options", browserstackOptions);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new RemoteWebDriver(new URL(URL), caps);
            percy = new Percy(driver);
            driver.get("https://webcammictest.com/");
            driver.findElement(By.xpath("//button[contains(text(), 'Test webcam')]")).click();
            Thread.sleep(2000);
            //driver.switchTo().alert().accept();
            Thread.sleep(2000);
            driver.quit();
            percy.snapshot("Home Page");

            JavascriptExecutor jse = (JavascriptExecutor)driver;
            Path path = Paths.get("/Users/qadir/Documents/BS-Demos/axe/axe.min.js");

            String content = new String(Files.readAllBytes(path));
            jse.executeScript(content);

            File output = new File("src/test/resources/report/report.json");
            FileWriter writer = new FileWriter(output);
            Object result = jse.executeAsyncScript("var callback = arguments[arguments.length - 1]; " +
                "axe.configure({reporter: 'no-passes'});" +
                "axe.run({ runOnly: { type: 'tag', values: ['wcag2a'] }}, (err, results) => {callback(results);});");
            Gson g = new Gson();
            String p = g.toJson(result);
            writer.write(p);
            writer.flush();
            writer.close();
    }

    @AfterTest
    public void cleanUP() throws Exception {
        driver.quit();
        if(l != null) {
            l.stop();
        }
    }
}
