package com.webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponents.AbstractComponent;

public class Checkout2Page extends AbstractComponent{
    WebDriver driver;

    public Checkout2Page(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "header_secondary_container")
    WebElement summaryOrder;

    @FindBy(id = "finish")
    WebElement finishButton;

    By overview = By.className("header_secondary_container");
    
    public String getSummaryOrder(){
        return summaryOrder.getText();
    }

    public void clickFinish(){
        visibilityOfElementLocated(overview);
        finishButton.click();
        }
    
}
