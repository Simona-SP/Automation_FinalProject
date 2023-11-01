package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class UploadPostTest extends BaseTest {

    public final String PROFILE_URL = "http://training.skillo-bg.com:4200/users/";


    @DataProvider(name = "successfulPostUplaod")
    public Object[][] getValues() {
        return new Object[][]{
                {"SimonaSLS", "Monika987321*","C:\\Users\\simon\\IdeaProjects\\Automation_FinalProject\\src\\test\\java\\Resources\\Nature.jpg","Calmness"}

        };
    }

    @Test(dataProvider = "successfulPostUplaod")
    public void uploadPost(String username, String password,String pathname,String captionText) {
        System.out.println("1.Go to base page URL");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();

        System.out.println("2.Click login button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLoginPage();

        System.out.println("3.Login successfully");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(username, password);
        loginPage.successfulLogin();

        System.out.println("4.Go to Profile Page and obtain the number of all posts and all existing posts.");
        headerPage.goToProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        int existingPostsNumberBefore = profilePage.getExistingPostsNumber();
        int allPostsNumberBefore = profilePage.getAllPostsNumber();


        System.out.println("5.Go to New Post page");
        headerPage.goToNewPostPage();

        System.out.println("6.Upload a picture");
        NewPostPage newPostPage = new NewPostPage(driver);
        File uploadedfile = new File(pathname);
        newPostPage.uploadPost(uploadedfile);

        System.out.println("7.Verify that the picture is visible");
        newPostPage.verifyPostIsUploaded();

        System.out.println("8.Confirm that the picture name is correct");
        String expectedPostName = uploadedfile.getName();
        Assert.assertEquals(expectedPostName,newPostPage.confirmPostName(),"The file name is incorrect");

        System.out.println("9.Write a caption");
        newPostPage.enterCaption(captionText);

        System.out.println("10.Submit the post");
        newPostPage.submitThePost();

        System.out.println("11. Verify that the number of all posts and existing posts has increased");
        profilePage.verifyURL();
        int existingPostsNow = profilePage.getExistingPostsNumber();
        int allPostsNow = profilePage.getAllPostsNumber();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(existingPostsNow,existingPostsNumberBefore + 1 , "The number of existing posts does not match");
        softAssert.assertEquals(allPostsNow,allPostsNumberBefore + 1,"The number of all posts does not match");

        System.out.println("11. Scroll and Open the last post");
        profilePage.scrollAndOpenPost(existingPostsNow-1);


        System.out.println("12.Verify post details");
        PostModal postModal = new PostModal(driver);
        postModal.waitUntilModalAppears();
        Assert.assertEquals(postModal.getUsername(),username,"The Username does not match");

        softAssert.assertAll();

    }
}

