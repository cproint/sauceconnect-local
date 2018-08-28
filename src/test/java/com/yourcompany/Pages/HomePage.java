package com.yourcompany.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(id = "j_username")
	@CacheLookup
	private WebElement emailTextBox;
	
	
	
    public WebDriver driver;
    public static String url = "http://localhost:8080";
    //public static String url = "http://54.160.91.35:8080";
    
    public static HomePage homePage(WebDriver driver) {
        HomePage page = new HomePage(driver);
        page.getURL();
        return page;
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getURL() {
        this.driver.get(url);
    }



    public String getTitle() {
        return driver.getTitle();
    }
    
    public boolean isLoginTextBoxDisplayed() {
        return emailTextBox.isDisplayed();
    }

}
