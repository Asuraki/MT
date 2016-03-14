package com.synebo.exportFromSFDC.components;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.synebo.exception.SynchronizationException;
import com.synebo.synchronization.Synchronization;
import com.synebo.beans.ContactBean;
import com.synebo.exportFromSFDC.AbstractExportFromSFDC;
import com.synebo.tools.CuttingCake;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by User on 26.02.2016.
 */
public class ExportContactFromSFDC extends AbstractExportFromSFDC {

    private SObject[] records;
    private List<Contact> syncedList = new ArrayList<>();
    private Map<Contact, String> failedMap = new HashMap<>();

    public ExportContactFromSFDC(EnterpriseConnection connection, Logger logger) {
        super(connection, logger);
    }

    @Override
    protected void loadDataFromSFDC() throws ConnectionException {
        QueryResult queryResult = connection.query("SELECT " +
                                                        "Main_Contact__c," +
                                                        "Secondary_Contact__c," +
                                                        "Performs_Deposit__c," +
                                                        "Legal_Representative__c," +
                                                        "Guarantor__c," +
                                                        "FirstName," +
                                                        "LastName," +
                                                        "ID__c," +
                                                        "Gender__c," +
                                                        "Birthdate," +
                                                        "Address_String__c," +
                                                        "City__r.Name," +
                                                        "Zip_Code__c," +
                                                        "Phone," +
                                                        "MobilePhone," +
                                                        "Relation_Code__c," +
                                                        "Healthcare_Services__c," +
                                                        "Fax," +
                                                        "Email," +
                                                        "Id, " +
                                                        "AccountId, " +
                                                        "Center__c, " +
                                                        "Interface_status__c, " +
                                                        "Contact_Role__c, " +
                                                        "Contact_Sync_Failure_Reason__c " +
                                                    "FROM Contact " +
                                                    "WHERE Interface_status__c  = 'Ready for Sync'");
        records = queryResult.getRecords();
        logger.info("Selected "+ records.length + " records.");
    }

    @Override
    protected void writeCSV() throws IOException, SynchronizationException {
        if(records.length == 0)
            throw new SynchronizationException("There are no records, ready to sync.");

        String date = new SimpleDateFormat("ddMMYY_HH-mm").format(new Date());
        String outputPath = Synchronization.PRIORITY_WAITING_FOR_SYNC_PATH+"\\C_"+date+"_"+records.length+".csv";
        Writer outputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8"));
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setQuoteAllFields(false);
        settings.setRowWriterProcessor(new BeanWriterProcessor<ContactBean>(ContactBean.class));
        settings.setHeaders("FirstName", "LastName", "ID__c", "Gender__c", "Birthdate", "Address_String__c", "City__c",
                            "Zip_Code__c", "Phone", "MobilePhone", "Relation_code_index_c", "Healthcare_Services__c", "Fax",
                            "Email", "Contact_SFDC_ID", "Account_SFDC_ID", "Center__c", "Contact_Role__c");
        CsvWriter writer = new CsvWriter(outputWriter, settings);
        writer.writeHeaders();
        ContactBean contactBean;
        for(SObject sObj : records) {
            contactBean = new ContactBean();
            try {
                contactBean.setContact((Contact) sObj);
                writer.processRecord(contactBean);
                syncedList.add((Contact) sObj);
            } catch (Exception e) {
                failedMap.put((Contact) sObj, e.getMessage());
            }
        }
        writer.close();
        outputWriter.close();
        logger.info("Save records to: "+outputPath);
    }

    @Override
    protected void updateStatus() throws ConnectionException {
        records = new SObject[syncedList.size()+failedMap.size()];
        int i = 0;
        for(Contact contact : syncedList) {
            contact.setAddress_String__c(null);
            contact.setInterface_status__c("Synced");
            if(contact.getBirthdate() != null)
                contact.getBirthdate().add(Calendar.HOUR, 25); // ?????
            records[i] = contact;
            i++;
        }
        for(Contact contact : failedMap.keySet()) {
            contact.setAddress_String__c(null);
            contact.setInterface_status__c("Failed");
            contact.setContact_Sync_Failure_Reason__c(failedMap.get(contact));
            if(contact.getBirthdate() != null)
                contact.getBirthdate().add(Calendar.HOUR, 25); // ?????
            records[i] = contact;
            i++;
        }
        CuttingCake cut = new CuttingCake(records);
        while(cut.hasNext()) {
            SaveResult[] saveResults = connection.update(cut.next());
            for(SaveResult saveResult : saveResults) {
                if(!saveResult.isSuccess())
                    logger.warning(saveResult.getId()+" have errors: "+ Arrays.toString(saveResult.getErrors()));
            }
        }
        logger.info("Update records. "+syncedList.size()+" records Synced. "+failedMap.size()+" records Failed");
    }

    @Override
    public String toString() {
        return "ExportContactFromSFDC";
    }
}
