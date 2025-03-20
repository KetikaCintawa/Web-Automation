package com.webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    WebElement confirmationPage;

    By confirmation = By.className("complete-header");

    public String getConfirmationPage(){
        visibilityOfElementLocated(confirmation);
        return confirmationPage.getText();
    }


}
