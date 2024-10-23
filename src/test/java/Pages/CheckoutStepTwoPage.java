package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage extends BaseTest {

    public CheckoutStepTwoPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "cancel")
    public WebElement cancelButton;

    @FindBy (id = "finish")
    public WebElement finishButton;

    //---------------------------------------

    public void clickOnCancelButton () {
        cancelButton.click();
    }

    public void clickOnFinishButton () {
        finishButton.click();
    }
}
