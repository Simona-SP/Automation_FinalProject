package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public final String LOGIN_URL = "http://training.skillo-bg.com:4200/users/login";
    @FindBy(css = "form.form-container")
    private WebElement signInForm;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;

    @FindBy(css = "input.remember-me-button")
    private WebElement rememberMeBtn;

    @FindBy(id = "sign-in-button")
    private WebElement signInBtn;

    @FindBy(xpath = "//a[contains(text(), 'Register')]")
    private WebElement registerLink;

    @FindBy(className = "toast-message")
    WebElement actualToastMessage;

    @FindBy(css = "div[aria-label = 'Successful logout!']")
    private WebElement successfulLogoutMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }

    public void populateUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
    }

    public void populatePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void clickTheSignInBtn() {
        clickElement(signInBtn);
    }

    public void signIn(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        clickElement(signInBtn);

    }

    public void verifyLoginURL() {
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
    }

    public void signInFormIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(signInForm));
    }

    public void clickTheRememberMeBtn() {
        clickElement(rememberMeBtn);

    }
    public void clickTheRegisterLink(){
        clickElement(registerLink);
    }

    public void getErrorToastMessage(String expectedErrorMessage) {
        wait.until(ExpectedConditions.visibilityOf(actualToastMessage));
        String actualMessage = actualToastMessage.getText();
        Assert.assertEquals(actualMessage, expectedErrorMessage, "Toast message not as expected, actual is " + actualMessage);
    }

    public void successfulLogin() {
        wait.until(ExpectedConditions.visibilityOf(actualToastMessage));
        String actualLoginMessage = actualToastMessage.getText();
        Assert.assertEquals(actualLoginMessage, "Successful login!", "Toast message not as expected, actual is " + actualLoginMessage);

    }
    public void confirmSuccessfulLogout() {
        verifyLoginURL();
        wait.until(ExpectedConditions.visibilityOf(successfulLogoutMessage));
        String actualLogoutMsg = successfulLogoutMessage.getText();
        Assert.assertEquals(actualLogoutMsg, "Successful logout!", "Toast message not as expected, actual is " + actualLogoutMsg);
    }
}





