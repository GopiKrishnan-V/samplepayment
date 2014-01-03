//******************************************************************************
// Package Declaration
//******************************************************************************
package com.pebblejet.PebblejetPayment;
//******************************************************************************
// Import Specifications
//******************************************************************************

/**
 *******************************************************************************
 * Copyright (c) 2013 PebbleJet.com, Inc.
 *
 * All Rights Reserved
 *******************************************************************************
 * <B> Class Description: </B><p><pre>
 *     This class composes Order Status file string
 * </pre>
 *******************************************************************************
 * <B> Author: </B><p><pre>
 * Gopi Krishnan
 * </pre>
 *******************************************************************************
 */
//******************************************************************************
// Class Definition
//******************************************************************************
public class PaymentConstant {
    //**************************************************************************
    // Attributes Definitions
    //**************************************************************************
    //Constants

    public static final String STATUS_CODE_SUCCESS = "200";
    
    public static final String PAYMENT_CONTEXT = "jdbc/pjOnboarding";
    //public static final String REDIRECT_URL="https://dev-cloud.pebblejet.com/PebbleJetPayment/thankYouPage.html";
    //public static final String ERROR_REDIRECT_URL="https://dev-cloud.pebblejet.com/PebbleJetPayment/ErrorPage.html";
    public static final String REDIRECT_URL="http://redcross.pebblejet.com:3000/pebblejetApp/index.html#/menutab/catalogue";
    public static final String ERROR_REDIRECT_URL="http://redcross.pebblejet.com:3000/pebblejetApp/index.html#/menutab/catalogue";
    public static final String PJ_SUPPLIERS ="PJ_SUPPLIERS";
    public static final String API_KEY= "test_KfkINjDKJB4Sm1ka30udWfJhop0cd8QBr";
    public static final String SITE_URL ="pebblejettest-test";
    
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String SUBSCRIPTION_ID = "SUBSCRIPTION_ID";
    public static final String SUPPLIER_ID = "SUPPLIER_ID";
    
    public static final String SUPPLIER_NAME ="SUPPLIER_NAME";          
    public static final String ADDRESS ="ADDRESS";
    public static final String EXTENDED_ADDRESS2="EXTENDED_ADDRESS2";
    public static final String CITY="CITY";
    public static final String STATE="STATE";
    public static final String COUNTRY="COUNTRY";
    public static final String POSTAL_CODE="POSTAL_CODE";
    public static final String PHONE_NO="PHONE_NO";
    public static final String EMAIL="EMAIL";         
}
