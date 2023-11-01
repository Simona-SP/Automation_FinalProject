package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FollowUserTest extends BaseTest {
    

    @DataProvider(name = "successfulLogin")
    public Object[][] getValues() {
        return new Object[][]{
                {"SimonaSLS", "Monika987321*",}

        };
    }

    @Test(dataProvider = "successfulLogin",groups = "Positive scenarios")
    public void followUserTest(String username, String password) {
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

        System.out.println("4.Go to Profile Page and obtain the number of Followed users");
        headerPage.goToProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        int followedUsersBefore = profilePage.getAllFollowedUsersNumber();

        System.out.println("5.Open the last but one post at Home Page");
        homePage.navigateToHome();
        int homePostsSize = homePage.getAllHomePagePostsSize();
        homePage.openPost(homePostsSize - 2);

        System.out.println("6.Follow the user who uploaded the last but one post by clicking the Follow button in Post Modal and get his name");
        PostModal postModal = new PostModal(driver);
        postModal.waitUntilModalAppears();
        postModal.clickFollowButton();
        String followedUsername = postModal.getUsername();
        postModal.openUserProfilePage();

        System.out.println("7.Open Followed user's Profile page and check the Follow Button status");
        FollowedUserProfilePage followedUserProfilePage = new FollowedUserProfilePage(driver);
        String followBtnStatus = followedUserProfilePage.followBtnCheckStatus();
        Assert.assertEquals(followBtnStatus, "Unfollow", "The user is not being followed");

        System.out.println("8.Verify that the user'SimonaSLS' us listed in the Followers' list");
        followedUserProfilePage.openFollowersModal();
        FollowersModal followersModal = new FollowersModal(driver);
        Assert.assertTrue(followersModal.isUserListedInFollowers(username), "The user is not in the followers' list");
        postModal.closeModal();

        System.out.println("9.Verify that the name of the followed user is the one from the picture");
        String followedUsernameTitle = followedUserProfilePage.getFollowedUsernameTitle();
        Assert.assertEquals(followedUsername, followedUsernameTitle, " The name of followed user does not match");

        System.out.println("10.Navigate to Personal Profile page and Verify that the number of followed users has increased");
        headerPage.goToProfilePage();
        BasePage basePage = new BasePage(driver);
        basePage.verifyURL(profilePage.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsernameTitle(), username, "Username title is incorrect.");
        int followedUsersAfter = profilePage.getAllFollowedUsersNumber();
        Assert.assertEquals(followedUsersAfter, followedUsersBefore + 1, "The count of followed users is incorrect");


        System.out.println("11.Verify that the name of the followed user in Following modal corresponds to the one of the user uploaded the picture ");
        profilePage.openFollowingModal();
        FollowingUsersModal followingUsersModal = new FollowingUsersModal(driver);
        Assert.assertTrue(followingUsersModal.isTheUserFollowed(followedUsername), "User is not listed in your followers");

        System.out.println("12. Close the modal and log out");
        postModal.closeModal();
        headerPage.signOut();

    }

    @Test(dataProvider = "successfulLogin")
    public void unfollowUser(String username, String password){

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

            System.out.println("4.Go to Profile Page and obtain the number of Followed users");
            headerPage.goToProfilePage();
            ProfilePage profilePage = new ProfilePage(driver);
            int followedUsersBefore = profilePage.getAllFollowedUsersNumber();

            System.out.println("5.Open the Followed users list, get the  username and attirubte 'href' of the lastly followed user");
            profilePage.openFollowingModal();
            FollowingUsersModal followingUsersModal = new FollowingUsersModal(driver);
            followingUsersModal.waitTheFollowingUserModal();
            int followingListSize = followingUsersModal.getFollowedUsersSize();
            String lastFollowedUserName = followingUsersModal.getUsername(followingListSize-1);
            String href = followingUsersModal.getHref(followingListSize-1);

            System.out.println("6. Open the Profile of the lastly followed user ");
            followingUsersModal.OpenLastFollowedUserProfile(followingListSize-1);

           System.out.println("Verify that the URL of the followed user page contains the attribute'href'");
           FollowedUserProfilePage followedUserProfilePage = new FollowedUserProfilePage(driver);
           String currentProfileURL = followedUserProfilePage.getCurrentURL();
           Assert.assertTrue(currentProfileURL.contains(href));

            System.out.println("7.Verify that the name on the followed user Profile page matches the one from the modal");
            Assert.assertEquals(lastFollowedUserName,followedUserProfilePage.getFollowedUsernameTitle(),"The usernames do not match");

           System.out.println("8. check the Follow Button status");
           String followBtnStatus = followedUserProfilePage.followBtnCheckStatus();
           Assert.assertEquals(followBtnStatus, "Unfollow", "The user is not being followed");

            System.out.println("9.Unfollow the User");
            followedUserProfilePage.followOrUnfollow();

            System.out.println("10.Verify that the user 'SimonaSLS' is not listed in the Followers' list");
            followedUserProfilePage.openFollowersModal();
            FollowersModal followersModal = new FollowersModal(driver);
            Assert.assertTrue(followersModal.isUserListedInFollowers(username), "The user is still in the followers' list");
            PostModal postModal =new PostModal(driver);
            postModal.closeModal();


            System.out.println("11.Navigate to Personal Profile page and Verify that the number of followed users has decreased");
            headerPage.goToProfilePage();
            BasePage basePage = new BasePage(driver);
            basePage.verifyURL(profilePage.PROFILE_URL);
            Assert.assertEquals(profilePage.getUsernameTitle(), username, "Username title is incorrect.");
            int followedUsersAfter = profilePage.getAllFollowedUsersNumber();
            Assert.assertEquals(followedUsersAfter, followedUsersBefore - 1, "The count of followed users is incorrect");


            System.out.println("12.Verify that the name of the followed user is not present in Following modal");
            profilePage.openFollowingModal();
            Assert.assertTrue(followingUsersModal.isTheUserSuccessfulyUnfollowed(lastFollowedUserName), "User is not listed in your followers as expected");

            System.out.println("13. Close the modal and log out");
            postModal.closeModal();
            headerPage.signOut();

        }

    }






