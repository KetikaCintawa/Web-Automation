package automation;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webautomation.pageobjects.LandingPage;
import com.webautomation.pageobjects.ProductListPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestNGImpl {
/*
 * Annotasi
 * Data provider
 * Running testng
 */

 public WebDriver driver;

 @BeforeMethod
 /* 
  * Fungsi: Supaya ketika menjalankan banyak fungsi tidak perlu memasukkan setup driver satu per satu
  */
 public void setup(){
    //Setup Driver
    System.setProperty("webdriver.chrome.driver","C:/Users/Admin/Web Automation Batch 2/webautomationbatch2/chromedriver.exe");

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://www.saucedemo.com/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 }
 
 @Test (dataProvider="dataTestMapping")
 public void createOder(HashMap<String, String> input) throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        
        // Scenario Login
        /* 
         * Semisal usename & password ganti terus menerus. 
         * Improve: Maka perlu adanya centralized. Untuk menyimpan json
         * Menggunakan Data Provider
         */

      LandingPage landingPage = new LandingPage(driver);
      landingPage.loginApplication(input.get("user-name"), input.get("password"));

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));

      String productName = "Sauce Labs Backpack";
      ProductListPage productListPage = new ProductListPage(driver);
      productListPage.addToCart(productName);

      //   System.out.println("list product" + product);

        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();

        driver.findElement(By.cssSelector(".checkout_button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder = 'First Name']")));

        Actions action =  new Actions(driver);

        action.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'First Name']")),"Ketika").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder = 'Last Name']")));

        action.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Last Name']")),"Cintawa").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder = 'Zip/Postal Code']")));

        action.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Zip/Postal Code']")),"64131").build().perform();

        Thread.sleep(3000);

        driver.findElement(By.cssSelector(".cart_button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        Thread.sleep(5000);

        driver.findElement(By.cssSelector(".cart_button")).click();

        String confirmationPage = driver.findElement(By.className("complete-header")).getText();

        Thread.sleep(5000);

        System.out.println("buyer berhasil checkout " + confirmationPage);

 }

 @AfterMethod
 /*
  * Fungsi: Setelah menjalankan akan close driver
  */
 public void tearDown(){
    driver.close();
 }

 @DataProvider
 public Object[][] dataTest(){
    return new Object[][]{
        {"standard_user", "secret_sauce", "Sauce Labs Backpack"},
    };
 }

 //Mapping
 @DataProvider
 public Object[][] dataTestMapping(){
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("user-name", "standard_user");
    map.put("password", "secret_sauce");
    map.put("product-name", "Sauce Labs Backpack");
    return new Object[][] {{map}};

 }

}
