package com.webautomation.locator;

import java.time.Duration;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.interactions.Actions;

public class TaskLocator {
    public static void main(String[] args) throws InterruptedException {

        /*
         * Setup
         */

        System.setProperty("webdriver.chrome.driver",
                "C:/Users/Admin/Web Automation Batch 2/webautomationbatch2/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        /*
         * Radio Button
         */

        // Radio 1
        WebElement radio1 = driver.findElement(By.xpath("(//input[@value='radio1'])[1]"));
        radio1.click();
        Thread.sleep(2000);

        // Radio 2
        WebElement radio2 = driver.findElement(By.xpath("(//input[@value='radio2'])[1]"));
        radio2.click();
        Thread.sleep(2000);

        // Radio 3
        WebElement radio3 = driver.findElement(By.xpath("(//input[@value='radio3'])[1]"));
        radio3.click();
        Thread.sleep(2000);

        /*
         * Suggestion Class
         */

        driver.findElement(By.xpath("(//input[@id='autocomplete'])[1]")).sendKeys("Indonesia");

        Thread.sleep(3000);

        List<WebElement> country = driver.findElements(By.cssSelector("li.ui-menu-item div.ui-menu-item-wrapper"));

        for (WebElement webElement : country) {
            System.out.println("Ini adalah negara " + webElement.getText());
            if (webElement.getText().equals("Indonesia")) {
                webElement.click();
                break;
            }
        }

        Thread.sleep(2000);

        /*
         * Dropdown
         */

        WebElement staticDropdown = driver.findElement(By.id("dropdown-class-example"));

        Select dropdown = new Select(staticDropdown);

        System.out.println("All option" + dropdown.getAllSelectedOptions().size());
        System.out.println("First option" + dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Select");
        System.out.println("Select" + dropdown.getFirstSelectedOption().getText());

        Thread.sleep(2000);

        dropdown.selectByValue("option1");

        Thread.sleep(2000);

        dropdown.selectByValue("option2");

        Thread.sleep(2000);

        dropdown.selectByIndex(3);

        Thread.sleep(2000);

        /*
         * Checkbox
         */

        driver.findElement(By.id("checkBoxOption1")).click();
        driver.findElement(By.id("checkBoxOption2")).click();
        driver.findElement(By.id("checkBoxOption3")).click();

        Thread.sleep(2000);

        /*
         * Switch Windows
         */

         driver.findElement(By.id("openwindow")).click();

         Set<String> windows = driver.getWindowHandles();

         Iterator<String> iterator = windows.iterator();
         String parentId = iterator.next();
         String childId = iterator.next();

         driver.switchTo().window(childId);

         System.out.println("Ini adalah windows" + windows);

         Thread.sleep(5000);

         driver.close();

         driver.switchTo().window(parentId);

         System.out.println("Kembali ke jendela utama. Ini adalah windows: " + driver.getWindowHandles());

         Thread.sleep(5000);

         /*
          * Switch Tab
          */

        driver.findElement(By.id("opentab")).click();

        windows = driver.getWindowHandles();
        iterator = windows.iterator();
        parentId = iterator.next(); 
        childId = iterator.next();

        driver.switchTo().window(childId);

        System.out.println("Beralih ke tab baru. Ini adalah windows: " + windows);

        Thread.sleep(5000);

        driver.close();

        driver.switchTo().window(parentId);

        System.out.println("Kembali ke tab utama. Ini adalah windows: " + driver.getWindowHandles());

        Thread.sleep(3000);

        /*
         * Switch to Alert
         */

        driver.findElement(By.id("name")).sendKeys("Ketika Cintawa");
        Thread.sleep(3000);

        driver.findElement(By.id("alertbtn")).click();
        Thread.sleep(3000);

        System.out.println(driver.switchTo().alert().getText());
        Thread.sleep(3000);

        driver.switchTo().alert().accept();

        /*
         * Swith to alert : confirm
         */

         Thread.sleep(5000);

         driver.findElement(By.id("name")).sendKeys("Ketika Cintawa");
         Thread.sleep(4000);

         driver.findElement(By.id("confirmbtn")).click();
         Thread.sleep(3000);

         System.out.println(driver.switchTo().alert().getText());
         Thread.sleep(3000);

         driver.switchTo().alert().accept();
         Thread.sleep(4000);

        /*
         * Web Table
         */

        WebElement table = driver.findElement(By.id("product"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("Jumlah course: " + (rows.size() - 1));

        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
        
            String instructor = columns.get(0).getText();
            String course = columns.get(1).getText();
            String price = columns.get(2).getText();
            
            System.out.println(i + ". " + course + " - $" + price);
            
            Thread.sleep(5000);

    }

        /*
         * Element Displayed
         */

         driver.findElement(By.id("displayed-text")).sendKeys("Ketika");
         Thread.sleep(4000);

         //Hide
         driver.findElement(By.id("hide-textbox")).click();
         Thread.sleep(5000);

         //Show
         driver.findElement(By.id("show-textbox")).click();
         Thread.sleep(5000);

         String textValue = driver.findElement(By.id("displayed-text")).getAttribute("value");
         System.out.println("Value Element Displayed: " + textValue);

         Thread.sleep(4000);

         /*
          * Web Table Fixed Header
          */

        WebElement fixedTable = driver.findElement(By.className("tableFixHead"));

        List<WebElement> rows1 = fixedTable.findElements(By.tagName("tr"));
        System.out.println("Jumlah data dalam tabel: " + (rows1.size() - 1));

        List<WebElement> amounts = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));

        int totalAmount = 0;
        for (int i = 1; i < rows1.size(); i++) {
            List<WebElement> columns = rows1.get(i).findElements(By.tagName("td"));
            
            String name = columns.get(0).getText();
            String position = columns.get(1).getText();
            String city = columns.get(2).getText();
            int amount = Integer.parseInt(columns.get(3).getText());
            
            totalAmount += amount;
            
            System.out.println(name + " - " + position + " - " + city + " - " + amount);
        }

        System.out.println("\nTotal Amount Calculated: " + totalAmount);

        /*
         * Mouse Hover
         */

        Actions actions = new Actions(driver);
        
        WebElement mouseHover = driver.findElement(By.id("mousehover"));

        actions.moveToElement(mouseHover).perform();

        Thread.sleep(2000);

        WebElement topLink = driver.findElement(By.xpath("//div[@class='mouse-hover-content']/a[text()='Top']"));
        topLink.click();

        Thread.sleep(2000);

        actions.moveToElement(mouseHover).perform();
        Thread.sleep(2000);
        WebElement reloadLink = driver.findElement(By.xpath("//div[@class='mouse-hover-content']/a[text()='Reload']"));
        reloadLink.click();
                
        /*
         * iFrame
         */

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Jumlah iFrame pada halaman: " + iframes.size());

        Thread.sleep(5000);

        driver.switchTo().frame("courses-iframe");

        Thread.sleep(5000);

        // Interaksi : Mencoba menekan All access plan
        WebElement accessPlan = driver.findElement(By.xpath("//a[@class='new-navbar-highlighter'][normalize-space()='All Access plan']"));
        accessPlan.click();

        Thread.sleep(5000);

        driver.switchTo().defaultContent();

        Thread.sleep(2000);

        driver.quit();
    }

}
