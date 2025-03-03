package com.webautomation.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LocatorPractice {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/Admin/Web Automation Batch 2/webautomationbatch2/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Thread.sleep(5000);

        /*
         * Sellect currency
         * condition: dropdowns
         */

        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        
        Select dropdown = new Select(staticDropdown);

        System.out.println("All option" + dropdown.getAllSelectedOptions().size());
        System.out.println("First option" + dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("AED");
        System.out.println("AED" + dropdown.getFirstSelectedOption().getText());
        
        Thread.sleep(2000);

        dropdown.selectByValue("USD");

        Thread.sleep(2000);

        dropdown.selectByIndex(1);

        Thread.sleep(2000);

        /*
         * Handle dynamic dropdown
         */

        driver.findElement(By.id("divpaxinfo")).click();

        Thread.sleep(5000);

        /*
         * Menambahkan passenger
         */

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
                
        }

        //Child
        
        for (int i = 0; i < 4; i++) {
            driver.findElement(By.id("hrefIncChd")).click();
                
        }
        Thread.sleep(5000);

        //Decrease Adult

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.id("hrefDecAdt")).click();
        }

        Thread.sleep(4000);

        driver.findElement(By.id("btnclosepaxoption")).click();

        Thread.sleep(4000);


        /*
         * Scenario select From
         * Delhi = //div[@id="dropdownGroup1"]//child::div[@class="dropdownDiv"]//child::ul[1]//child::li//child::a[@value="DEL"]
         */

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        
        // WebElement countryDelhi = driver.findElement(By.xpath("//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1]//child::li//child::a[@value='Bengalure']"));
        

        List<WebElement> options = driver.findElements(By.xpath("//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1]//child::li"));
        System.out.println("ini adldaj options" + options);

        for (WebElement element : options){
            System.out.println("list country" + element.getText());
            if (element.getText().equals("Durgapur (RDP)")) {
                element.click();
                break;
            }
        }

        Thread.sleep(4000);

        /*
         * Arrival City
         */

         List<WebElement> arrivalCity = driver.findElements(By.xpath(
            "(//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1])[2]//child::li"));

        for (WebElement element : arrivalCity){
            System.out.println("list country" + element.getText());
            if (element.getText().equals("Chennai (MAA)")) {
                element.click();
                break;
            }
        }

        //Handle suggestion
        driver.findElement(By.xpath("(//*[@id='autosuggest'])[1]")).sendKeys("ind");

        Thread.sleep(3000);

        // driver.findElement(By.xpath("//a[@id='ui-id-5']")).click();

        // Thread.sleep(3000);

        List<WebElement> country = driver.findElements( By.cssSelector("li[class='ui-menu-item'] a"));

        for (WebElement webElement : country) {
            System.out.println("Ini adalah negara " + webElement.getText());
            if (webElement.getText().equals("Indonesia")) {
                webElement.click();
                break;
            }
        }

        Thread.sleep(3000);

        //Hande radio button
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();

        //Handle checkbox
        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

        Thread.sleep(3000);

        driver.close();
    }

}
