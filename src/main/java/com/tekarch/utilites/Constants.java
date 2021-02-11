package com.tekarch.utilites;

import org.apache.log4j.Logger;

public class Constants {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    public static final String sContentType="application/json";
    public String getSalesForceURL() throws Exception{
        return "https://login.salesforce.com/";
    }

}
