package com.yourcompany.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class JenkinsHomePage {

    public WebDriver driver;
    //public static String url = "http://localhost:8080";
    public static String url = "http://54.160.91.35:8080";
    
    public static JenkinsHomePage homePage(WebDriver driver) {
        JenkinsHomePage page = new JenkinsHomePage(driver);
        page.getURL();
        return page;
    }

    public JenkinsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getURL() {
        this.driver.get(url);
    }



    public String getTitle() {
        return driver.getTitle();
    }

}
