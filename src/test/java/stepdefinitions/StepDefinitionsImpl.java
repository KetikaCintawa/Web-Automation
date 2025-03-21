package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.webautomation.pageobjects.CartPage;
import com.webautomation.pageobjects.Checkout1Page;
import com.webautomation.pageobjects.Checkout2Page;
import com.webautomation.pageobjects.ConfirmationPage;
import com.webautomation.pageobjects.FilteringProducts;
import com.webautomation.pageobjects.LandingPage;
import com.webautomation.pageobjects.ProductListPage;

import components.BaseTest;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

// mendefinisikan step yang ada di feature
public class StepDefinitionsImpl extends BaseTest{
    public WebDriver driver;
    @Given("Buyer landing to ecommerce")
    public void landingPage() throws IOException{
    driver=Hooks.initializeDriver();
        
    }

    // (.+) artinya menerima inputan dari feature
    @Given("^Buyer logged to website email (.+) and password (.+)$")
    public void buyerLogin(String email, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication(email, password);
    }

    @When("^Buyer add product (.+) to Cart")
    public void buyerAddProduct(String productName) throws InterruptedException{
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addToCart(productName);
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
        
    }

    @And("Buyer checkout product")
    public void buyerCheckoutProduct() {
        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckoutPage();
        
    }
 
    @And("^Buyer place order first_name (.+) last_name (.+) and zip_code (.+)$")
    public void buyerPlaceOrder(String first_name, String last_name, String zip_code){
        Checkout1Page orderPage = new Checkout1Page(driver);
        orderPage.fillOrderForm(first_name, last_name, zip_code);
        orderPage.placeOrder();
        
    }

    @And("Buyer will see checkout overview")
    public void buyerSeeCheckoutOverview(){
        Checkout2Page summaryOrder = new Checkout2Page(driver);
        String summaryOrderText = summaryOrder.getSummaryOrder();
        Assert.assertEquals(summaryOrderText, "Checkout: Overview");
        summaryOrder.clickFinish();
        
    }
    
    @Then("^Buyer will receive confirmation page (.+)$")
    public void buyerSeeOrderConfirmation(String successCheckout){
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String confirmationPageText = confirmationPage.getConfirmationPage();
        Assert.assertEquals(confirmationPageText, successCheckout);        
    }

    @When ("^Buyer logged to website with wrong email (.+) or password (.+)$")
    public void buyerLoginn(String email, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication(email, password);
        String wrongCredentials = landingPage.getErrorBadge();
        Assert.assertEquals(wrongCredentials, "Epic sadface: Username and password do not match any user in this service");
    }

    @Then("^Buyer will see tag heading error (.+)$")
    public void buyerSeeError(String errorTag){
        LandingPage landingPage = new LandingPage(driver);
        String errorTagText = landingPage.getErrorBadge();
        Assert.assertEquals(errorTagText, errorTag);
    }

    @When("^Buyer filter products by (.+)$")
    public void buyerFilterProducts(String productType){
        FilteringProducts filteringProducts = new FilteringProducts(driver);
        switch (productType) {
            case "Name (A to Z)":
                filteringProducts.selectNameAToZ();
                break;
            case "Name (Z to A)":
                filteringProducts.selectNameZToA();
                break;
            case "Price (low to high)":
                filteringProducts.selectPriceLowToHigh();
                break;
            case "Price (high to low)":
                filteringProducts.selectPriceHighToLow(); // Pastikan metode ini ada di Page Object
                break;
            }
    }

    @Then("^Buyer will see products sorted by (.+)$")
    public void buyerSeeFilteredProducts(String productType){
        FilteringProducts filteringProducts = new FilteringProducts(driver);
        String selectedOption = filteringProducts.getSelectedFilterOption();
        Assert.assertEquals(selectedOption, productType, "Filter tidak cocok");

            }

}
