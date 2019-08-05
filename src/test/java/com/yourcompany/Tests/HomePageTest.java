package com.yourcompany.Tests;

import com.yourcompany.Pages.HomePage;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

/**
 * Created by mehmetgerceker on 12/7/15.
 */

public class HomePageTest extends TestBase {

    /**
     * Runs a simple test verifying link can be followed.
     *
     * @throws InvalidElementStateException
     */
    @Test(dataProvider = "hardCodedBrowsers", enabled = true)
    public void verifyTitleTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        createDriver(browser, version, os, method.getName(),"SmokeTest");
        WebDriver driver = getWebDriver();

        annotate("Open Jenkins page...");
        HomePage page = HomePage.homePage(driver);


        annotate("Asserting that Jenkins Home Page Title...");
        Assert.assertEquals(page.getTitle(),"Jenkins");
    }
    
    @Test(dataProvider = "hardCodedBrowsers", enabled = false)
    public void verifyIsLoginTextBoxDisplayed(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {

        //create webdriver session
        createDriver(browser, version, os, method.getName(),"RegressionTest");
        WebDriver driver = getWebDriver();

        annotate("on Jenkins page...");
        HomePage page = HomePage.homePage(driver);


        annotate("Asserting that Jenkins LoginTextBox is Displayed...");       
        Assert.assertEquals(page.isLoginTextBoxDisplayed(),true);
    }

}