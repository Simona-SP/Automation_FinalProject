package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FollowedUserProfilePage extends BasePage{

    @FindBy(css =".profile-user-settings h2")
    WebElement followedUsername;

    @FindBy (css = "button.profile-edit-btn")
    WebElement followButton;

    @FindBy(id = "followers")
    WebElement followersNumber;



    public FollowedUserProfilePage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public String getFollowedUsernameTitle() {

        mediumWait.until(ExpectedConditions.visibilityOf(followedUsername));
        return followedUsername.getText();
    }

    public int getAllFollowersNumber(){
            wait.until(ExpectedConditions.visibilityOf(followersNumber));
            return Integer.parseInt(followersNumber.getText());
        }

        public void ClickFollow(){
        clickOnElement(followButton);
        }
        public String followBtnCheckStatus(){
        wait.until(ExpectedConditions.visibilityOf(followedUsername));
        wait.until(ExpectedConditions.visibilityOf(followButton));
       return followButton.getText();
}
 public void openFollowersModal(){
        actions.sendKeys(Keys.HOME).perform();
        wait.until(ExpectedConditions.visibilityOf(followersNumber));
        clickOnElement(followersNumber);
 }

 public void followOrUnfollow(){
        clickOnElement(followButton);

 }

 public String getCurrentURL(){
        wait.until(ExpectedConditions.visibilityOf(followButton));
        return driver.getCurrentUrl();
 }
}
