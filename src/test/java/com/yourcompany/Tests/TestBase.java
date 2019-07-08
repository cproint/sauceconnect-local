package com.yourcompany.Tests;

import org.openqa.selenium.JavascriptExecutor;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.lift.TestContext;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

import com.saucelabs.ci.sauceconnect.*;
import com.saucelabs.ci.sauceconnect.AbstractSauceTunnelManager.SauceConnectException;
import com.saucelabs.saucerest.SauceREST;
import java.io.File;
/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider in order to supply multiple browser combinations.
 *
 * @author Neil Manvar
 */
public class TestBase  {

    public String buildTag = System.getenv("BUILD_TAG");

    public String sauceUserName = System.getenv("SAUCE_USERNAME");
    public String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    

    
    public String sauceTunnelOptions = System.getenv("SAUCE_TUNNEL_OPTIONS");
    
	SauceREST sauce = new SauceREST(sauceUserName, sauceAccessKey);

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return Two dimensional array of objects with browser, version, and platform information
     */
/*    
    Process tunnel;
    public SauceConnectFourManager sauceFourTunnelManager = new SauceConnectFourManager(); 

    
	@BeforeClass
	public void startTunnel() throws SauceConnectException {
		
		
				sauceFourTunnelManager.setUseLatestSauceConnect(true);		
				tunnel = sauceFourTunnelManager.openConnection(
				sauceUserName,      // username
				sauceAccessKey,       // apiKey
				 4445,           // port
				 null,           // sauceConnectJar
				 sauceTunnelOptions,  // Tunnel options as String
				 null,           // printStream
				 null,           // verboseLogging
				 null            // sauceConnectPath
				 );
		System.out.println("Started Tunnel");
	}
*/
    
    
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
                new Object[]{"firefox", "", "Windows 10"},
                new Object[]{"internet explorer", "11.0", "Windows 7"},
                new Object[]{"safari", "10.0", "OS X 10.11"},
                new Object[]{"chrome", "latest", "OS X 10.10"},
                new Object[]{"firefox", "latest-1", "Windows 7"},
                new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
                new Object[]{"firefox", "", "Windows 10"},
                new Object[]{"internet explorer", "11.0", "Windows 7"},
                new Object[]{"safari", "10.0", "OS X 10.11"}
        };
    }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @param methodName Represents the name of the test case that will be used to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriver(String browser, String version, String os, String methodName, String tagName)
            throws MalformedURLException, UnexpectedException {
    	
      DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("tags", "someTest");

        capabilities.setCapability("tunnel-identifier", "El_Chapo_Tunnel");
        //capabilities.setCapability("parentTunnel", "muralitulugu");
        capabilities.setCapability("public", "team");
        //capabilities.setCapability("extendedDebugging", "true");
        //capabilities.setCapability("videoUploadOnPass", "false");
 
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);            
        }

        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(
                new URL("https://" + sauceUserName + ":" + sauceAccessKey + "@ondemand.saucelabs.com:443/wd/hub"),
                capabilities));

        
       
        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
        
        
        // Testing Jenkins Plug-in
        	 
            String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
            (((RemoteWebDriver) getWebDriver()).getSessionId().toString()), methodName.getClass().getName());
            System.out.println(message);
                    
        
        
        
    }

    /**
     * Method that gets invoked after test.
     * Dumps browser log and
     * Closes the browser
     */
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
  //     ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        
    	if (result.isSuccess()) {
    		
            sauce.jobPassed(((RemoteWebDriver) getWebDriver()).getSessionId().toString());

    	} else {
    	
    		sauce.jobFailed(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
    	}
    	

    	webDriver.get().quit();
        webDriver.remove();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
    
/*	@AfterClass
	public void stopTunnel() throws SauceConnectException {
		
		sauceFourTunnelManager.closeTunnelsForPlan(
				sauceUserName,      // username (same as start tunnel)
				sauceTunnelOptions,  // tunnelOptions (same as start tunnel)
				  null);
		
		System.out.println("Stopped Tunnel");
       ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));

		
	}
*/
}
