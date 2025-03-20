package com.webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout1Page {
 WebDriver driver;

    public Checkout1Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder = 'First Name']")
    WebElement firstNameInput;

    @FindBy(css = "[placeholder = 'Last Name']")
    WebElement lastNameInput;

    @FindBy(css = "[placeholder = 'Zip/Postal Code']")
    WebElement zipCodeInput;

    @FindBy(css = ".cart_button")
    WebElement placeOrderButton;

    public void fillOrderForm(String firstName, String lastName, String zipCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        zipCodeInput.sendKeys(zipCode);
    }

    public void placeOrder() {
        placeOrderButton.click();
    }
    
    
    /*
     *  Actions action =  new Actions(driver);

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
     */
}
