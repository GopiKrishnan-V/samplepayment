/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pebblejet.PebblejetPayment;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chargebee.*;
import com.chargebee.Environment.*;
import com.chargebee.models.*;
import com.chargebee.models.enums.*;


import com.pebblejet.PebblejetPayment.PaymentConstant;
import com.pebblejet.PebblejetPayment.PaymentManager;
import com.pebblejet.PebblejetPayment.SupplierInfo;
 

/**
 *
 * @author Saravanan
 */
public class PaymentValidation extends HttpServlet {
    private PaymentConstant paymentConstant = new PaymentConstant();
    public SupplierInfo supplierInfo = new SupplierInfo();
    private String SUBSCRIPTION_ID;
    private String SUPPLIER_ID;
    private String CUSTOMER_ID;
    public PaymentManager paymentManager = new PaymentManager();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{            
            //handleRequest(request, response);
             response.sendRedirect(paymentConstant.REDIRECT_URL);
        }catch (Exception exp){
            response.sendRedirect(paymentConstant.ERROR_REDIRECT_URL);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try{
            //handleRequest(request, response);
             response.sendRedirect(paymentConstant.REDIRECT_URL);
        }catch (Exception exp){
            response.sendRedirect(paymentConstant.ERROR_REDIRECT_URL);
        }
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {        
        try{
        response.setContentType("text/plain");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();            
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                if(paramName.equals(paymentConstant.SUBSCRIPTION_ID)){
                     SUBSCRIPTION_ID =paramValue;
                     supplierInfo.setSubscriptionId(paramValue);
                }
                if(paramName.equals(paymentConstant.SUPPLIER_ID)){
                     SUPPLIER_ID =paramValue;                     
                }
                if(paramName.equals(paymentConstant.CUSTOMER_ID)){
                    CUSTOMER_ID = paramValue;
                }                         
            }
        }                
        if (SUBSCRIPTION_ID!= null  && !SUBSCRIPTION_ID.isEmpty() &&!CUSTOMER_ID.isEmpty()){
            if(SUPPLIER_ID !=null && !SUPPLIER_ID.isEmpty() && paymentManager.verifySupplier(SUPPLIER_ID)){                
                paymentManager.updateSupplierInformation(SUPPLIER_ID,SUBSCRIPTION_ID);              
            }else{
                System.out.println("[PebblejetPayment][handleRequest] Creating Supplier Information for Customer Id "+CUSTOMER_ID);                                
                Environment.configure(paymentConstant.SITE_URL,paymentConstant.API_KEY);                
                Result result = Customer.retrieve(CUSTOMER_ID).request();
                Customer customer = result.customer();           
                if(customer !=null){
                    setBillingInformation(customer);
                }
                paymentManager.insertSupplierDetails(supplierInfo);                
            }
            response.sendRedirect(paymentConstant.REDIRECT_URL);     
        }else{
            response.sendRedirect(paymentConstant.ERROR_REDIRECT_URL);     
        }         
        }catch(Exception ex){
            System.out.println("[PebblejetPayment][handleRequest] Error While Processing Redirect URL "+ex);                                       
        }finally{            
            try{
                response.sendRedirect(paymentConstant.ERROR_REDIRECT_URL);     
            }catch(Exception exce){
                System.out.println("[PebblejetPayment][handleRequest] Error While Processing Redirecting to Error URL "+exce);
            }
            
        }
    }

    /**
     * Set Billing Information for Customer
     */
    public void setBillingInformation(Customer customer){
            if(customer.firstName()!=null) {
                 supplierInfo.setSupplierName(customer.firstName().toString());                     
            }
            if(customer.billingAddress().line1() !=null){
                 supplierInfo.setAddress(customer.billingAddress().line1().toString());
            }
            if(customer.billingAddress().line2() != null){
                 supplierInfo.setExtendedAddress(customer.billingAddress().line2().toString());
            }
            if(customer.billingAddress().state() !=null){
                 supplierInfo.setState(customer.billingAddress().state().toString());
            }
            if(customer.billingAddress().zip() !=null){
                 supplierInfo.setPostalCode(customer.billingAddress().zip().toString());
            }
            if(customer.email() !=null){
                 supplierInfo.setEmail(customer.email().toString());
            }
            if(customer.phone() !=null){
                 supplierInfo.setPhoneNo(customer.phone());
            }
            if(customer.billingAddress().country()!=null){
                 supplierInfo.setCountry(customer.billingAddress().country().toString());
            }                    
       }
    }