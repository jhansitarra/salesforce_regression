package com.tekarch.pologin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tekarch.utilites.TestBase;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;


public class pologinSalesApp  extends TestBase {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @FindBy(id="username")
    WebElement el_useranme;

    @FindBy(id="password")
    WebElement el_password;

    @FindBy(xpath="//input[@id='Login']")
    WebElement button_login;

    @FindBy(linkText="Logout")
    WebElement button_logout;

    @FindBy(xpath="//a[contains(text(),'Home')]")
    WebElement button_home;

    @FindBy(id="forgot_password_link")
    WebElement forgot_password_link;

    @FindBy(xpath="//div[@id='error']")
    WebElement error_message_div;

    @FindBy(id="un")
    WebElement forgot_username;

    @FindBy(xpath="//input[@id='continue']")
    WebElement button_reset_continue;

    @FindBy(xpath="//p[contains(text(),'We’ve sent you an email with a link to finish rese')]")
    WebElement reset_message_div;

    @FindBy(xpath="//a[contains(text(),'Home')]")
    WebElement home_tab;

    public pologinSalesApp(WebDriver driver){
        TestBase.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean loginToSalesForce() throws Exception{
        log.debug("LOGging into SalesForce");
        oBroUti.waitForElementVisible(driver, el_useranme, 5);
        oBroUti.ufSendKeys(el_useranme, System.getProperty("sf_user_name"));
        oBroUti.ufSendKeys(el_password, System.getProperty("sf_password"));
        oBroUti.ufClick(button_login);
        Thread.sleep(20000);
        oBroUti.waitForElementVisible(driver, home_tab, 20);
        log.debug("Successfully logged into Salesforce");
        return true;
    }

    public boolean logoutOfSalesForce() throws Exception {
        if(oBroUti.isDisplayed(button_logout))
            button_logout.click();
        return true;
    }

    public boolean checkLoggingIntoSF() throws Exception{

        boolean bRes_Flag=false;
        try {
            oBroUti.waitForElementVisible(driver, button_home, 3);
        }catch(Exception ea) {log.error("By passing error when we run on suite case");}
        if(!oBroUti.isDisplayed(button_home))
            bRes_Flag=this.loginToSalesForce();

        return bRes_Flag;
    }

    public boolean checkLoggedIntoSFOrNotElseLogout() throws Exception{
        boolean bRes_Flag=false;
        if(oBroUti.isDisplayed(button_home))
            bRes_Flag=this.logoutOfSalesForce();

        return bRes_Flag;

    }

    public boolean invalidLogintoSF() throws Exception {
        oBroUti.waitForElementVisible(driver, el_useranme, 5);
        oBroUti.ufSendKeys(el_useranme, System.getProperty("invalid_sf_username"));
        oBroUti.ufSendKeys(el_password, System.getProperty("invalid_sf_password"));
        oBroUti.ufClick(button_login);
        Thread.sleep(20000);
        String expectedError = "Your access to salesforce.com has been disabled by your system administrator. Please contact your Administrator for more information.";
        String actualError = oBroUti.ufGetText(error_message_div);
        log.debug(actualError);
        assertEquals(expectedError, actualError);
        return true;
    }

    public boolean forgotPassword() throws Exception {
        oBroUti.ufClick(forgot_password_link);
        oBroUti.ufSendKeys(forgot_username, System.getProperty("invalid_sf_username"));
        oBroUti.ufClick(button_reset_continue);

        String expectedMessage = "We’ve sent you an email with a link to finish resetting your password.";
        String resetMessage = oBroUti.ufGetText(reset_message_div);
        log.debug(resetMessage);
        assertEquals(expectedMessage, resetMessage);
        return true;
    }
}
