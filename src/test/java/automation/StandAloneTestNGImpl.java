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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webautomation.pageobjects.CartPage;
import com.webautomation.pageobjects.LandingPage;
import com.webautomation.pageobjects.ProductListPage;
import com.webautomation.pageobjects.Checkout1Page;
import com.webautomation.pageobjects.Checkout2Page;
import com.webautomation.pageobjects.ConfirmationPage;

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
        
        // Scenario Login
        /* 
         * Semisal usename & password ganti terus menerus. 
         * Improve: Maka perlu adanya centralized. Untuk menyimpan json
         * Menggunakan Data Provider
         */

        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication(input.get("user-name"), input.get("password"));
                
        String productName = "Sauce Labs Backpack";
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addToCart(productName);

        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();

        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckoutPage();
        
      //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder = 'First Name']")));

        Checkout1Page orderPage = new Checkout1Page(driver);
        orderPage.fillOrderForm(input.get("first-name"), input.get("last-name"), input.get("postal-code"));
        orderPage.placeOrder();

      //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        Checkout2Page summaryOrder = new Checkout2Page(driver);
        String summaryOrderText = summaryOrder.getSummaryOrder();
        Assert.assertEquals(summaryOrderText, "Checkout: Overview");
        summaryOrder.clickFinish();

      //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String confirmationPageText = confirmationPage.getConfirmationPage();
        Assert.assertEquals(confirmationPageText, "Thank you for your order!");

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
    map.put("first-name","Ketika");
    map.put("last-name","Cintawa");
    map.put("postal-code","64131");
    return new Object[][] {{map}};

 }

}
