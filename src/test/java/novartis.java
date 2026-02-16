import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class novartis {
    @Test
    public void BrowserWidth() throws Exception{
        // Use WebDriverManager to setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.pro.novartis.com/jp-ja/node/186");
            // Wait for the cookie banner and accept it
            Thread.sleep(3000); // Simple wait for demo; replace with WebDriverWait for production
            // Try to find and click the cookie accept button
            try {
                WebElement acceptBtn = driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler, button[aria-label='同意する'], button[title='同意する']"));
                acceptBtn.click();
            } catch (Exception e) {
                System.out.println("Cookie accept button not found or already accepted.");
            }
            System.out.println("Executing BrowserWidth test for Novartis.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
