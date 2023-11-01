package Pages;

import Tests.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HeaderPage extends BasePage {

    @FindBy(id = "nav-link-home")
    WebElement homeButton;

    @FindBy(id = "nav-link-login")
    WebElement loginButton;

    @FindBy(id = "nav-link-profile")
    WebElement profileButton;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostButton;

    @FindBy(css = ".fa-sign-out-alt")
    WebElement signOutButton;



    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void goToHomePage() {
        clickOnElement(homeButton);
    }

    public void goToLoginPage() {clickOnElement(loginButton);
    }
    public void goToProfilePage() {
        clickOnElement(profileButton);
    }

    public void goToNewPostPage() {
        clickOnElement(newPostButton);
    }


    public void signOut() {
        clickOnElement(signOutButton);
    }


    public void isSignOutVisible() {
        Assert.assertTrue(signOutButton.isDisplayed(),"The Sign out Button should not be visible, but it's not");
    }

    public void isSignOutNotVisible() {
        try {
            Assert.assertFalse(signOutButton.isDisplayed(), "The Sign out Button should not be visible, but it is");
        } catch (NoSuchElementException e) {
            System.out.println("The Sign Out button is not visible as expected.");
        }
    }

}