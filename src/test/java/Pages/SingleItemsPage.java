package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SingleItemsPage extends BaseTest {

    public SingleItemsPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".inventory_details_name.large_size")
    public WebElement itemName;

    @FindBy (id = "back-to-products")
    public WebElement backToProductsButton;

    //------------------------------------------

    public void clickOnBackToProductsButton () {
        backToProductsButton.click();
    }

}
