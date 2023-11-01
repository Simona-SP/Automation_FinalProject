package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UnsuccessfulRegistration extends BaseTest {

    @DataProvider(name = "Invalid username")
    public Object[][] getInvalidUsernameValues() {
        return new Object[][]{
                {"N", "validemail1@abv.bg", "ValidPassword1", " Minimum 2 characters ! ", "Registration failed!"},
        };
    }

    @DataProvider(name = "Invalid emailAddress")
    public Object[][] getInvalidEmailValues() {
        return new Object[][]{
                {"ValidUsername12", "Inv", "ValidPassword1", "Email invalid!", "Registration failed!"}

        };
    }

    @DataProvider(name = "Invalid password")
    public Object[][] getInvalidPasswordValues() {
        return new Object[][]{
                {"ValidUsername1211", "validemai1l@abv.bg", "Inv", " Minimum 6 characters !", "Registration failed!"}

        };
    }

    @DataProvider(name = "Non matching password")
    public Object[][] getNotMatchingPasswordValues() {
        return new Object[][]{
                {"ValidUsername1211", "validEmail1@abv.bg", "Valid Password","Passwords do not match!", "Passwords do not match!", "Registration failed!"},

        };
    }

    @Test(dataProvider = "Invalid username", groups = "Negative scenarios")
    public void incorrectUsernameUnsuccessfulRegistration(String username, String emailAddress, String password, String invalidFeedback, String expectedErrorMessage) {

        System.out.println("1.Go to base page URL");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();

        System.out.println("2.Verify that Sign in form is visible and click on Register link");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signInFormIsVisible();
        loginPage.clickTheRegisterLink();

        System.out.println("3.Verify that Sign up form is visible");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.verifyURL(registerPage.Register_URL);

        System.out.println("4. Populate invalid username and verify that invalid sign is displayed in usernameField");
        registerPage.populateUserName(username);
        String usernameInputSign = registerPage.getUsernameFieldInputSign();
        Assert.assertTrue(usernameInputSign.contains("is-invalid"), "The input is valid!");

        System.out.println("5. Verify that negative feedback for the input Username is received.");
        String usernameInvalidFeedback = registerPage.getUsernameInvalidFeedback();
        Assert.assertEquals(usernameInvalidFeedback.trim(), invalidFeedback.trim(), "The input should not be valid, but it is!");

        System.out.println("6. Populate a valid email and verify a valid sign is displayed");
        registerPage.populateEmailAddress(emailAddress);
        String emailInputSign = registerPage.getEmailAddressFieldInputSign();
        Assert.assertTrue(emailInputSign.contains("is-valid"), "The input is Invalid!");

        System.out.println("7. Populate password and verify a valid sign is displayed");
        registerPage.populatePassword(password);
        String passwordInputSign = registerPage.getPasswordFieldInputdSign();

        Assert.assertTrue(passwordInputSign.contains("is-valid"), "The input is Invalid!");

        System.out.println("8. Populate confirm password and verify a valid sign is displayed");
        registerPage.confirmPassword(password);
        String confirmPasswordInputSign = registerPage.getConfirmPasswordFieldInputSign();
        Assert.assertTrue(confirmPasswordInputSign.contains("is-valid"), "The input is Invalid!");

        System.out.println("9. Click Sign-in");
        registerPage.Register();

        System.out.println("10. Check that the toast message is correct");
        loginPage.getErrorToastMessage(expectedErrorMessage);

        System.out.println("11.Verify that the URL is still Register URL");
        registerPage.verifyURL(registerPage.Register_URL);
    }

    @Test(dataProvider = "Invalid emailAddress", groups = "Negative scenarios")
    public void incorrectEmailInvalidRegistration(String username, String emailAddress, String password, String invalidFeedback, String expectedErrorMessage) {

        System.out.println("1.Go to base page URL");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();

        System.out.println("2.Verify that Sign in form is visible and click on Register link");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signInFormIsVisible();
        loginPage.clickTheRegisterLink();

        System.out.println("3.Verify that Sign up form is visible");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.verifyURL(registerPage.Register_URL);

        System.out.println("4. Populate valid username and verify that invalid sign is displayed in usernameField");
        registerPage.populateUserName(username);
        String usernameInputSign = registerPage.getUsernameFieldInputSign();
        Assert.assertTrue(usernameInputSign.contains("is-valid"), "The input is invalid!");

        System.out.println("5. Populate an invalid email and verify a valid sign is displayed");
        registerPage.populateEmailAddress(emailAddress);
        String emailInputSign = registerPage.getEmailAddressFieldInputSign();
        Assert.assertTrue(emailInputSign.contains("is-invalid"), "The input is valid!");

        System.out.println("6. Verify that negative feedback for the input in Email field is received.");
        String emailInvalidFeedback = registerPage.getEmailInvalidFeedback();
        Assert.assertEquals(emailInvalidFeedback.trim(), invalidFeedback.trim(), "The input should not be valid, but it is!");

        System.out.println("7. Populate password and verify a valid sign is displayed");
        registerPage.populatePassword(password);
        String passwordInputSign = registerPage.getPasswordFieldInputdSign();
        Assert.assertTrue(passwordInputSign.contains("is-valid"), "The input is Invalid!");

        System.out.println("8. Populate confirm password and verify a valid sign is displayed");
        registerPage.confirmPassword(password);
        String confirmPasswordInputSign = registerPage.getConfirmPasswordFieldInputSign();
        Assert.assertTrue(confirmPasswordInputSign.contains("is-valid"), "The input is Invalid!");

        System.out.println("9. Click Sign-in");
        registerPage.Register();

        System.out.println("10. Check that the toast message is correct");
        loginPage.getErrorToastMessage(expectedErrorMessage);

        System.out.println("11.Verify that the URL is still Register URL");
        registerPage.verifyURL(registerPage.Register_URL);
    }

    @Test(dataProvider = "Invalid password", groups = "Negative scenarios")
    public void IncorrectPasswordInvalidRegistration(String username, String emailAddress, String password, String invalidFeedback, String expectedErrorMessage) {

        System.out.println("1.Go to base page URL");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();

        System.out.println("2.Verify that Sign in form is visible and click on Register link");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signInFormIsVisible();
        loginPage.clickTheRegisterLink();

        System.out.println("3.Verify that Sign up form is visible");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.verifyURL(registerPage.Register_URL);

        System.out.println("4. Populate valid username and verify that invalid sign is displayed in usernameField");
        registerPage.populateUserName(username);
        String usernameInputSign = registerPage.getUsernameFieldInputSign();
        Assert.assertTrue(usernameInputSign.contains("is-valid"), "The input is invalid!");

        System.out.println("5. Populate a valid email and verify a valid sign is displayed");
        registerPage.populateEmailAddress(emailAddress);
        String emailInputSign = registerPage.getEmailAddressFieldInputSign();
        Assert.assertTrue(emailInputSign.contains("is-valid"), "The input is invalid!");


        System.out.println("6. Populate invalid password and verify a valid sign is displayed");
        registerPage.populatePassword(password);
       String passwordInputSign = registerPage.getPasswordFieldInputdSign();
       Assert.assertTrue(passwordInputSign.contains("is-invalid"), "The input is valid!");

       System.out.println("8 Verify that negative feedback for the input in password field is received.");
       String passwordInvalidFeedback = registerPage.getPasswordInvalidFeedback();
       Assert.assertEquals(passwordInvalidFeedback.trim(), invalidFeedback.trim(), "The input should not be valid, but the message feedback is!: "+invalidFeedback);

        System.out.println("8. Populate confirm password and verify a valid sign is displayed");
        registerPage.confirmPassword(password);
        String confirmPasswordInputSign = registerPage.getConfirmPasswordFieldInputSign();
        Assert.assertTrue(confirmPasswordInputSign.contains("is-valid"), "The password does not match!");

        System.out.println("9. Click Sign-in");
        registerPage.Register();

        System.out.println("10. Check that the toast message is correct");
        loginPage.getErrorToastMessage(expectedErrorMessage);

        System.out.println("11.Verify that the URL is still Register URL");
        registerPage.verifyURL(registerPage.Register_URL);

    }

//    @Test(dataProvider = "Non matching password", groups = "Negative scenarios")
//    public void nonMatchingPassInvalidRegistration(String username, String emailAddress, String password,String confirmPassword, String invalidFeedback, String expectedErrorMessage) {
//
//        System.out.println("1.Go to base page URL");
//        HomePage homePage = new HomePage(driver);
//        homePage.navigateToHome();
//
//        System.out.println("2.Verify that Sign in form is visible and click on Register link");
//        HeaderPage headerPage = new HeaderPage(driver);
//        headerPage.goToLoginPage();
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.signInFormIsVisible();
//        loginPage.clickTheRegisterLink();
//
//        System.out.println("3.Verify that Sign up form is visible");
//        RegisterPage registerPage = new RegisterPage(driver);
//        registerPage.verifyURL(registerPage.Register_URL);
//
//        System.out.println("4. Populate valid username and verify that invalid sign is displayed in usernameField");
//        registerPage.populateUserName(username);
//        String usernameInputSign = registerPage.getUsernameFieldInputSign();
//        Assert.assertTrue(usernameInputSign.contains("is-valid"), "The input is invalid!");
//
//        System.out.println("5. Populate a valid email and verify a valid sign is displayed");
//        registerPage.populateEmailAddress(emailAddress);
//        String emailInputSign = registerPage.getEmailAddressFieldInputSign();
//        Assert.assertTrue(emailInputSign.contains("is-valid"), "The input is invalid!");
//
//        System.out.println("6. Populate valid password and verify a valid sign is displayed");
//        registerPage.populatePassword(password);
//        String passwordInputSign = registerPage.getPasswordFieldInputdSign();
//        Assert.assertTrue(passwordInputSign.contains("is-valid"), "The input is invalid!");
//
//        System.out.println("8. Populate confirm password and verify a valid sign is displayed");
//        registerPage.confirmPassword(confirmPassword);
//        String confirmPasswordInputSign = registerPage.getConfirmPasswordFieldInputSign();
//        Assert.assertTrue(confirmPasswordInputSign.contains("is-invalid"), "The input is valid!");
//
//        System.out.println("9 Verify that negative feedback for the input in password field is received.");
//        String confirmPasswordInvalidFeedback = registerPage.getConfirmPasswordInvalidFeedback();
//        Assert.assertEquals(confirmPasswordInvalidFeedback.trim(), confirmPassword.trim(), "The input should not be valid, but the message feedback is!: "+invalidFeedback);
//
//        System.out.println("10. Click Sign-in");
//        registerPage.Register();
//
//        System.out.println("11. Check that the toast message is correct");
//        loginPage.getErrorToastMessage(expectedErrorMessage);
//
//        System.out.println("12.Verify that the URL is still Register URL");
//        registerPage.verifyURL(registerPage.Register_URL);
//
//    }
}
