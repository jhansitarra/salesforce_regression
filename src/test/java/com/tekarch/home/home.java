package com.tekarch.home;

import com.tekarch.pohome.poHomeSF;
import org.apache.log4j.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import com.tekarch.pologin.pologinSalesApp;
import com.tekarch.utilites.TestBase;


public class home extends TestBase{
    pologinSalesApp login;
    poHomeSF homeSF;

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @BeforeTest
    public void settingUpEnvironment() throws Exception {
        log.info("Lets login before running every test");
        sErrorMessage = "";
        sClassNameForScreenShot = getClass().getSimpleName();
        driver.get(oCons.getSalesForceURL());
        login = new pologinSalesApp(driver);
        homeSF = new poHomeSF(driver);
        login.loginToSalesForce();
    }

    @AfterTest
    public void tearDown() throws Exception {
        login.checkLoggedIntoSFOrNotElseLogout();
    }

    @Test
    public void checkMenuButton() throws Exception {
        homeSF.click_user_menu_and_verify();
    }

    @Test
    public void logoutFromHome() throws Exception {
        homeSF.logout();
        String expectedTest = "salesforce";
        assertEquals(expectedTest, homeSF.get_logo_text());
    }

    @Test
    public void createNewAccount() throws Exception {
        homeSF.create_new_account();
    }

    @Test
    public void opportunitesCheck() throws Exception {
        String expectedText = "all opportunities";
        String actualText = homeSF.get_opportunities_text();
        assertEquals(actualText, expectedText);
    }

    @Test
    public void opportunitiesStatusCheck() throws Exception {
        String expectedText = "complete";
        String actualText = homeSF.get_opportunities_status();
        assertEquals(actualText, expectedText);
    }

    @Test
    public void leadsTabCheck() throws Exception {
        String expectedText = "leads Home";
        String actualText = homeSF.get_leads_info();
        assertEquals(actualText, expectedText);
    }

    @Test
    public void checkDropDownLeadsView() throws Exception {
        String expectedText = "all open leads";
        String actualText = homeSF.get_leads_dropdown_view();
        assertEquals(actualText, expectedText);
    }
}
