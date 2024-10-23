package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class SocialMediaLinksTests extends BaseTest {


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
    public void userCanOpenXAccount () throws InterruptedException {
        scrollToElement(footerPage.twitterIcon);
        footerPage.clickOnX();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), footerPage.xURL);
    }

    @Test (priority = 20)
    public void userCanOpenFacebookAccount () throws InterruptedException {
        scrollToElement(footerPage.facebookIcon);
        footerPage.clickOnFacebook();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), footerPage.facebookURL);
    }

    @Test (priority = 30)
    public void userCanOpenLinkedInAccount () throws InterruptedException {
        scrollToElement(footerPage.linkedInIcon);
        footerPage.clickOnLinkedIn();
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), footerPage.linkedInURL);
    }

    @AfterMethod
    public void ThreadSleep () throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }


}
