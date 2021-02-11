package com.tekarch.utilites;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


public class TestBase {
    public static CommonUtilities oCommUtil = new CommonUtilities();
    public static BrowserUtilities oBroUti = new BrowserUtilities();
    public static Constants oCons = new Constants();
//    public static ExtentManager oExt = new ExtentManager();
    Logger log = Logger.getLogger(getClass().getSimpleName());
    public static HashMap<String, String> sMapAuthorizationHeader = new HashMap<String, String>();
    public static WebDriver driver;
    public static String sHost;
    public static String sScreenShotName;
    public static String sClassNameForScreenShot;
    public static String sErrorMessage="";

    @BeforeSuite
    public void TriggerDependencies() throws Exception {
        log.info("Trigger Dependencies");
        oCommUtil.deleteScreenShotDirectory();
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
        oCommUtil.loadLog4jProperty(System.getProperty("user.dir") + "/src/main/java/properties/log4j.properties");
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/main/java/properties/testdata.properties");
        oCommUtil.deleteCreateScreenShotDirectorySureFireReports();

        oBroUti.launchBrowser(System.getProperty("Browser"));
        log.info("Triggerred all depencies");

    }

    @AfterSuite
    public void ShuttingDownAllDependencies() throws Exception {
        driver.quit();
    }

    // Added  a new function for getting element dynamically.
    public WebElement getWebElementWithDynamicPath (String xpathValue, String subValue ) {

        return driver.findElement(By.xpath(xpathValue.replace("xxxxx", subValue)));
    }
}