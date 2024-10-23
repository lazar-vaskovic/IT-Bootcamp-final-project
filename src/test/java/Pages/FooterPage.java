package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage extends BaseTest {

    public FooterPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "social_twitter")
    public WebElement twitterIcon;

    @FindBy (className = "social_facebook")
    public WebElement facebookIcon;

    @FindBy (className = "social_linkedin")
    public WebElement linkedInIcon;

    //---------------------------------------
    public void clickOnX () {
        twitterIcon.click();
    }

    public void clickOnFacebook () {
        facebookIcon.click();
    }

    public void clickOnLinkedIn () {
        linkedInIcon.click();
    }

    public String xURL = "https://x.com/saucelabs";
    public String facebookURL = "https://www.facebook.com/saucelabs";
    public String linkedInURL = "https://www.linkedin.com/company/sauce-labs/";

    /*
    public void clickOnSocialNetwork (String name) {
        for (int i = 0; i < socialNetworks.size(); i++) {
            if (socialNetworks.get(i).getText().contains(name)){
                socialNetworks.get(i).click();
                break;
            }
        }
    }
     */
}
