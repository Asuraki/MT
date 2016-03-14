package com.synebo.beans;

import com.sforce.soap.enterprise.sobject.Contact;
import com.synebo.tools.Util;
import com.univocity.parsers.annotations.Parsed;

/**
 * Created by User on 26.02.2016.
 */
public class ContactBean {

    @Parsed(field = "FirstName")
    private String FirstName;

    @Parsed(field = "LastName")
    private String LastName;

    @Parsed(field = "ID__c")
    private String ID__c;

    @Parsed(field = "Gender__c")
    private String Gender__c;

    @Parsed(field = "Birthdate")
    private String Birthdate;

    @Parsed(field = "Address_String__c")
    private String Address_String__c;

    @Parsed(field = "City__c")
    private String City__c;

    @Parsed(field = "Zip_Code__c")
    private String Zip_Code__c;

    @Parsed(field = "Phone")
    private String Phone;

    @Parsed(field = "MobilePhone")
    private String MobilePhone;

    @Parsed(field = "Relation_Code__c")
    private String Relation_Code__c;

    @Parsed(field = "Healthcare_Services__c")
    private String Healthcare_Services__c;

    @Parsed(field = "Fax")
    private String Fax;

    @Parsed(field = "Email")
    private String Email;

    @Parsed(field = "Contact_SFDC_ID")
    private String Contact_SFDC_ID;

    @Parsed(field = "Account_SFDC_ID")
    private String Account_SFDC_ID;

    @Parsed(field = "Center__c")
    private String Center__c;

    @Parsed(field = "Contact_Role__c")
    private String Contact_Role__c;

    public void setContact(Contact contact) {
        FirstName = contact.getFirstName();
        LastName = contact.getLastName();
        ID__c = contact.getID__c();
        Gender__c = contact.getGender__c();
        Birthdate = Util.convertDateToString(contact.getBirthdate());
        Address_String__c = contact.getAddress_String__c();
        City__c = contact.getCity__r() != null ? contact.getCity__r().getName() : null;
        Zip_Code__c = contact.getZip_Code__c();
        Phone = contact.getPhone();
        MobilePhone = contact.getMobilePhone();
        Relation_Code__c = contact.getRelation_Code__c();
        Healthcare_Services__c = contact.getHealthcare_Services__c();
        Fax = contact.getFax();
        Email = contact.getEmail();
        Contact_SFDC_ID = contact.getId().substring(0, 15);
        Account_SFDC_ID = contact.getAccountId();
        Center__c = contact.getCenter__c();
        Contact_Role__c = contact.getContact_Role__c();
    }
}
