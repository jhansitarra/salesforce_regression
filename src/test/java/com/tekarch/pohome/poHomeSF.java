package com.tekarch.pohome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import com.tekarch.utilites.TestBase;

public class poHomeSF extends TestBase {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @FindBy(xpath="//span[@id='userNavLabel']")
    WebElement user_menu;

    @FindBy(id="userNav")
    WebElement activatedMenu;

    @FindBy(xpath="//a[contains(text(),'Logout')]")
    WebElement logout;

    @FindBy(id="logo")
    WebElement logo;

    @FindBy(xpath="//body/div[@id='contentWrapper']/div[@id='AppBodyHeader']/div[1]/div[1]/nav[1]/ul[1]/li[13]/a[1]")
    WebElement button_all_menu;

    @FindBy(xpath="//tbody/tr[1]/td[1]/a[1]")
    WebElement accounts_link;

    @FindBy(xpath="//tbody/tr[1]/td[2]/input[1]")
    WebElement new_account_button;

    @FindBy(xpath="//input[@id='acc2']")
    WebElement account_name_text_field;

    @FindBy(xpath="//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[2]/input[1]")
    WebElement save_account_button;

    @FindBy(xpath="//h2[contains(text(),'jhansi')]")
    WebElement account_name_header;

    @FindBy(xpath = "//tbody/tr[9]/td[2]/a[1]")
    WebElement opportunities_button;

    @FindBy(id="fcf")
    WebElement opportunities_dropDown;

    @FindBy(xpath = "//a[contains(text(),'Opportunity Pipeline')]")
    WebElement opportunity_pipeline;

    @FindBy(xpath = "//div[@id='status']")
    WebElement op_status_field;

    @FindBy(xpath = "//tbody/tr[3]/td[2]/a[1]")
    WebElement leads_tab;

    @FindBy(xpath = "//h2[contains(text(),'Home')]")
    WebElement home_tab;

    @FindBy(id = "fcf")
    WebElement drop_down_leads_view;

    public poHomeSF(WebDriver driver) {
        TestBase.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click_user_menu_and_verify() throws Exception {
        oBroUti.ufClick(user_menu);
        Thread.sleep(10000);
        String expectedActivatedClass = "menuButton menuButtonRounded";
        String actualActivatedClass = activatedMenu.getAttribute("class");
        assertEquals(actualActivatedClass,expectedActivatedClass, "menuButton");
    }

    public boolean logout() throws Exception {
        oBroUti.ufClick(logout);
        return true;
    }

    public String get_logo_text() throws Exception {
        return logo.getText();
    }

    public String create_new_account() throws Exception {
        String new_account_name = System.getProperty("new_account_name");
        oBroUti.ufClick(button_all_menu);
        Thread.sleep(3000);
        oBroUti.ufClick(accounts_link);
        Thread.sleep(3000);
        oBroUti.ufClick(new_account_button);
        Thread.sleep(3000);
        oBroUti.ufSendKeys(account_name_text_field, new_account_name);
        oBroUti.ufClick(save_account_button);
        Thread.sleep(3000);
        WebElement account_name_link = this.getWebElementWithDynamicPath("//a[contains(text(), 'xxxx')]", new_account_name);
        String actual_text = oBroUti.ufGetText(account_name_link);
        assertEquals(actual_text, new_account_name);
        return new_account_name;
    }

    public String get_opportunities_text() throws Exception {

        oBroUti.ufClick(button_all_menu);
        Thread.sleep(3000);
        oBroUti.ufClick(opportunities_button);
        Thread.sleep(3000);
        oBroUti.ufClick(opportunities_dropDown);
        return oBroUti.ufGetText(opportunities_dropDown);
    }

    public String get_opportunities_status() throws Exception {
        oBroUti.ufClick(button_all_menu);
        Thread.sleep(3000);
        oBroUti.ufClick(opportunities_button);
        Thread.sleep(3000);
        oBroUti.ufClick(opportunity_pipeline);
        Thread.sleep(3000);
        String expectedText = "complete";
        return oBroUti.ufGetText(op_status_field);
    }

    public String get_leads_info() throws Exception {
        oBroUti.ufClick(button_all_menu);
        Thread.sleep(3000);
        oBroUti.ufClick(leads_tab);
        Thread.sleep(3000);
        return oBroUti.ufGetText(home_tab);
    }

    public String get_leads_dropdown_view() throws Exception {
        oBroUti.ufClick(button_all_menu);
        Thread.sleep(3000);
        oBroUti.ufClick(leads_tab);
        Thread.sleep(3000);
        oBroUti.ufClick(drop_down_leads_view);
        Thread.sleep(3000);
        return oBroUti.ufGetText(drop_down_leads_view);
    }

}
