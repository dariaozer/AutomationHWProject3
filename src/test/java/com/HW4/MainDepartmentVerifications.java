package com.HW4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainDepartmentVerifications {

    @Test
    public void main_departments(){
        WebDriverManager.chromedriver().version("79").setup();
       WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/gp/site-directory");
        List<WebElement> mainDep = driver.findElements(By.tagName("h2"));
        List<WebElement> allDep = new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        Set<String> mainDepS = new HashSet<>();
        Set<String> allDepS = new HashSet<>();
        for( WebElement each : mainDep){
            mainDepS.add(each.getText());
        }
        for( WebElement each : allDep){
            allDepS.add(each.getText());
        }
        for(String each : mainDepS){
            if(!allDepS.contains(each)){
                System.out.println(each);
                System.out.println("This main dep is not in All departments list");
            }
        }
        Assert.assertTrue(allDep.containsAll(mainDepS));
        driver.quit();
    }
}
