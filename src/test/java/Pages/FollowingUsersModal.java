package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static io.netty.util.internal.SystemPropertyUtil.contains;

public class FollowingUsersModal extends BasePage {

    @FindBy(className = "modal-content")
    WebElement followersModal;

    @FindBy(className = "post-user")
    List<WebElement> followedUsersList;



    public FollowingUsersModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitTheFollowingUserModal(){
        wait.until(ExpectedConditions.visibilityOf(followersModal));
    }
    public boolean isTheUserFollowed(String followerName) {
        for (WebElement followedUser : followedUsersList) {
            if (followedUser.getText().contains(followerName)) {
                return true;
            }
        }System.out.println("The user is not present in the list!");
        return false;
    }

    public boolean isTheUserSuccessfulyUnfollowed(String followerName) {
        for (WebElement followedUser : followedUsersList) {
            if (!followedUser.getText().contains(followerName)){
                return true;
            }
        }
        System.out.println("The user is not present in the list!");
        return false;
    }
    public int getFollowedUsersSize(){
    mediumWait.until(ExpectedConditions.visibilityOf(followedUsersList.get(0)));
    return followedUsersList.size();
}
        public void OpenLastFollowedUserProfile(int index) {
        wait.until(ExpectedConditions.visibilityOf(followedUsersList.get(0)));
            clickOnElement(followedUsersList.get(index));
    }

    public String getUsername(int index){
        wait.until(ExpectedConditions.visibilityOf(followedUsersList.get(0)));
        return followedUsersList.get(index).getText();
    }

    public String getHref(int index){
        wait.until(ExpectedConditions.visibilityOf(followedUsersList.get(0)));
        return followedUsersList.get(index).getAttribute("href");

    }
}



