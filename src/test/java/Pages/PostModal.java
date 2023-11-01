package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends BasePage{

    @FindBy(css = ".post-modal-container a.post-user")
    WebElement user;

    @FindBy(className = "modal-content")
    WebElement modal;

    @FindBy(className = "modal-dialog")
    WebElement modalDialogSpace;
    @FindBy(css = ".post-modal-container button")
    WebElement followButton;

    @FindBy(css = ".post-modal-container a")
    WebElement followedUser;

    @FindBy(className = "custom-control")
    WebElement switchPrivacyPostBtn;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


        public void waitUntilModalAppears(){
            mediumWait.until(ExpectedConditions.visibilityOf(modal));

        }
        public String getUsername(){
        wait.until(ExpectedConditions.visibilityOf(user));
        return user.getText();
        }

        public void clickFollowButton(){
        clickOnElement(followButton);
        }

        public void openUserProfilePage(){
        clickOnElement(followedUser);
        }

    public void closeModal(){
        actions.sendKeys(Keys.ESCAPE).build().perform();
        wait.until(ExpectedConditions.invisibilityOf(modal));
    }

        }



