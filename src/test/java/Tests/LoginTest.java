package Tests;

import Pages.BasePage;
import Pages.HeaderPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest{
    final private String Base_URL = "http://training.skillo-bg.com:4200";

    @DataProvider(name = "Unsuccessful login")
    public Object[][] getValues() {
        return new Object[][]{
                {"SimonaSLS", "Incorrect password", "Ivalid password"},
                {"", "Monika987321*", "UsernameOrEmail cannot be empty"},
                {"WrongUsername", "Monika987321*", "User not found"},
                {"SimonaSLS", "", "Password cannot be empty"}

        };
    }

    @Test(dataProvider = "Unsuccessful login", groups = "Negative scenarios")
    public void loginWithIncorrectCredentials(String username, String password, String expectedErrorMessage) {
        System.out.println("1.Go to base page URL");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();

        System.out.println("2.Click login button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLoginPage();

        System.out.println("3.Verify that the URL is correct - sign in");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginURL();

        System.out.println("4.Verify that the Sign in form is visible");
        loginPage.signInFormIsVisible();

        System.out.println("5.Populate username");
        loginPage.populateUsername(username);

        System.out.println("6.Populate password");
        loginPage.populatePassword(password);

        System.out.println("7. Click the Sign in Button");
        loginPage.clickTheSignInBtn();

        System.out.println("8. Verify that correct error message appeared ");
        loginPage.getErrorToastMessage(expectedErrorMessage);

        System.out.println("9.Verify that the URL still the same - login page ");
        loginPage.verifyLoginURL();
    }


}
