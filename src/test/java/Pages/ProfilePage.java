package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProfilePage extends BasePage {
    public final String PROFILE_URL = "http://training.skillo-bg.com:4200/users/4964";

    @FindBy(css = ".profile-user-settings h2")
    WebElement usernameTitle;

    @FindBy(className = "fa-plus-square")
    WebElement newPost;
    @FindBy(xpath = "//li[contains(text(), 'posts')]/strong")
    WebElement allPostsNumber;

    @FindBy(css = "app-post")
    List<WebElement> existingPostsNumber;

    @FindBy(id = "following")
    WebElement followingUsers;

    @FindBy(className = "user-list-container")
    List<WebElement> followingList;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifyURL(){
        wait.until(ExpectedConditions.urlContains(PROFILE_URL));
    }
    public String getUsernameTitle() {
        wait.until(ExpectedConditions.visibilityOf(usernameTitle));
        return usernameTitle.getText();
    }

    public int getAllPostsNumber(){
        wait.until(ExpectedConditions.visibilityOf(allPostsNumber));
        return Integer.parseInt(allPostsNumber.getText());
    }
    public int getExistingPostsNumber(){
        wait.until(ExpectedConditions.visibilityOf(existingPostsNumber.get(0)));
        return existingPostsNumber.size();
    }

    public void scrollAndOpenPost(int index){
        wait.until(ExpectedConditions.visibilityOf(existingPostsNumber.get(0)));
//        actions.sendKeys(Keys.END).perform();
//        actions.moveToElement(existingPostsNumber.get(index)).perform();
        clickOnElement(existingPostsNumber.get(index));
    }
    public int getAllFollowedUsersNumber(){
        wait.until(ExpectedConditions.visibilityOf(followingUsers));
        String input = followingUsers.getText();
        int number = 0;
        String[] parts = input.split(" ");
        if (parts.length >= 2) {
            try {
                number = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
            }
        }
        return number;
    }

    public void openFollowingModal(){
        clickOnElement(followingUsers);
    }


}



