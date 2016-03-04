package com.synebo.beans;

import com.sforce.soap.enterprise.sobject.Contact;
import com.synebo.tools.Util;
import com.univocity.parsers.annotations.Parsed;

/**
 * Created by User on 26.02.2016.
 */
public class ContactBean {

    @Parsed(field = "A")
    private String A;

    @Parsed(field = "B")
    private String B;

    @Parsed(field = "C")
    private String C;

    @Parsed(field = "D")
    private String D;

    @Parsed(field = "E")
    private String E;

    @Parsed(field = "F")
    private String F;

    @Parsed(field = "G")
    private String G;

    @Parsed(field = "H")
    private String H;

    @Parsed(field = "I")
    private String I;

    @Parsed(field = "J")
    private String J;

    @Parsed(field = "K")
    private String K;

    @Parsed(field = "L")
    private String L;

    @Parsed(field = "M")
    private String M;

    @Parsed(field = "N")
    private String N;

    @Parsed(field = "O")
    private String O;

    @Parsed(field = "P")
    private String P;

    @Parsed(field = "Q")
    private String Q;

    @Parsed(field = "R")
    private String R;

    public void setContact(Contact contact) {
        A = contact.getFirstName();
        B = contact.getLastName();
        C = contact.getID__c();
        D = contact.getGender__c();
        E = Util.convertDateToString(contact.getBirthdate());
        F = contact.getAddress_String__c();
        G = contact.getCity__r() != null ? contact.getCity__r().getName() : null;
        H = contact.getZip_Code__c();
        I = contact.getPhone();
        J = contact.getMobilePhone();
        K = contact.getRelation_Code__c();
        L = contact.getHealthcare_Services__c();
        M = contact.getFax();
        N = contact.getEmail();
        O = contact.getId().substring(0, 15);
        P = contact.getAccountId();
        Q = contact.getCenter__c();
        R = contact.getContact_Role__c();
    }
}
