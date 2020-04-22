package com.HW4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateVerifications {
    private static WebDriver driver;
    private static By yearBy =By.cssSelector("select[id='year']");
    private static By monthBy =By.cssSelector("select[id='month']");
    private static By dayBy =By.cssSelector("select[id='day']");


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/dropdown");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     *
     *  verify that dropdowns under Select your date of birth
     *  display current year, month, day
     */

    @Test(priority = 0, description = "Verifying current date")
    public static void test2(){
      WebElement year =  driver.findElement(yearBy);
      WebElement month =   driver.findElement(monthBy);
      WebElement day = driver.findElement(dayBy);
      //creating Select objects to select from dropdown
        Select sYear = new Select(year);
        Select sMonth = new Select(month);
        Select sDay = new Select(day);
        String yearSelected = sYear.getFirstSelectedOption().getText();
        String daySelected = sDay.getFirstSelectedOption().getText();
        String monthSelected = sMonth.getFirstSelectedOption().getText();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMMMd");
        Assert.assertEquals(yearSelected+monthSelected+daySelected, sf.format(new Date()));

    }
    /**
     * 2. select a random year under Select your date of birth
     * 3. select month January
     * 4. verify that days dropdown has current number of days
     * 5. repeat steps 3, 4 for all the months
     * NOTE: if you randomly select a leap year, verify February has 29 day
     */
 @Test(priority = 1, description = "Verifying number of days in Months")
    public void test3(){
     Select sYear = new Select(driver.findElement(yearBy));
     Select sMonth = new Select(driver.findElement(monthBy));
     Select sDay = new Select(driver.findElement(dayBy));
     Random random = new Random();
     //getOptions returns List<WebElement>
     int randomIndex = random.nextInt(sYear.getOptions().size());
     sYear.selectByIndex(randomIndex);
     //so, I will parse it into Integer, so that I can manipulate with numbers.
     int selectedYear = Integer.parseInt((sYear.getFirstSelectedOption().getText()));
     System.out.println("Randomly selected year is : "+sYear.getFirstSelectedOption().getText());
     List<String> months30Days = new ArrayList<>(Arrays.asList("September", "April", "June","November"));
     int februaryDayCount=0;
     if(selectedYear%4==0 && selectedYear%100!=0 && selectedYear%400==0){
         februaryDayCount =29;
     }else{
         februaryDayCount=28;
     }
//now, I will select months from drop down and check the the number of days.
     for(int i = 0; i<12; i++){
         sMonth.selectByIndex(i);
         if(months30Days.contains(sMonth.getFirstSelectedOption().getText())){
             Assert.assertEquals(sDay.getOptions().size(), 30);
             //printing out just because of curiosity :)
             System.out.println(sMonth.getFirstSelectedOption().getText());
             System.out.println(sDay.getOptions().size());
         }else if(sMonth.getFirstSelectedOption().getText().equalsIgnoreCase("february")){
             Assert.assertEquals(sDay.getOptions().size(), februaryDayCount);
             System.out.println(sMonth.getFirstSelectedOption().getText());
             System.out.println(sDay.getOptions().size());
         }else{
             Assert.assertEquals(sDay.getOptions().size(), 31);
             System.out.println(sMonth.getFirstSelectedOption().getText());
             System.out.println(sDay.getOptions().size());
         }
     }


 }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
 //Second Way:We solved in class.
//    @Test
//    public void test() {
//        driver.get("http://practice.cybertekschool.com/dropdown");
//        Select year = new Select(driver.findElement(By.id("year")));
//        Select month = new Select(driver.findElement(By.id("month")));
//        Select day = new Select(driver.findElement(By.id("day")));
//        Random random = new Random();
//        int yearToSelect = random.nextInt(year.getOptions().size());
//        //select a year
//        year.selectByIndex(yearToSelect);
//        for (int i = 0; i < 12; i++) {
//            //we create date object based on year and month
//            LocalDate localDate = LocalDate.of(yearToSelect, i + 1, 1);
//            //select a month
//            month.selectByIndex(i);
//            int actual = day.getOptions().size();//actual number of days
//            int expected = Month.from(localDate).length(isLeapYear(yearToSelect)); //expected number of days in a month
//            System.out.println("Month: " + month.getFirstSelectedOption().getText());
//            System.out.println("Expected number of days: " + expected);
//            System.out.println("Actual number of days: " + actual);
//            System.out.println();
//            Assert.assertEquals(actual, expected);
//        }
//        driver.quit();
//    }
//    public static boolean isLeapYear(int year) {
//        if (year % 4 == 0) {
//            if (year % 100 == 0) {
//                return year % 400 == 0;
//            }
//        }
//        return year % 4 == 0;
//    }
}


