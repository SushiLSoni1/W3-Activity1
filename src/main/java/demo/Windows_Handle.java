package demo;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Windows_Handle {
        ChromeDriver driver;

         public Windows_Handle(){

       System.out.println("Constructor: Windows_Handle");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
public void endTest()
{
    System.out.println("End Test: Windows_Handle");
    driver.close();
    driver.quit();
}
  public void takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String fileSeparator = System.getProperty("file.separator");
        String destFilePath = "path_to_save_screenshot" + fileSeparator + fileName + ".png";
        File destFile = new File(destFilePath);
        
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            // Handle the exception, for example:
            System.out.println("Error occurred while taking screenshot: " + e.getMessage());
        }
    }

    public void Windows_Handle() {
           System.out.println("Start Test case: Windows_Handle");
        //Navigate to url "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open"
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");
        //Switch to frame "iframeResult"
        driver.switchTo().frame("iframeResult");

        // Click on the "Try it" button using xpath "//button[text()='Try it']"
        WebElement tryItButton = driver.findElement(By.xpath("//button[text()='Try it']"));
        // Click on the "Try it" button
        tryItButton.click();
        //Store all the window handles
        Set<String> windowHandles = driver.getWindowHandles();
        String originalWindowHandle = driver.getWindowHandle();

        // Switch to the new window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);

                // Get the URL and Title of the new window
                String newWindowUrl = driver.getCurrentUrl();
                String newWindowTitle = driver.getTitle();
                // Print the URL and Title of the new window
                System.out.println("URL of the new window: " + newWindowUrl);
                System.out.println("Title of the new window: " + newWindowTitle);
                // Take a screenshot of the new window
                takeScreenshot(driver, "screenshot_filename");
            }
        }      
        System.out.println("End Test case: Windows_Handle");  
    
}
    
}

