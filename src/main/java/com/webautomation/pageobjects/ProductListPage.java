package com.webautomation.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webautomation.abstractcomponents.AbstractComponent;

public class ProductListPage extends AbstractComponent{
    WebDriver driver;
    WebElement product;
    WebDriverWait wait;
    
    public ProductListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_item")
    List<WebElement> listProducts;

    @FindBy(xpath = "//span[@class='title']")
    WebElement productTag;

    @FindBy(css= ".inventory_item_name")
    WebElement productTitle;

    By titleProduct = By.cssSelector(".inventory_item_name");
    By products = By.xpath("//span[@class='title']");
    By cartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']");

    public By getAddToCartButton(String productName){
        return By.xpath("//div[contains(text(),'" + productName + "')]/ancestor::div[@class='inventory_item']//button[contains(text(),'Add to cart')]");
    }

    public void addToCartError(String productName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(getAddToCartButton(productName)));
        addToCartButton.click();

    }

    public List<WebElement> getProductList(){
        return listProducts;
    }

    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(prod -> 
        prod.findElement(titleProduct).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    public void addToCart(String productName) throws InterruptedException {
        ((WebElement) cartButton).click();
        visibilityOfElementLocated(titleProduct);
        product = getProductByName(productName);
        product.findElement(cartButton).click();
        Thread.sleep(2000);

    }

    public String getProductTag(){
        visibilityOfElementLocated(products);
        return productTag.getText();
    }

    public String getProductTitle(){
        visibilityOfElementLocated(titleProduct);
        return productTitle.getText();
    }

    public ProductDetailPage clickProductByName(String productName) {
        visibilityOfElementLocated(titleProduct);
        WebElement product = getProductByName(productName);
        product.findElement(titleProduct).click();
        return new ProductDetailPage(driver);
    }


    }


