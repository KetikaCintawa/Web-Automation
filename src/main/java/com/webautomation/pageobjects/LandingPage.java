package com.webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* 
 * POM biasanya mencakup 1 screen page di web
 * ex: dash, myorders, order, verification page, etc.
 * bisa dipecah juga per service / modules
 * ex: dalam dashboard ada product, filter, etc.
 * masing - masing dibuat pomnya sendiri" per service
 */

public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement userEmail;

    @FindBy (xpath = "//input[@type='password']")
    WebElement userPassword;

    @FindBy (id = "login-button")
    WebElement LoginBtn;

    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        LoginBtn.click();
    }
    
}
