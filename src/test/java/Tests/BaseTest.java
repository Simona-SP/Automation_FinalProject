package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
WebDriver driver;
public static final String Screenshot_Dir = "src\\test\\java\\Resources\\Screenshots\\";
    @BeforeMethod
    public void setUp()  {
        cleanDirectory((Screenshot_Dir));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void cleanDirectory(String filepath) {
        File directory = new File(filepath);
        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void takesScreenshot(ITestResult testResult)  {
        if(testResult.getStatus()==ITestResult.FAILURE){
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot =takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getTestName()+ testResult.getEndMillis();
            try {
                FileUtils.copyFile(screenshot, new File(Screenshot_Dir.concat(testName).concat(".jpeg")));
            }catch(IOException e){
                throw new RuntimeException();
            }
        }

    }
        @AfterMethod
        public void tearDown(ITestResult testResult) {
            takesScreenshot(testResult);
        driver.close();
    }
}
