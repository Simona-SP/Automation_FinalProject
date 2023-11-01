package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoutTest extends BaseTest {

    @DataProvider(name = "successfulLogin")
    public Object[][] getValues() {
        return new Object[][]{
                {"SimonaSLS", "Monika987321*"},
                {"auto_user", "auto_pass"}
        };
    }

    @Test(dataProvider = "successfulLogin")
    public void logoutFromProfilePage(String username, String password){
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

        System.out.println("4.Sign in Successfully");
        loginPage.signIn(username,password);

        System.out.println("5. Verify that the URL is correct ( homepage )");
        BasePage basePage = new BasePage(driver);
        basePage.verifyURL(homePage.HOME_URL);

        System.out.println("6. Verify that the Successful login Toast message appeared");
        loginPage.successfulLogin();

        System.out.println("7.Click the Profile button");
        headerPage.goToProfilePage();

        System.out.println(".8.Verify that you are at the ProfilePage Username title matches the text 'auto_user");
        ProfilePage profilePage = new ProfilePage(driver);
        String actualUsernameTitle = profilePage.getUsernameTitle();
        Assert.assertEquals(actualUsernameTitle, username, "Username title is incorrect!");

        System.out.println("10.Click the Logout button");
        headerPage.isSignOutVisible();
        headerPage.signOut();

        System.out.println("11 Verify that you have been redirected to the Sign in page URL again and toast message 'Successful logout has appeared'");
       loginPage.confirmSuccessfulLogout();

        System.out.println("12.Verify that Sign in form is visible and sign out button is not displayed");
        loginPage.signInFormIsVisible();
        headerPage.isSignOutNotVisible();
    }

}
