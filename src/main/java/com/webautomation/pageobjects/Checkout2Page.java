package com.webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout2Page {
    WebDriver driver;

    public Checkout2Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "header_secondary_container")
    WebElement summaryOrder;

    @FindBy(id = "finish")
    WebElement finishButton;
    
    public String getSummaryOrder(){
        return summaryOrder.getText();
    }

    public void clickFinish(){
        finishButton.click();
        }
    
}
