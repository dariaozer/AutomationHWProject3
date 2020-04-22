package com.HW3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifySubscriptionMessage_T6 {

    /**
     * Test case #6
     * Step 1. Go to "https://www.tempmailaddress.com/"
     * Step 2. Copy and save email as a string.
     * Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
     * Step 4. And click on “Sign Up For Mailing List".
     * Step 5. Enter any valid name.
     * Step 6. Enter email from the Step 2.
     * Step 7. Click Sign Up
     * Step 8. Verify that following message is displayed:
     * “Thank you for signing up. Click the button below to
     * return to the home page.”
     * Step 9. Navigate back to the “https://
     * www.tempmailaddress.com/”
     * Step 10. Verify that you’ve received an email from
     * “do-not-reply@practice.cybertekschool.com”
     * Step 11. Click on that email to open it.
     * Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
     * Step 13. Verify that subject is: “Thanks for
     * subscribing to practice.cybertekschool.com!”
     *
     */

    private WebDriver driver;
    @Test(description = "Verifying subscription for email")
    public void verifySubscription(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tempmailaddress.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String email = driver.findElement(By.xpath("//span[@id='email']")).getText();
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("Tom Smith");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("button[name='wooden_spoon']")).click();
       String actual= driver.findElement(By.xpath("//h3[contains(text(),'Thank you for signing up')]")).getText();
       String expected ="Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual,expected);
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        WebElement emailTab = driver.findElement(By.xpath("//table//tbody//td[1]"));
        wait.until(ExpectedConditions.visibilityOf(emailTab));
        wait.until(ExpectedConditions.elementToBeClickable(emailTab)).click();

        String actual2 =driver.findElement(By.cssSelector("span[id='predmet']")).getText();
        String expected2 = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(actual2, expected2);
        driver.quit();
    }


}
