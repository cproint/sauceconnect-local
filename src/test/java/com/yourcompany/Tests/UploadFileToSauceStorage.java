package com.yourcompany.Tests;

import java.io.File;

import org.testng.annotations.Test;

import com.saucelabs.saucerest.SauceREST;


public class UploadFileToSauceStorage  {

  
    public String sauceUserName = System.getenv("SAUCE_USERNAME");
    public String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    
	SauceREST sauce = new SauceREST(sauceUserName, sauceAccessKey);

    
    File fileToUpload = new File("/Users/muralitulugu/Documents/testFile.rtf"); 

    @Test
    public void uploadFile() throws Exception {
 	
    	sauce.uploadFile(fileToUpload);


    }

}
