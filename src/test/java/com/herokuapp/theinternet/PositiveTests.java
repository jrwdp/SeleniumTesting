package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;

public class PositiveTests {

    @Test
    public void loginTest() {
        System.out.println("Starting loginTest.");
//      Create driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

//      open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is now open");

//      Making it sleep for m seconds
        //sleep(2000);

        // Maximize browser window
        driver.manage().window().maximize();

//        enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //tomsmith
        //input[@id='username']
//      enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");
        //SuperSecretPassword!
        //input[@id='password']
//      click login button
        WebElement loginButton = driver.findElement((By.xpath("//button[@class='radius']")));
        loginButton.click();
        //button[@class='radius']

//        verification;
//            new url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected.");
        System.out.println("Expected URL, Yes");

//            logout button is visible
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "There is no logout button.");
        System.out.println("Expected logout button, Yes");
//            successful login message
        WebElement successMsg = driver.findElement(By.cssSelector("#flash"));
        String expectedMsg = "You logged into a secure area!";
        String actualMsg = successMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Actual message does not contain the expected message.\nActual Message: " + actualMsg + "\nExpected Messaged: " + expectedMsg);
        System.out.println("Expected success message");
        System.out.println("Test SAT!");

        /// Close browser
        driver.quit();
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
