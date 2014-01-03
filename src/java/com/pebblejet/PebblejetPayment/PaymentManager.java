/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pebblejet.PebblejetPayment;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;


import com.pebblejet.PebblejetPayment.PaymentConstant;
import com.pebblejet.PebblejetPayment.SupplierInfo;
/**
 *
 * @author Saravanan
 */
public class PaymentManager {
     /**
     * <pre> return Connection</pre>
     */
    private Connection connection;
    /**
     * <pre> return Statement</pre>
     */
    private Statement statement;
    /**
     * <pre> return ResultSet</pre>
     */
    private ResultSet result;
    /**
     * <pre> return query</pre>
     */
    private String query = new String();
    
    
    
    /**
     * <pre> This Method will Provide Connection from pjPunchout</pre>
     */
    
    public Connection getPaymentDBConnection() {
        try {
            Context ctx = new InitialContext();
            DataSource webds = (DataSource) ctx.lookup(PaymentConstant.PAYMENT_CONTEXT);
            connection = webds.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            System.out.println("[PebblejetPayment][getPaymentDBConnection][ERROR] " + e.getMessage());
            return connection;
        }
        return connection;
    }

    /**
     * <pre> This Method will Close the Connection from PebblejetPayment</pre>
     */
    public void closePaymentDBConnection() {
        try {
            if (result != null) {
                result.close();
            }
        } catch (Exception ex) {
            System.out.println("[PebblejetPayment][closePaymentDBConnection][From rs]][ERROR]" + ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception ex) {
                System.out.println("[PebblejetPayment][closePaymentDBConnection][From statement]][ERROR]" + ex.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception ex) {
                    System.out.println("[PebblejetPayment][closePaymentDBConnection][From Connection]][ERROR]" + ex.getMessage());
                }
            }
        }
    }
    
    
     /**
     * <pre> This method validates  SUPPLIER Credential
     *
     * @return Boolean - Supplier Availability
     * </pre>
     */
    public boolean verifySupplier(String SUPPLIER_IDENTITY) {
        try {
            getPaymentDBConnection();
            boolean temp = false;
            query = "select * from  PJ_SUPPLIERS  SUPPLIER where SUPPLIER.PJ_SUPPLIER_IDENTITY='"+SUPPLIER_IDENTITY+"'";
            System.out.println("[PebblejetPayment][verifySupplier]" + query);
            result = statement.executeQuery(query);
            if (result.next()) {
                result.close();
                temp = true;
            }
            result.close();
            closePaymentDBConnection();
            return temp;
        } catch (Exception ex) {            
            System.out.println("[PebblejetPayment][verifySupplier][ERROR]" + ex.getMessage());
            closePaymentDBConnection();
            return false;
        }
    }
    
    /**
     * <pre> This method updates SUPPLIER Credential
     * </pre>
     */
    public void updateSupplierInformation(String SUPPLIER_IDENTITY,String SUBSCRIPTION_ID){
        try {
            getPaymentDBConnection();          
            query = "update PJ_SUPPLIERS set PJ_SUBSCRIPTION_ID='"+SUBSCRIPTION_ID+"' where PJ_SUPPLIER_IDENTITY='"+SUPPLIER_IDENTITY+"'";
            System.out.println("[PebblejetPayment][updateSupplierInformation]" + query);
            result = statement.executeQuery(query);
            result.close();
            closePaymentDBConnection();            
        } catch (Exception ex) {            
            System.out.println("[PebblejetPayment][updateSupplierInformation][ERROR]" + ex.getMessage());
            closePaymentDBConnection();            
        }
    }
    
    /**
     * This Method Create Supplier Information After Payment
     * @param supplierInfo 
     */
    public void insertSupplierDetails(SupplierInfo supplierInfo){
        try {
            getSupplierIdentity(supplierInfo);
            getSupplierId(supplierInfo);            
            query ="insert into " + PaymentConstant.PJ_SUPPLIERS + "(PJ_SUPPLIER_ID,SUPPLIER_NAME,ADDRESS1,ADDRESS2,CITY,STATE,POSTAL_CODE,COUNTRY,ENABLED_FLAG,SUPPLIER_TYPE,PHONE_NO,FREEZED,EMAIL,PJ_SUPPLIER_DOMAIN,PJ_SUBSCRIPTION_ID,PJ_SUPPLIER_IDENTITY) values ('" +supplierInfo.getSupplierId()+"','"
                    + supplierInfo.getSupplierName() + "','" + supplierInfo.getAddress() + "','" + supplierInfo.getExtendedAddress() + "','" + supplierInfo.getCity() + "','" + supplierInfo.getState() + "','"+supplierInfo.getPostalCode()+"','" + supplierInfo.getCountry() + "','"
                    +"Y"+"','"+""+"','"+supplierInfo.getPhoneNo()+"','"+"N"+"','"+supplierInfo.getEmail()+"','"+"PJID"+"','"+supplierInfo.getSubscriptionId()+"','"+"PJ000" + supplierInfo.getSupplierIdentity() + "-S" +"')";
             getPaymentDBConnection();
             System.out.println("[PebblejetPayment][insertSupplierDetails]" + query);
             result = statement.executeQuery(query);
             result.close();
             closePaymentDBConnection();
        } catch (Exception ex) {  
            System.out.println("[PebblejetPayment][updateSupplierInformation][ERROR]" + ex.getMessage());
            closePaymentDBConnection();    
        }
    }
    
    
    /**
     * Gets the Previous Sequences for Supplier Identity from  supplier Table
     */
    public void getSupplierIdentity(SupplierInfo supplierInfo){
        String SupplierIdentity = "";
        boolean check = true;
        try{
            getPaymentDBConnection();
            while (check){
                check = false;
                query = "select PJ_SUPPLIER_IDENTITY_SEQ.Nextval from dual";
                result = statement.executeQuery(query);
                if (result != null) {
                    if (result.next()) {
                        if (result.getString("Nextval") != null) {
                           SupplierIdentity =result.getString("Nextval");
                        }
                    }
                }
                query = "select * from PJ_SUPPLIERS where PJ_SUPPLIER_IDENTITY='" + "PJ000" + SupplierIdentity + "-S" + "'";
                result = statement.executeQuery(query);
                if (result != null) {
                    while (result.next()){             
                        check =true;
                        break;
                    }
                }
            }
            supplierInfo.setSupplierIdentity(SupplierIdentity);
        }catch(Exception ex) {
            System.out.println("[PebblejetPayment][getSupplierIdentity][ERROR]" + ex.getMessage());
            closePaymentDBConnection();              
        }finally{
            supplierInfo.setSupplierIdentity(SupplierIdentity);
        }
    }
    
    /**
     * Gets the Previous Sequences for Supplier Id  from supplier Table
     */
    public void getSupplierId(SupplierInfo supplierInfo){
        String SupplierId= "";
        boolean check = true;
        try{
            getPaymentDBConnection();
            while (check){
                check = false;
                query = "select PJ_SUPPLIER_ID_S.Nextval from dual";
                result = statement.executeQuery(query);
                if (result != null) {
                    if (result.next()) {
                        if (result.getString("Nextval") != null) {
                           SupplierId =result.getString("Nextval");
                        }
                    }
                }
                 query = "select * from PJ_SUPPLIERS where PJ_SUPPLIER_ID=" + SupplierId;
                result = statement.executeQuery(query);
                if (result != null) {
                    while (result.next()){             
                        check =true;
                        break;
                    }
                }
            }
            supplierInfo.setSupplierId(SupplierId);
        }catch(Exception ex) {
            System.out.println("[PebblejetPayment][getSupplierId][ERROR]" + ex.getMessage());
            closePaymentDBConnection();              
        }finally{
            supplierInfo.setSupplierId(SupplierId);
        }
    }
    
}
