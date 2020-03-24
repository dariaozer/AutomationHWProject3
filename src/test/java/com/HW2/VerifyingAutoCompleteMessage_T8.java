package com.HW2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyingAutoCompleteMessage_T8 {
    /**
     * Test case #8
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 2. And click on “Autocomplete”.
     * Step 3. Enter “United States of America” into
     * country input box.
     * Step 4. Verify that following message is displayed:
     * “You selected: United States of America
     */

    @Test(description = "Verify You selected: United States of America message is displayed")
    public void test8(){
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();
        String actual = driver.findElement(By.id("result")).getText();
        String expected = "You selected: United States of America";
        Assert.assertEquals(actual, expected);
        driver.quit();
    }
}
