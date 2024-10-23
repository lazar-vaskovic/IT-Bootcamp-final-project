package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public FooterPage footerPage;
    public SidebarPage sidebarPage;
    public ShoppingCartPage shoppingCartPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckoutStepTwoPage checkoutStepTwoPage;
    public CheckoutCompletePage checkoutCompletePage;
    public SingleItemsPage singleItemsPage;

    @BeforeClass
    public void setUp () throws IOException {
        WebDriverManager.chromedriver().setup();
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        //excelReader = new ExcelReader("C:\\Users\\Vaskovic\\IdeaProjects\\SauceDemo-AutomaticPart\\src\\main\\resources\\SauceDemoData.xlsx");
        excelReader = new ExcelReader(dir + "\\src\\main\\resources\\SauceDemoData.xlsx");
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void userIsLoggedIn () {
        String validUsername = excelReader.getStringData("Sheet1", 1, 0);
        String validPassword = excelReader.getStringData("Sheet1", 1, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(inventoryPage.shoppingCartIcon.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.inventoryPageURL);
        Assert.assertTrue(inventoryPage.appLogo.isDisplayed());
    }

    public void userAddedProductToCartAndProceededToCheckout () {
        inventoryPage.addOneSpecificItemToTheCart(excelReader.getStringData("Sheet1", 0,9));
        inventoryPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
    }

    @AfterClass
    public void tearDown () {
        driver.quit();
    }
}
