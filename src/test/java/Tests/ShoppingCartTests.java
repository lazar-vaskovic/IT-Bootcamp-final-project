package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingCartTests extends BaseTest {

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
    }

    @Test (priority = 10)
    public void userCanAddItemToCart() throws InterruptedException {
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 5,9));
        inventoryPage.clickOnShoppingCart();
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "1");
        Assert.assertTrue(shoppingCartPage.removeItemButtons.getFirst().isDisplayed());
        Assert.assertEquals(shoppingCartPage.itemName.getText(), excelReader.getStringData("Sheet1", 5,9));
    }

    @Test (priority = 20)
    public void userCanAddMultipleItemsToCart() throws InterruptedException {
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 5,9));
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 3,9));
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 1,9));
        inventoryPage.clickOnShoppingCart();
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "3");
        Assert.assertTrue(shoppingCartPage.removeItemButtons.getFirst().isDisplayed());
        Assert.assertTrue(shoppingCartPage.checkIsItemInTheCart(excelReader.getStringData("Sheet1", 5,9)));
        Assert.assertTrue(shoppingCartPage.checkIsItemInTheCart(excelReader.getStringData("Sheet1", 3,9)));
        Assert.assertTrue(shoppingCartPage.checkIsItemInTheCart(excelReader.getStringData("Sheet1", 1,9)));
    }

    @Test (priority = 30)
    public void userCanAddAllItemsToCart() throws InterruptedException {
        inventoryPage.addAllItemsToTheCart();
        inventoryPage.clickOnShoppingCart();
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "6");
        Assert.assertTrue(shoppingCartPage.removeItemButtons.getFirst().isDisplayed());
        for (int i = 0; i <= excelReader.getLastRow("Sheet1"); i++) {
            String itemName = excelReader.getStringData("Sheet1", i, 9);
            Assert.assertTrue(shoppingCartPage.checkIsItemInTheCart(itemName));
        }
    }

    @Test (priority = 40)
    public void userCanRemoveItemFromTheCart() throws InterruptedException {
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 0,9));
        inventoryPage.clickOnShoppingCart();
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "1");
        Assert.assertEquals(shoppingCartPage.itemName.getText(), excelReader.getStringData("Sheet1", 0,9));
        shoppingCartPage.removeOneItemFromTheCart(0);
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "");
        Assert.assertTrue(shoppingCartPage.assertCartIsEmpty());
    }

    @Test (priority = 50)
    public void userCanRemoveAllItemsFromTheCart() throws InterruptedException {
        inventoryPage.addAllItemsToTheCart();
        inventoryPage.clickOnShoppingCart();
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "6");
        Assert.assertTrue(shoppingCartPage.removeItemButtons.get(0).isDisplayed());
        shoppingCartPage.removeAllItemsFromTheCart();
        Assert.assertEquals(inventoryPage.shoppingCartIcon.getText(), "");
        Assert.assertTrue(shoppingCartPage.assertCartIsEmpty());
    }


    @AfterMethod
    public void ThreadSleep () throws InterruptedException {
        driver.quit();
    }
}
