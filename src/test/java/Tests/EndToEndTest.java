package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EndToEndTest extends BaseTest {

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
    }

    @Test(priority = 10)
    public void userCanPlaceOrderUsingSwagLabsWebsite() throws InterruptedException {
        String validUsername = excelReader.getStringData("Sheet1", 1, 0);
        String validPassword = excelReader.getStringData("Sheet1", 1, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.appLogo.isDisplayed());
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 5,9));
        inventoryPage.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.itemName.getText(), excelReader.getStringData("Sheet1", 5,9));
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "1");
        shoppingCartPage.clickOnCheckoutButton();
        String validFirstName = excelReader.getStringData("Sheet1", 1, 5);
        String validLastName = excelReader.getStringData("Sheet1", 1, 6);
        String validPostalCode = excelReader.getStringData("Sheet1", 1, 7);
        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        checkoutStepTwoPage.clickOnFinishButton();
        sidebarPage.clickOnBurgerMenuButton();
        Assert.assertTrue(checkoutCompletePage.completeCheckoutMessage.isDisplayed());
        Assert.assertTrue(checkoutCompletePage.backHomeButton.isDisplayed());
        Assert.assertEquals(checkoutCompletePage.checkoutCompletedTitle.getText(), "Checkout: Complete!");
        Thread.sleep(1000);
        sidebarPage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
    }

    @AfterMethod
    public void ThreadSleep () throws InterruptedException {
        driver.quit();
    }
}
