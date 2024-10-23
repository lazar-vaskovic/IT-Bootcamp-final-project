package Tests;

import Base.BaseTest;
import Pages.*;
import com.beust.ah.A;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        footerPage = new FooterPage();
        sidebarPage = new SidebarPage();
        shoppingCartPage = new ShoppingCartPage();
        checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepTwoPage = new CheckoutStepTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();
        singleItemsPage = new SingleItemsPage();
        userIsLoggedIn();
        userAddedProductToCartAndProceededToCheckout();
    }

    @Test(priority = 10)
    public void userCanPlaceOrder() throws InterruptedException {
        String validFirstName = excelReader.getStringData("Sheet1", 1, 5);
        String validLastName = excelReader.getStringData("Sheet1", 1, 6);
        String validPostalCode = excelReader.getStringData("Sheet1", 1, 7);

        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertTrue(shoppingCartPage.checkIsItemInTheCart(excelReader.getStringData("Sheet1", 0,9)));
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "1");
        checkoutStepTwoPage.clickOnFinishButton();
        Assert.assertTrue(checkoutCompletePage.completeCheckoutMessage.isDisplayed());
        Assert.assertTrue(checkoutCompletePage.backHomeButton.isDisplayed());
        Assert.assertEquals(checkoutCompletePage.checkoutCompletedTitle.getText(), "Checkout: Complete!");
    }

    @Test(priority = 20)
    public void userCannotPlaceOrderWithEmptyFirstNameField() throws InterruptedException {
        String validLastName = excelReader.getStringData("Sheet1", 1, 6);
        String validPostalCode = excelReader.getStringData("Sheet1", 1, 7);

        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertTrue(checkoutStepOnePage.continueButton.isDisplayed());
        Assert.assertTrue(checkoutStepOnePage.errorMessage.isDisplayed());
        Assert.assertTrue(checkoutStepOnePage.errorMessageContains("First Name"));
    }

    @Test(priority = 30)
    public void userCannotPlaceOrderWithEmptyLastNameField() throws InterruptedException {
        String validFirstName = excelReader.getStringData("Sheet1", 1, 5);
        String validPostalCode = excelReader.getStringData("Sheet1", 1, 7);

        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertTrue(checkoutStepOnePage.continueButton.isDisplayed());
        Assert.assertTrue(checkoutStepOnePage.errorMessage.isDisplayed());
        Assert.assertTrue(checkoutStepOnePage.errorMessageContains("Last Name"));
    }

    @Test(priority = 40)
    public void userCannotPlaceOrderWithEmptyPostalCodeField() throws InterruptedException {
        String validFirstName = excelReader.getStringData("Sheet1", 1, 5);
        String validLastName = excelReader.getStringData("Sheet1", 1, 6);

        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertTrue(checkoutStepOnePage.continueButton.isDisplayed());
        Assert.assertTrue(checkoutStepOnePage.errorMessage.isDisplayed());
        Assert.assertTrue(checkoutStepOnePage.errorMessageContains("Postal Code"));
    }

    @AfterMethod
    public void ThreadSleep () throws InterruptedException {
        driver.quit();
    }



}
