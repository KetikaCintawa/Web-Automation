package com.webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FilteringProducts {
    WebDriver driver;

    public FilteringProducts(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*  WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        
        Select dropdown = new Select(staticDropdown);

        System.out.println("All option" + dropdown.getAllSelectedOptions().size());
        System.out.println("First option" + dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("AED");
        System.out.println("AED" + dropdown.getFirstSelectedOption().getText());

     * @FindBy(id = "user-name")
        WebElement userEmail;

     * Name (A to Z), Name (Z to A), Price (low to high)   
     */

     @FindBy(className = "product_sort_container")
     WebElement filteringOptions;

     public void selectNameAToZ(){
        Select dropdown = new Select(filteringOptions);
        dropdown.selectByVisibleText("Name (A to Z)");
     }

     public void selectNameZToA(){
        Select dropdown = new Select(filteringOptions);
        dropdown.selectByVisibleText("Name (Z to A)");
     }

     public void selectPriceLowToHigh(){
        Select dropdown = new Select(filteringOptions);
        dropdown.selectByVisibleText("Price (low to high)");
     }

     public void selectPriceHighToLow(){
        Select dropdown = new Select(filteringOptions);
        dropdown.selectByVisibleText("Price (high to low)");
     }

     public String getSelectedFilterOption() {
        Select dropdown = new Select(filteringOptions);
        return dropdown.getFirstSelectedOption().getText();
    }
    
    
}

