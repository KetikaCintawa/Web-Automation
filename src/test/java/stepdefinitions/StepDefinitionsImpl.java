package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.webautomation.pageobjects.CartPage;
import com.webautomation.pageobjects.Checkout1Page;
import com.webautomation.pageobjects.Checkout2Page;
import com.webautomation.pageobjects.ConfirmationPage;
import com.webautomation.pageobjects.FilteringProducts;
import com.webautomation.pageobjects.LandingPage;
import com.webautomation.pageobjects.ProductDetailPage;
import com.webautomation.pageobjects.ProductListPage;

import components.BaseTest;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

    @When("^Buyer clicks the product titled (.+)$")
    public void buyerClickProduct(String productName){
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.getProductByName(productName).findElement(By.className("inventory_item_name")).click();

    }

    @Then("^Buyer should see the product details for (.+)$")
    public void buyerSeeProductDetails(String expectedProduct){
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        String actualProductTitle = productDetailPage.getProductTitle();
        System.out.println("Actual product title captured: " + actualProductTitle);

        if (actualProductTitle.equals(expectedProduct)){
            System.out.println("Test passed! Correct product displayed.");
        } else {
            System.out.println("Bug detected!");
            System.out.println("Expected:" + expectedProduct);
            System.out.println("Actual: " + actualProductTitle);

            Assert.fail("Bug detected! Expected:" + expectedProduct + "but got:" + actualProductTitle);
        }
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

    @When("^Buyer logged to website acceptable email (.+) and password (.+)$")
    public void buyerLoginAcceptable(String email, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication(email, password);
    }

    @Then("^Buyer will see list products (.+)$")
    public void buyerSeeProducts(String order){
        ProductListPage productPage = new ProductListPage(driver);
        String productTagText = productPage.getProductTag();
        Assert.assertEquals(productTagText, order);

    }

    @When("^Blocked Buyer logged to website with their email (.+) and password (.+)$")
    public void lockedOutBuyerLogin(String email, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication(email, password);
        String lockedOutError = landingPage.getLockedOutError();
        Assert.assertEquals(lockedOutError, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Then("^Buyer will see error (.+)$")
    public void buyerSeeLockedOutError(String errorTag){
        LandingPage landingPage = new LandingPage(driver);
        String errorTagText = landingPage.getLockedOutError();
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
