package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

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
    public void userCanLogInWithValidCredentials() {
            String validUsername = excelReader.getStringData("Sheet1", 1, 0);
            String validPassword = excelReader.getStringData("Sheet1", 1, 1);
            loginPage.inputUsername(validUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(inventoryPage.shoppingCartIcon.isDisplayed());
            Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
            Assert.assertTrue(inventoryPage.appLogo.isDisplayed());
    }

    @Test(priority = 20)
    public void userCanLogInWithAllValidUsernames() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String validUsernames = excelReader.getStringData("Sheet1", i, 0);
            String validPassword = excelReader.getStringData("Sheet1", 1, 1);
            loginPage.inputUsername(validUsernames);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(inventoryPage.shoppingCartIcon.isDisplayed());
            Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
            Assert.assertTrue(inventoryPage.appLogo.isDisplayed());
            sidebarPage.clickOnBurgerMenuButton();
            Thread.sleep(1000);
            sidebarPage.clickOnLogoutButton();
        }
    }

    @Test (priority = 30)
    private void userCannotLoginWithInvalidUsername () {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String invalidUsername = excelReader.getStringData("Sheet1", i, 2);
            String validPassword = excelReader.getStringData("Sheet1", 1, 1);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        }
    }

    @Test (priority = 40)
    private void userCannotLoginWithInvalidPassword () {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String validUsername = excelReader.getStringData("Sheet1", 1, 0);
            String invalidPassword = excelReader.getStringData("Sheet1", i, 3);
            loginPage.inputUsername(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        }
    }

    @Test (priority = 50)
    private void userCannotLoginWithInvalidPasswordAndUsername () {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String invalidUsername = excelReader.getStringData("Sheet1", i, 2);
            String invalidPassword = excelReader.getStringData("Sheet1", i, 3);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        }
    }

    @Test(priority = 60)
    public void userCannotLogInWithUsernameFieldEmpty() {
        String validPassword = excelReader.getStringData("Sheet1", 1, 1);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(loginPage.errorMessageContains("Username"));
    }

    @Test(priority = 70)
    public void userCannotLogInWithPasswordFieldEmpty() {
        String validUsername = excelReader.getStringData("Sheet1", 1, 0);
        loginPage.inputUsername(validUsername);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(loginPage.errorMessageContains("Password"));
    }

    @Test(priority = 80)
    public void userCannotLogInWithPasswordAndUsernameFieldsEmpty() {
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
    }

    @AfterMethod
    public void ThreadSleep () throws InterruptedException {
        driver.quit();
    }




}



