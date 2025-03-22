package com.webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponents.AbstractComponent;

public class ProductDetailPage extends AbstractComponent{
    WebDriver driver;

    public ProductDetailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'inventory_details_name')]")
    WebElement productTitle;

    By title = By.cssSelector("[data-test='inventory-item-name']");

    public String getProductTitle() {
        visibilityOfElementLocated(title);
        String productTitleText = productTitle.getText();
        System.out.println("Product title extracted: " + productTitleText);
        return productTitle.getText();
    }

}
