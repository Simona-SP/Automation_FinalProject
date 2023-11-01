package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FollowersModal extends BasePage {

    @FindBy(className = "user-list-container")
    List<WebElement> followersList;


    public FollowersModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isUserListedInFollowers(String username) {
        for (WebElement following : followersList) {
            if (following.getText().contains(username)) {
                return true;
            }
        }
        return false;
    }
}