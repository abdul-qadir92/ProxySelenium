//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.ios.IOSElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
////Appium Java Client 7.6.0 for IOSElement
//
////Selenium 4.1.x
//public class iOSImages extends Thread{
//        public static String userName = "abdulqadirkhande_5QjKSy";
//        public static String accessKey = "";
//        public static void main(String args[]) throws MalformedURLException, InterruptedException {
//            DesiredCapabilities caps = new DesiredCapabilities();
//            caps.setCapability("device", "iPhone 12 Pro Max");
//            caps.setCapability("os_version", "14");
//            caps.setCapability("real_mobile", "true");
//            caps.setCapability("project", "iOS Images");
//            caps.setCapability("build", "My First Build");
//            caps.setCapability("name", "Bstack-[Java] Sample Test");
//            caps.setCapability("nativeWebTap", "true");
//            caps.setCapability("browserstack.uploadMedia", new String[]{"media://2c319d83056268e96c137ea448c01924aff6aa6b","media://949ce2f7656623d179a6c85aed47df2b3da38304"});
//            IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
//            driver.get("https://the-internet.herokuapp.com/upload");
//            Thread.sleep(5000);
//            driver.findElement(By.id("file-upload")).click();
//            driver.context("NATIVE_APP");
//            driver.findElement(By.name("Photo Library")).click();
//            Thread.sleep(5000);
//            List list = driver.findElements(By.className("XCUIElementTypeImage"));
//            System.out.println(list.get(0).toString());
//            ((IOSElement) list.get(1)).click();
//            Thread.sleep(5000);
//            driver.findElement(By.name("Choose")).click();
//            Set<String> contextName = driver.getContextHandles();
//            driver.context(contextName.toArray()[1].toString());
//            driver.findElement(By.id("file-submit")).click();
//            driver.quit();
//        }
//}
