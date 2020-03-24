package com.HW2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

public class VerifyFileIsUploaded_T7 {

    /**
     * Test case #7
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 2. And click on “File Upload".
     * Step 3. Upload any file with .txt extension from your
     * computer.
     * Step 4. Click “Upload” button.
     * Step 5. Verify that subject is: “File Uploaded!”
     * Step 6. Verify that uploaded file name is displayed.
     * Note: use element.sendKeys(“/file/path”) with
     * specifying path to the file for uploading. Run this
     * method against “Choose File” button.
     */

    @Test(description = "Verify file upload verification on practice website")
    public void test7(){
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys( "C:/Users/QCSS/Desktop/JavaScriptExecuterMethods.txt.docx");
        driver.findElement(By.id("file-submit")).click();
        String actualSubject = driver.findElement(By.tagName("h3")).getText();
        String expectedSubject = "File Uploaded!";
        Assert.assertEquals(actualSubject, expectedSubject);
        driver.quit();


    }
}
