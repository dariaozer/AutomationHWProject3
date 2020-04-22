package com.HW3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCodesVerifications_T9_To_T12 {


    /**
     * Test case #9-10-11-12
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 3. And click on “Status Codes”.
     * Step 4. Then click on “200”.
     * Step 5. Verify that following message is displayed:
     * “This page returned a 200 status code”
     * Same steps for codes, 301, 404, and 500
     */
private WebDriver driver;


@BeforeMethod
    public void setUp(){
    WebDriverManager.chromedriver().version("79").setup();
    driver = new ChromeDriver();
    driver.get("https://practice-cybertekschool.herokuapp.com");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Status Codes")).click();
    //BrowserFactory.wait(5);

}
@DataProvider(name="statusCodes")
public static Object [] statusCodes(){
    return new Object[]{"200", "301", "404", "500"};
}

@Test( dataProvider ="statusCodes", description = "Verifying status codes display messages by7 using data provider")
    public void verifyStatusCodeMessages(String str){
    driver.findElement(By.linkText(str)).click();
    String actual = driver.findElement(By.cssSelector("div[id='content']>div>p")).getText();
    String expected ="This page returned a "+ str+" status code.";
    Assert.assertTrue(actual.contains(expected));
}
@AfterMethod
    public  void tearDown(){
    driver.quit();
}
}
