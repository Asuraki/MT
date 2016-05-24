package com.synebo.populate.components;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.Lead;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.synebo.beans.ZipCodeBean;
import com.synebo.exception.ParserException;
import com.synebo.exception.SynchronizationException;
import com.synebo.populate.AbstractPopulate;
import com.synebo.synchronization.Synchronization;
import com.synebo.tools.CuttingCake;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by User on 29.02.2016.
 */
public class PopulateZipCode extends AbstractPopulate {

    private SObject[] leads;
    private SObject[] accounts;
    private SObject[] contacts;
    private List<ZipCodeBean> zipCodeBeans;

    public PopulateZipCode(EnterpriseConnection connection, Logger logger) {
        super(connection, logger);
    }

    @Override
    protected void loadDataFromSFDC() throws ConnectionException, SynchronizationException {
        QueryResult queryResult = connection.query("SELECT " +
                                                        "Id, " +
                                                        "City_Code__c, " +
                                                        "Street_Code__c, " +
                                                        "House_Number__c " +
                                                    "FROM Lead " +
                                                    "WHERE Zip_Code_Interface_Status__c = 'Ready to Sync' " +
                                                        "OR Zip_Code_Interface_Status__c = 'Ready for Sync'");
        leads = queryResult.getRecords();
        queryResult = connection.query("SELECT " +
                                            "Id, " +
                                            "City_Code__c, " +
                                            "Street_Code__c, " +
                                            "House_Number__c " +
                                        "FROM Account " +
                                        "WHERE Zip_Code_Interface_Status__c = 'Ready to Sync' " +
                                            "OR Zip_Code_Interface_Status__c = 'Ready for Sync'");
        accounts = queryResult.getRecords();
        queryResult = connection.query("SELECT " +
                                            "Id, " +
                                            "City_Code__c, " +
                                            "Street_Code__c, " +
                                            "House_Number__c " +
                                        "FROM Contact " +
                                        "WHERE Zip_Code_Interface_Status__c = 'Ready to Sync' " +
                                            "OR Zip_Code_Interface_Status__c = 'Ready for Sync'");
        contacts = queryResult.getRecords();

        logger.info("Selected "+ (leads.length+accounts.length+contacts.length) + " records.");
        if(leads.length+accounts.length+contacts.length == 0)
            throw new SynchronizationException("There are no records, ready to sync.");
    }

    @Override
    protected void parseFromCSV() throws IOException, ParserException {
        BeanListProcessor<ZipCodeBean> rowProcessor = new BeanListProcessor<>(ZipCodeBean.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setHeaders("A","B","C","D","E","F","G","H","I","J","K","L","M");
        parserSettings.setRowProcessor(rowProcessor);
        CsvParser parser = new CsvParser(parserSettings);
        Reader reader = new FileReader(Synchronization.ZIP_CODE_PATH);
        parser.parse(reader);
        reader.close();
        zipCodeBeans = rowProcessor.getBeans();
        if(zipCodeBeans.size() == 0)
            throw new ParserException(Synchronization.ZIP_CODE_PATH + " is empty or doesn't contain any values.");
    }

    @Override
    protected void populate() throws ConnectionException {
        ArrayList<Cortege> cortegeList = new ArrayList<>();
        SObject[] result;

        // generate cortegeList
        Cortege cortege;
        Lead lead;
        Account account;
        Contact contact;


        for(SObject sObject : leads){
            lead = (Lead) sObject;
            cortege = new Cortege(lead.getCity_Code__c(), lead.getStreet_Code__c(), lead.getHouse_Number__c());
            cortege.setsObject(sObject);
            cortegeList.add(cortege);
        }
        for(SObject sObject : accounts){
            account = (Account) sObject;
            cortege = new Cortege(account.getCity_Code__c(), account.getStreet_Code__c(), account.getHouse_Number__c());
            cortege.setsObject(sObject);
            cortegeList.add(cortege);
        }
        for(SObject sObject : contacts){
            contact = (Contact) sObject;
            cortege = new Cortege(contact.getCity_Code__c(), contact.getStreet_Code__c(), contact.getHouse_Number__c());
            cortege.setsObject(sObject);
            cortegeList.add(cortege);
        }

        // set zipCode to Cortege from csv
        for(ZipCodeBean zipCodeBean : zipCodeBeans) {
            for(Cortege cortege1 : cortegeList) {
                if (cortege1.cityCode != null && cortege1.streetCode != null && cortege1.houseNumber != null) {
                    if (
                        Objects.equals(deleteZero(cortege1.cityCode), deleteZero(zipCodeBean.getA())) &&
                        Objects.equals(deleteZero(cortege1.streetCode), deleteZero(zipCodeBean.getJ())) &&
                        Objects.equals(deleteZero(cortege1.houseNumber), deleteZero(zipCodeBean.getD())) &&
                        cortege1.zipCode == null
                        )
                        cortege1.zipCode = zipCodeBean.getG();
                } else if (cortege1.cityCode != null && cortege1.streetCode != null) {
                    if (
                        Objects.equals(deleteZero(cortege1.cityCode), deleteZero(zipCodeBean.getA())) &&
                        Objects.equals(deleteZero(cortege1.streetCode), deleteZero(zipCodeBean.getJ())) &&
                        cortege1.zipCode == null
                        )
                        cortege1.zipCode = zipCodeBean.getG();
                } else if (cortege1.cityCode != null) {
                    if (
                        Objects.equals(deleteZero(cortege1.cityCode), deleteZero(zipCodeBean.getA())) &&
                        cortege1.zipCode == null
                        )
                        cortege1.zipCode = zipCodeBean.getG();
                }
            }
        }

        // populate zipCode & change status
        result = new SObject[leads.length+accounts.length+contacts.length];
        int failedCount = 0;
        int syncedCount = 0;
        int i = 0;

        for(Cortege cor : cortegeList) {
            if(cor.sObject instanceof Lead) {
                lead = (Lead) cor.sObject;
                if(cor.zipCode != null) {
                    lead.setZip_Code__c(cor.zipCode);
                    lead.setZip_Code_Interface_Status__c("Synced");
                    lead.setCity_Code__c(null);
                    lead.setStreet_Code__c(null);
                    syncedCount++;
                } else {
                    lead.setZip_Code_Interface_Status__c("Failed");
                    lead.setZip_Sync_Failure_Reason__c("ZipCode is not found.");
                    lead.setCity_Code__c(null);
                    lead.setStreet_Code__c(null);
                    failedCount++;
                }
            }

            if(cor.sObject instanceof Account) {
                account = (Account) cor.sObject;
                if(cor.zipCode != null) {
                    account.setZip_Code__c(cor.zipCode);
                    account.setZip_Code_Interface_Status__c("Synced");
                    account.setCity_Code__c(null);
                    account.setStreet_Code__c(null);
                    syncedCount++;
                } else {
                    account.setZip_Code_Interface_Status__c("Failed");
                    account.setZip_Sync_Failure_Reason__c("ZipCode is not found.");
                    account.setCity_Code__c(null);
                    account.setStreet_Code__c(null);
                    failedCount++;
                }
            }

            if(cor.sObject instanceof Contact) {
                contact = (Contact) cor.sObject;
                if(cor.zipCode != null) {
                    contact.setZip_Code__c(cor.zipCode);
                    contact.setZip_Code_Interface_Status__c("Synced");
                    contact.setCity_Code__c(null);
                    contact.setStreet_Code__c(null);
                    syncedCount++;
                } else {
                    contact.setZip_Code_Interface_Status__c("Failed");
                    contact.setZip_Sync_Failure_Reason__c("ZipCode is not found.");
                    contact.setCity_Code__c(null);
                    contact.setStreet_Code__c(null);
                    failedCount++;
                }
            }
            result[i] = cor.sObject;
            i++;
        }

        CuttingCake cut = new CuttingCake(result);
        while(cut.hasNext()) {
            SObject[] tmp = cut.next();
            for(SObject tm : tmp) {
                System.out.println(tm.getId());
            }
            SaveResult[] saveResults = connection.update(tmp);
            int ia = 0;
            for(SaveResult saveResult : saveResults) {
                logger.warning(ia++ + " " + saveResult.getId() + " have errors: " + Arrays.toString(saveResult.getErrors()));
            }
        }
        logger.info("Update records. "+syncedCount+" records Synced. "+failedCount+" records Failed");
    }

    private static String deleteZero(String data) {
        if(data != null)
            while (data.startsWith("0")) {
            data = data.replaceFirst("0","");
        }
        return data;
    }

    @Override
    public String toString() {
        return "PopulateZipCode";
    }

    private class Cortege {
        String cityCode;
        String streetCode;
        String houseNumber;
        String zipCode;
        SObject sObject;

        Cortege() {}

        Cortege(String cityCode, String streetCode, String houseNumber) {
            this.cityCode = cityCode;
            this.streetCode = streetCode;
            if (houseNumber == null)
                this.houseNumber = "0";
            else
                this.houseNumber = houseNumber;
        }

        public SObject getsObject() {
            return sObject;
        }

        public void setsObject(SObject sObject) {
            this.sObject = sObject;
        }

    }
}
