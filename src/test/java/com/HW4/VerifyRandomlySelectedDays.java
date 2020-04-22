package com.HW4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class VerifyRandomlySelectedDays {

    /**
     * Randomly select a checkbox. As soon as you check any day, print the name of the day
     * and uncheck immediately.
     * After you check and uncheck Friday for the third time, exit the program.
     */
    @Test
    public void VerifyDays() {
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Random anyNum = new Random();
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input"));
        List<WebElement> days = driver.findElements(By.xpath("//label"));
        int randomIndex;
        int count = 0;
        while (count < 3) {
            randomIndex=anyNum.nextInt(checkBoxes.size());
                if (checkBoxes.get(randomIndex).isEnabled()) {
                    checkBoxes.get(randomIndex).click();
                    System.out.println(days.get(randomIndex).getText());
                    checkBoxes.get(randomIndex).click();
                    if(days.get(randomIndex).getText().equalsIgnoreCase("Friday")){
                        count++;
                    }
                   // checkBoxes.get(randomIndex).click();
                }
        }
        Assert.assertEquals(3, count);
        driver.quit();
    }
}
