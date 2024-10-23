package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    public String inventoryPageURL = "https://www.saucedemo.com/inventory.html";

    @FindBy(className = "app_logo")
    public WebElement appLogo;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartIcon;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(css = ".inventory_item_name")
    public List<WebElement> inventoryItemsList;

    @FindBy(className = "product_sort_container")
    public WebElement sortProductsMenu;

    @FindBy(className = "active_option")
    public WebElement activeSortingOption;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemsPriceList;

    //------------------------------------------------------

    public void clickOnShoppingCart() {
        shoppingCartIcon.click();
    }

    public void addOneItemtoTheCart(int itemOrder) {
        for (int i = 0; i < addToCartButtons.size(); i++) {
            if (i == itemOrder) {
                scrollToElement(addToCartButtons.get(i));
                addToCartButtons.get(i).click();
                break;
            }
        }
    }

    public void addOneSpecificItemToTheCart(String itemName) {
        for (int i = inventoryItemsList.size() - 1; i >= 0; i--) {
            if (inventoryItemsList.get(i).getText().equalsIgnoreCase(itemName)) {
                addToCartButtons.get(i).click();
                break;
            }
        }
    }

    public void addAllItemsToTheCart() throws InterruptedException {
        for (int i = addToCartButtons.size() - 1; i >= 0; i--) {
            if (addToCartButtons.get(i).getText().equals("Add to cart")) {
                Thread.sleep(500);
                addToCartButtons.get(i).click();
            }
        }
    }

    public void clickOnSingleItem(String singleItemName) {
        for (int i = inventoryItemsList.size() - 1; i >= 0; i--) {
            if (inventoryItemsList.get(i).getText().equalsIgnoreCase(singleItemName)) {
                inventoryItemsList.get(i).click();
                break;
            }
        }
    }

    //--------------------------------------------------------
    //Sorting Tests Methods

    public Select getSortOptions() {
        return new Select(sortProductsMenu);
    }

    public void selectSortingOption(String option) {
        Select optionSelect = new Select(sortProductsMenu);
        optionSelect.selectByVisibleText(option);
    }

    public List<String> getTextFromInventoryItemsList () {
        List<String> textItemList = new ArrayList<>();
        for (WebElement element : inventoryItemsList) {
            textItemList.add(element.getText().trim());
        }
        return textItemList;
    }

    public boolean listIsAscendingAtoZ(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

    public boolean listIsDescendingZtoA(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Collections.reverseOrder());
        return list.equals(sortedList);
    }

    public boolean priceIsAscending(List<Double> list) {
        List<Double> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

    public boolean priceIsDescending(List<Double> list) {
        List<Double> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Collections.reverseOrder());
        return list.equals(sortedList);
    }

    public List<Double> getDoublesFromItemsPriceList () {
        List<Double> priceList = new ArrayList<>();
        for (WebElement element:itemsPriceList) {
            String priceText = element.getText().replace("$", "");
            try {
                double price = Double.parseDouble(priceText.trim());
                priceList.add(price);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + priceText);
            }
        }
        return priceList;
    }

}
