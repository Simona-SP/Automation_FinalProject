package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {
    public final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    @FindBy(css = "div[aria-label = 'Successful login!']")
    WebElement successfulLoginMessage;

    @FindBy(css = "img[src= 'https://i.imgur.com/OR9wGzj.jpg']")
    WebElement uploadedPost;

    @FindBy(css = "div.post-list-container>div")
    List<WebElement> homePagePosts;


    @FindBy(xpath = "//a[@href='users/5023']")
    WebElement selectedUserLink;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToHome() {
        driver.get(HOME_URL);
    }

    public int getAllHomePagePostsSize(){
        mediumWait.until(ExpectedConditions.visibilityOf(homePagePosts.get(0)));
        actions.sendKeys(Keys.END).perform();
        return homePagePosts.size();
    }
    public void openPost(int index){
       mediumWait.until(ExpectedConditions.visibilityOf(homePagePosts.get(index)));
        actions.sendKeys(Keys.DOWN).perform();
        actions.moveToElement(homePagePosts.get(index)).perform();
        clickOnElement(homePagePosts.get(index));

    }


}