package com.HW3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;


public class RegistrationFormVerifications_T1_To_T5 {
    private WebDriver driver;
    private By registrationLinkBy = By.linkText("Registration Form");
    private By dobBoxBy= By.cssSelector("input[placeholder='MM/DD/YYYY']");
    private By invalidDobWarningBy = By.cssSelector("small[data-bv-result='INVALID']");
    private By cppBy = By.cssSelector("label[for='inlineCheckbox1']");
    private By jBy =By.cssSelector("label[for='inlineCheckbox2']");
    private By jsBy = By.cssSelector("label[for='inlineCheckbox3']");
    private By firstNameBy = By.cssSelector("input[data-bv-field='firstname']");
    private By firstNameWarningBy =By.cssSelector("small[data-bv-result='INVALID']");
    private By lastNameBy =By.cssSelector("input[data-bv-field='lastname']");
    private By emailBy = By.cssSelector("input[data-bv-field='email']");
    private By userNameBy =By.cssSelector("input[data-bv-field='username']");
    private By passwordBy = By.cssSelector("input[data-bv-field='password']");
    private By phoneBy = By.cssSelector("input[data-bv-field='phone']");
    private By dobBy= By.cssSelector("input[data-bv-field='birthday']");
   // private By submitBy = By.cssSelector("div[class='col-sm-9 col-sm-offset-3']");
    private By submitBy = By.cssSelector("#wooden_spoon");
   // radio button felame css-- input[value="female"]


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        BrowserFactory.wait(3);
        driver.findElement(registrationLinkBy).click();
    }

    /**
     * TestCase#1
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter “wrong_dob” into date of birth input box.
     * Step 4. Verify that warning message is displayed:
     * “The date of birth is not valid
     */

    @Test(description =" Verifying warning message for invalid DOB input" )
    public void verifyWarningMessage(){

    driver.findElement(dobBoxBy).sendKeys("wrong_dob");
    String expected ="The date of birth is not valid";
    String actual = driver.findElement(invalidDobWarningBy).getText();
    BrowserFactory.wait(3);
        Assert.assertEquals(actual, expected);
    }

    /**TestCase#2
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Verify that following options for
     * programming languages are displayed: c++, java,
     * JavaScript
     */
    @Test(description = "Verifying Programming Languages Display")
    public void verifyProgrammingLangs(){
     String expected = "C++ Java JavaScript";
     String str1 = driver.findElement(cppBy).getText();
     String str2 = driver.findElement(jBy).getText();
     String str3 = driver.findElement(jsBy).getText();
     String actual = str1+" "+str2+" "+str3;
     Assert.assertEquals(actual, expected);
    }

    /**
     * Test case #3
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter only one alphabetic character into first
     * name input box.
     * Step 4. Verify that warning message is displayed:
     * “first name must be more than 2 and less than 64
     * characters long
     */

    @Test(description ="Verifying warning message for one char input in First name box")
    public void verifyFirstNameWarning(){
        driver.findElement(firstNameBy).sendKeys("a");
        String actual = driver.findElement(firstNameWarningBy).getText();
        String expected = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test case #4
     * Step 1. Go to https://practicecybertekschool.herokuapp.com
     * Step 2. Click on “Registration Form”
     * Step 3. Enter only one alphabetic character into last
     * name input box.
     * Step 4. Verify that warning message is displayed:
     * “The last name must be more than 2 and less than
     * 64 characters long
     */
    @Test(description = "Verifying warning message for one char input in last name box")
    public void verifyLastNameWarning(){
        driver.findElement(lastNameBy).sendKeys("a");
        String actual = driver.findElement(By.cssSelector("small[data-bv-result='INVALID']")).getText();
        String expected ="The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual,expected);
    }


    /**
     * Test case #5
     * Step 1. Go to “https://practicecybertekschool.herokuapp.com”
     * Step 2. Click on “Registration Form”
     * Step 3. Enter any valid first name.
     * Step 4. Enter any valid last name.
     * Step 5. Enter any valid user name.
     * Step 6. Enter any valid password.
     * Step 7. Enter any valid phone number.
     * Step 8. Select gender.
     * Step 9. Enter any valid date of birth.
     * Step 10. Select any department.
     * Step 11. Enter any job title.
     * Step 12. Select java as a programming language.
     * Step 13. Click Sign up.
     * Step 14. Verify that following success message is
     * displayed: “You've successfully completed
     * registration!”
     *
     */
    @Test(description = "Verifying successfully completed registration message ")
    public void verifyRegistration(){
        driver.findElement(firstNameBy).sendKeys("Java");
        driver.findElement(lastNameBy).sendKeys("Selenium");
        driver.findElement(userNameBy).sendKeys("HelloJava");
        driver.findElement(emailBy).sendKeys("efewtrell8c@craigslist.org");
        driver.findElement(passwordBy).sendKeys("123ed!2344c");
        driver.findElement(phoneBy).sendKeys("987-321-1234");
        driver.findElement(By.cssSelector("input[value='female']")).click();
        driver.findElement(dobBy).sendKeys("01/25/1990");
        driver.findElement(By.cssSelector("input[value='female']")).click();
        driver.findElement(By.cssSelector("select[name=department]")).click();
        driver.findElement(By.cssSelector("select[name=department] option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("select[name=job_title]")).click();
        driver.findElement(By.cssSelector("select[name=job_title] option:nth-child(6)")).click();
        driver.findElement(By.cssSelector("#inlineCheckbox2")).click();
        driver.findElement(By.id("wooden_spoon")).click();
        String expected = "You've successfully completed registration!";
        String actual =driver.findElement(By.cssSelector("div[id=content]> div>div>p")).getText();
        Assert.assertEquals(actual,expected);

    }

    @AfterMethod
    public void tearDown(){
        BrowserFactory.wait(5);
       driver.quit();
    }
}
