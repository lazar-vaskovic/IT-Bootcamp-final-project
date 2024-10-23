package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SidebarPage extends BaseTest {

    public SidebarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "bm-burger-button")
    public WebElement burgerMenuButton;

    @FindBy (css = ".bm-item.menu-item")
    public List<WebElement> burgerMenu;

    @FindBy (id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy (id = "react-burger-cross-btn")
    public WebElement exitBurgerMenu;

    //-----------------------------------------

    public void clickOnBurgerMenuButton () {
        burgerMenuButton.click();
    }

    public void clickOnSidebarItem (String item) {
        for (int i = 0; i < burgerMenu.size(); i ++) {
            if (burgerMenu.get(i).getText().equals(item)){
                burgerMenu.get(i).click();
                break;
            }
        }
    }

    public void clickOnLogoutButton () {
        logoutButton.click();
    }

    public void clickOnExitBurgerMenuButton () {
        exitBurgerMenu.click();
    }
}
