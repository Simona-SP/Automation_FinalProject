package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage{

    public final String Register_URL = "http://training.skillo-bg.com:4200/users/register";

    @FindBy(xpath = "*//h4[contains(text(), 'Sign up')]")
    WebElement signUpHeader;

    @FindBy(name="username")
    WebElement usernameField;

    @FindBy(css = "input[type='email']")
    WebElement emailAddressField;

    @FindBy(id ="defaultRegisterFormPassword")
    WebElement passwordField;

    @FindBy(id ="defaultRegisterPhonePassword")
    WebElement passConfirmationField;


    @FindBy(id= "sign-in-button")
    WebElement signInButton;

    @FindBy(className = "toast-message")
    WebElement toastMessage;

    @FindBy(className = "invalid-feedback")
    WebElement invalidFeedbackMessage;

    @FindBy(xpath = "//input[@name='username']/following-sibling::span")
    WebElement usernameInvalidFeedback;

    @FindBy(xpath = "//input[@type='email']/following-sibling::span")
    WebElement emailInvalidFeedback;

    @FindBy(xpath = "//input[@type='password']/following-sibling::span")
    WebElement passwordInvalidFeedback;

    @FindBy(xpath = "//input[@name='verify-password']/following-sibling::span")
    WebElement confimrPasswordInvalidFeedback;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifySignUpHeaderVisible() {

        wait.until(ExpectedConditions.visibilityOf(signUpHeader));
    }
        public void populateUserName(String username) {

        populateRegisterData(usernameField,username);
        }

        public String getUsernameFieldInputSign(){
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        String classAttribute = usernameField.getAttribute("class");
        return classAttribute;

    }
    public String getUsernameInvalidFeedback() {
        wait.until(ExpectedConditions.visibilityOf(usernameInvalidFeedback));
        return usernameInvalidFeedback.getText();
    }

    public void populateEmailAddress( String emailAddress){
        populateRegisterData(emailAddressField,emailAddress);
//        Assert.assertTrue(classAttribute.contains("is-invalid"), "The input is valid!");
    }
    public String getEmailAddressFieldInputSign() {
        wait.until(ExpectedConditions.visibilityOf(emailAddressField));
        String classAttribute = emailAddressField.getAttribute("class");
        return classAttribute;
    }

    public String getEmailInvalidFeedback() {
        wait.until(ExpectedConditions.visibilityOf(emailInvalidFeedback));
        return emailInvalidFeedback.getText();
    }
    public void populatePassword(String password) {
       populateRegisterData(passwordField,password);

    }
    public String getPasswordFieldInputdSign() {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        String classAttribute = passwordField.getAttribute("class");
        return classAttribute;
    }

    public String getPasswordInvalidFeedback() {
        wait.until(ExpectedConditions.visibilityOf(passwordInvalidFeedback));
        return passwordInvalidFeedback.getText();
    }

    public void confirmPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passConfirmationField));
        populateRegisterData(passConfirmationField,password);
    }
    public String getConfirmPasswordFieldInputSign() {
        String classAttribute = passConfirmationField.getAttribute("class");
        return classAttribute;
    }
    public String getConfirmPasswordInvalidFeedback() {
        wait.until(ExpectedConditions.visibilityOf(confimrPasswordInvalidFeedback));
        return confimrPasswordInvalidFeedback.getText();
    }
    public void Register(){
        clickOnElement(signInButton);
    }

}

