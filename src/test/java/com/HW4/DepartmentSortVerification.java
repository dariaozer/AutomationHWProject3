package com.HW4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DepartmentSortVerification {

    /**
     * 1. go to https://www.amazon.com
     * 2. verify that default value of the All departments dropdown is All
     * 3. verify that options in the All departments dropdown are not sorted alphabetically
     */
    @Test(description = "Verifying that department dropdown are not in alphabetic order")
    public void amazonDepartmentSortTesting(){
        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
        Assert.assertEquals(driver.findElement(By.className("nav-search-label")).getText(), "All");
        List<WebElement> l1 =new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        boolean isAlphOrder = false;
        for( int i =0; i<l1.size()-1; i++){
            if(l1.get(i).getText().compareTo(l1.get(i+1).getText())>0){
                isAlphOrder=true;
                break;
            }
        }
        Assert.assertTrue(isAlphOrder);
        driver.quit();
    }
}
