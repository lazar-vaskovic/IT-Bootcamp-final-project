package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage extends BaseTest {

    public ShoppingCartPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "continue-shopping")
    public WebElement continueShoppingButton;

    @FindBy (id = "checkout")
    public WebElement checkoutButton;

    @FindBy (css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeItemButtons;

    @FindBy (className = "inventory_item_name")
    public WebElement itemName;

    @FindBy (className = "inventory_item_name")
    public List<WebElement> listOfItemsInCart;

    //------------------------------------------------------
    public void removeOneItemFromTheCart (int itemOrder) {
        for (int i = 0; i < removeItemButtons.size(); i++) {
            if (i == itemOrder) {
                scrollToElement(removeItemButtons.get(i));
                removeItemButtons.get(i).click();
                break;
            }
        }
    }

    public void removeAllItemsFromTheCart () throws InterruptedException {
        for (int i = removeItemButtons.size() - 1; i>= 0; i--) {
            if (removeItemButtons.get(i).getText().equals("Remove")){
                Thread.sleep(500);
                removeItemButtons.get(i).click();
            }
        }
    }

    public boolean checkIsItemInTheCart (String itemName){
        for (int i = listOfItemsInCart.size()-1; i >= 0; i--) {
            if (listOfItemsInCart.get(i).getText().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean assertCartIsEmpty () {
        boolean isEmpty = false;
        if (removeItemButtons.isEmpty()) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public void clickOnContinueShoppingButton () {
        continueShoppingButton.click();
    }

    public void clickOnCheckoutButton () {
        checkoutButton.click();
    }
}
