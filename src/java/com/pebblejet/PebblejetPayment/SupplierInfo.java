/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pebblejet.PebblejetPayment;


/**
 *
 * @author Saravanan
 */
public class SupplierInfo  {
       
    private String PhoneNo;
    private String SupplierName;
    private String Address;
    private String ExtendedAddress;
    private String City;
    private String State;
    private String Country;
    private String PostalCode;    
    private String Email;
    private String SubscriptionId;
    private String SupplierIdentity;
    private String SupplierId;

    

    public SupplierInfo() {
        this.PhoneNo = "";
        this.SupplierName = "";
        this.Address = "";
        this.ExtendedAddress = "";
        this.City = "";
        this.State = "";
        this.Country = "";
        this.PostalCode = "";
        this.Email = "";
        this.SubscriptionId = "";
       
    }

    
    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getExtendedAddress() {
        return ExtendedAddress;
    }

    public void setExtendedAddress(String ExtendedAddress) {
        this.ExtendedAddress = ExtendedAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSubscriptionId() {
        return SubscriptionId;
    }

    public void setSubscriptionId(String SubscriptionId) {
        this.SubscriptionId = SubscriptionId;
    }  
    
    public String getSupplierIdentity() {
        return SupplierIdentity;
    }

    public void setSupplierIdentity(String SupplierIdentity) {
        this.SupplierIdentity = SupplierIdentity;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String SupplierId) {
        this.SupplierId = SupplierId;
    }
}
