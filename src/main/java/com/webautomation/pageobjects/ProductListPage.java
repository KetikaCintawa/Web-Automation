package com.webautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {
    WebDriver driver;
    WebElement product;
    
    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_item")
    List<WebElement> listProducts;

    By cartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    By titleProduct = By.cssSelector(".inventory_item_name");

    public List<WebElement> getProductList(){
        return listProducts;
    }

    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(prod -> 
        prod.findElement(titleProduct).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    public void addToCart(String productName) throws InterruptedException {
        product = getProductByName(productName);
        product.findElement(cartButton).click();
        Thread.sleep(2000);

    }

}
