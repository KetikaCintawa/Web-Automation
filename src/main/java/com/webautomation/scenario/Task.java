package com.webautomation.scenario;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        /*
         * 1. Implement e2e test for checkout
         * 2. Wait != Thread
         * 3. Stream
         */

        /*
         * Scenario automation
         * 1. Buyer Login
         * 2. Buyer checkout product
         * 3. Verifikasi thanks page
         */

        //Setup Driver
        System.setProperty("webdriver.chrome.driver","C:/Users/Admin/Web Automation Batch 2/webautomationbatch2/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        // Scenario Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("secret_sauce");

        driver.findElement(By.cssSelector("input.submit-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));

        //List Product
        List<WebElement> listProduct =  driver.findElements(By.cssSelector(".inventory_item"));

        System.out.println("Available Products:");
        listProduct.forEach(prod -> System.out.println(prod.getText()));

        String productName = "Sauce Labs Backpack";

        WebElement product = listProduct.stream().filter(prod -> 
        prod.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);

        product.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();

        System.out.println("list product" + product);

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

        driver.quit();

}
}
