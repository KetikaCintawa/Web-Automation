package com.webautomation.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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

        Thread.sleep(3000);

        /*
         * Dropdown
         */

        WebElement staticDropdown = driver.findElement(By.id("dropdown-class-example"));

        Select dropdown = new Select(staticDropdown);

        System.out.println("All option" + dropdown.getAllSelectedOptions().size());
        System.out.println("First option" + dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Select");
        System.out.println("Select" + dropdown.getFirstSelectedOption().getText());

        Thread.sleep(4000);

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

        driver.quit();

        /*
         * Lanjut besok..
         */

    }

}
