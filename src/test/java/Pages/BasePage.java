package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    WebDriverWait mediumWait;

    Actions actions;
    public final String PROFILE_URL = "http://training.skillo-bg.com:4200/users/4964";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        mediumWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void enterText(WebElement element, String text) {

        wait.until(ExpectedConditions.visibilityOf(element));
        clickOnElement(element);
        element.sendKeys(text);
    }

    public void populateRegisterData(WebElement element, String input){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(input);
    }
    public void verifyURL(String url) {

        wait.until(ExpectedConditions.urlToBe(url));
    }


}

