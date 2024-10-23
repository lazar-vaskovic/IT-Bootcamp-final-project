package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class InventoryPageTests extends BaseTest {

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

    @Test(priority = 10)
    public void userCanOpenSingleItemsPage1() throws InterruptedException {
        inventoryPage.clickOnSingleItem(excelReader.getStringData("Sheet1", 0,9));
        Assert.assertTrue(singleItemsPage.backToProductsButton.isDisplayed());
        Assert.assertEquals(singleItemsPage.itemName.getText(), excelReader.getStringData("Sheet1", 0,9));
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Thread.sleep(500);
        singleItemsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.sortProductsMenu.isDisplayed());
    }

    @Test(priority = 20)
    public void userCanOpenSingleItemsPage2() throws InterruptedException {
        inventoryPage.clickOnSingleItem(excelReader.getStringData("Sheet1", 1,9));
        Assert.assertTrue(singleItemsPage.backToProductsButton.isDisplayed());
        Assert.assertEquals(singleItemsPage.itemName.getText(), excelReader.getStringData("Sheet1", 1,9));
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Thread.sleep(500);
        singleItemsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.sortProductsMenu.isDisplayed());
    }

    @Test(priority = 30)
    public void userCanOpenSingleItemsPage3() throws InterruptedException {
        inventoryPage.clickOnSingleItem(excelReader.getStringData("Sheet1", 2,9));
        Assert.assertTrue(singleItemsPage.backToProductsButton.isDisplayed());
        Assert.assertEquals(singleItemsPage.itemName.getText(), excelReader.getStringData("Sheet1", 2,9));
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Thread.sleep(500);
        singleItemsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.sortProductsMenu.isDisplayed());
    }

    @Test(priority = 40)
    public void userCanOpenSingleItemsPage4() throws InterruptedException {
        inventoryPage.clickOnSingleItem(excelReader.getStringData("Sheet1", 3,9));
        Assert.assertTrue(singleItemsPage.backToProductsButton.isDisplayed());
        Assert.assertEquals(singleItemsPage.itemName.getText(), excelReader.getStringData("Sheet1", 3,9));
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Thread.sleep(500);
        singleItemsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.sortProductsMenu.isDisplayed());
    }

    @Test(priority = 50)
    public void userCanOpenSingleItemsPage5() throws InterruptedException {
        inventoryPage.clickOnSingleItem(excelReader.getStringData("Sheet1", 4,9));
        Assert.assertTrue(singleItemsPage.backToProductsButton.isDisplayed());
        Assert.assertEquals(singleItemsPage.itemName.getText(), excelReader.getStringData("Sheet1", 4,9));
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Thread.sleep(500);
        singleItemsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.sortProductsMenu.isDisplayed());
    }

    @Test(priority = 60)
    public void userCanOpenSingleItemsPage6() throws InterruptedException {
        inventoryPage.clickOnSingleItem(excelReader.getStringData("Sheet1", 5,9));
        Assert.assertTrue(singleItemsPage.backToProductsButton.isDisplayed());
        Assert.assertEquals(singleItemsPage.itemName.getText(), excelReader.getStringData("Sheet1", 5,9));
        Assert.assertNotEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Thread.sleep(500);
        singleItemsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.sortProductsMenu.isDisplayed());
    }

    @Test(priority = 61)
    public void validateSortOptions() throws InterruptedException {
        Assert.assertEquals(inventoryPage.getSortOptions().getOptions().size(), 4);

        List<String> sortList = new ArrayList<>();
        sortList.add(excelReader.getStringData("Sheet1", 1,10));
        sortList.add(excelReader.getStringData("Sheet1", 2,10));
        sortList.add(excelReader.getStringData("Sheet1", 3,10));
        sortList.add(excelReader.getStringData("Sheet1", 4,10));

        for (WebElement option : inventoryPage.getSortOptions().getOptions()) {
            Assert.assertTrue(sortList.contains(option.getText()), "Option " + option.getText() + " is not expected.");
            sortList.remove(option.getText());
        }
        Assert.assertTrue(sortList.isEmpty(), "Not all expected sort options were found.");
    }

    @Test (priority = 70)
    public void userCanSortItemsByPrice () throws InterruptedException {
        inventoryPage.selectSortingOption(excelReader.getStringData("Sheet1", 1,10));
        Assert.assertEquals(inventoryPage.activeSortingOption.getText(), excelReader.getStringData("Sheet1", 1,10));
        Assert.assertTrue(inventoryPage.priceIsAscending(inventoryPage.getDoublesFromItemsPriceList()));
        inventoryPage.selectSortingOption(excelReader.getStringData("Sheet1", 2,10));
        Assert.assertEquals(inventoryPage.activeSortingOption.getText(), excelReader.getStringData("Sheet1", 2,10));
        Assert.assertTrue(inventoryPage.priceIsDescending(inventoryPage.getDoublesFromItemsPriceList()));
    }

    @Test (priority = 80)
    public void userCanSortItemsByName () throws InterruptedException {
        inventoryPage.selectSortingOption(excelReader.getStringData("Sheet1", 3,10));
        Assert.assertEquals(inventoryPage.activeSortingOption.getText(), excelReader.getStringData("Sheet1", 3,10));
        Assert.assertTrue(inventoryPage.listIsDescendingZtoA(inventoryPage.getTextFromInventoryItemsList()));
        inventoryPage.selectSortingOption(excelReader.getStringData("Sheet1", 4,10));
        Assert.assertEquals(inventoryPage.activeSortingOption.getText(), excelReader.getStringData("Sheet1", 4,10));
        Assert.assertTrue(inventoryPage.listIsAscendingAtoZ(inventoryPage.getTextFromInventoryItemsList()));
    }

    @AfterMethod
    public void ThreadSleep () throws InterruptedException {
        driver.quit();
    }

}
