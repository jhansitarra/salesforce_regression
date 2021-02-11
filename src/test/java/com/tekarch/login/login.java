package com.tekarch.login;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tekarch.pologin.pologinSalesApp;
import com.tekarch.utilites.TestBase;

public class login extends TestBase{

    pologinSalesApp login;

    @BeforeTest
    public void settingUpEnvironment() throws Exception {
        sErrorMessage = "";
        sClassNameForScreenShot = getClass().getSimpleName();
        driver.get(oCons.getSalesForceURL());
        login = new pologinSalesApp(driver);
        login.checkLoggedIntoSFOrNotElseLogout();

    }

    @AfterMethod
    public void settingReqURL() throws Exception {
        driver.get(oCons.getSalesForceURL());
    }

    @Test(priority = 2)
    public void CheckForSuccessfulLogin() throws Exception {

        login.loginToSalesForce();
    }

    @Test(priority = 1)
    public void CheckForInvalidLogin() throws Exception {
        login.invalidLogintoSF();
    }

    @Test(priority = 3)
    public void CheckForResetPassword() throws Exception {
        login.forgotPassword();
    }
}