package com.synebo.importToSFDC.components;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Apartment_Type__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.synebo.beans.ApartmentTypeBean;
import com.synebo.exception.ParserException;
import com.synebo.importToSFDC.AbstractImportToSFDC;
import com.synebo.tools.CuttingCake;
import com.synebo.tools.Util;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by User on 26.02.2016.
 */
public class ImportApartmentTypeToSFDC extends AbstractImportToSFDC {

    private List<ApartmentTypeBean> apartmentTypeBeans;

    public ImportApartmentTypeToSFDC(EnterpriseConnection connection, Logger logger, String filePath) {
        super(connection, logger, filePath);
    }

    @Override
    protected void parseFromCSV() throws IOException, ParserException {
        BeanListProcessor<ApartmentTypeBean> rowProcessor = new BeanListProcessor<>(ApartmentTypeBean.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.setRowProcessor(rowProcessor);
        CsvParser parser = new CsvParser(parserSettings);
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        parser.parse(reader);
        reader.close();
        apartmentTypeBeans = rowProcessor.getBeans();
        if(apartmentTypeBeans.size() == 0)
            throw new ParserException(filePath + " is empty or doesn't contain any values.");
        logger.info("Import List of Apartment_Type__c records from external file. Size " + apartmentTypeBeans.size());
    }

    @Override
    protected void loadDataToSFDC() throws ConnectionException {
        SObject[] apartmentTypeList = new SObject[apartmentTypeBeans.size()];
        Apartment_Type__c apartmentType;
        int i = 0;

        for(ApartmentTypeBean apTypeBean : apartmentTypeBeans) {
            apartmentType = new Apartment_Type__c();
            // add field
            apartmentType.setName(apTypeBean.getAppTypeCode());
            apartmentType.setApartment_Type_Description__c(apTypeBean.getAppTypeDes());
            apartmentType.setNumber_of_Rooms__c(apTypeBean.getRoomCount() == null ? null : Double.valueOf(apTypeBean.getRoomCount()));
            apartmentType.setCleaning_Minutes__c(apTypeBean.getCleanInMinutes() == null ? null : Double.valueOf(apTypeBean.getCleanInMinutes()));
            apartmentType.setCenter_Code__c(Util.getLastTwoDigits(filePath));
            apartmentType.setApartment_Type_Priority_ID__c(apTypeBean.getUnique());
            apartmentType.setActive__c(Util.convertStringToBoolean(apTypeBean.getActive()));

            apartmentTypeList[i] = apartmentType;
            i++;
        }
        CuttingCake cut = new CuttingCake(apartmentTypeList);
        while(cut.hasNext()) {
            UpsertResult[] upsertResults = connection.upsert("Apartment_Type_Priority_ID__c", cut.next());
            for(UpsertResult upsertResult : upsertResults) {
                if(!upsertResult.isSuccess())
                    logger.warning(upsertResult.getId()+" have errors: "+ Arrays.toString(upsertResult.getErrors()));
            }
        }
    }

    @Override
    public String toString() {
        return "ImportApartmentTypeToSFDC";
    }


}
