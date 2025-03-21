package hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    public static WebDriver driver;
    
        //setup driver
        @Before
        public void setupAutomation() throws IOException{
            Properties properties = new Properties();
    
            FileInputStream fileInputStream = new FileInputStream("C:/Users/Admin/Web Automation/src/main/java/com/webautomation/GlobalData.properties");
             
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
            System.out.println("ini adalah driver1" + driver);
            
        }
    
        @After
        public void tearDownAutomation(){
            if (driver != null) {
                driver.close();
            }
        }    
        
        public static WebDriver initializeDriver(){
            System.out.println("ini adalah driver" + driver);
            return driver;
    }


}
