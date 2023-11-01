package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;

public class NewPostPage extends BasePage {

  @FindBy(css = "input.file")
  WebElement fileInput;

  @FindBy(css = "input[name= 'caption']")
  WebElement captionField;

  @FindBy(css = ".input-group input")
  WebElement fileNameInput;

  @FindBy(className = "image-preview")
  WebElement imgPreview;

  @FindBy(css = "button[type='submit']")
  WebElement submitPostButton;

    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void uploadPost(File file){
        fileInput.sendKeys(file.getAbsolutePath());
    }

    public void verifyPostIsUploaded(){
        wait.until(ExpectedConditions.visibilityOf(imgPreview));
    }

    public String confirmPostName(){
     return fileNameInput.getAttribute("placeholder");
    }

    public void enterCaption(String captionText){
        captionField.sendKeys(captionText);
    }

    public void submitThePost(){
        clickOnElement(submitPostButton);

    }
}


