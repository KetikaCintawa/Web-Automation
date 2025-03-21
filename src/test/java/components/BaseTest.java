package components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
    public WebDriver initializeDriver() throws FileNotFoundException, IOException{
        /*
         * Check global.properties and get brower
         * if (browser == "chrome"){
         *      driver = chrome;
         * }else if(browser == "firefox") {
         *      driver = firefox;
         * }else{
         *      driver = edge
         * }
         */

         Properties properties = new Properties();
         FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Admin\\Web Automation\\src\\main\\java\\com\\webautomation\\GlobalData.properties");
         
         properties.load(fileInputStream);
         String browserName = properties.getProperty("browser");

         System.out.println("browserName" + browserName);

         if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:/Users/Admin/Web Automation Batch 2/webautomationbatch2/chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else{
            System.setProperty("webdriver.gecko.driver","C:/Users/Admin/Web Automation Batch 2/webautomationbatch2/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

         return driver;

    }
    }

