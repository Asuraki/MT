package com.synebo.importToSFDC.components;

import com.sforce.soap.enterprise.EnterpriseConnection;

import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Apartment_Type__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.synebo.beans.ApartmentTypePricingBean;
import com.synebo.exception.ParserException;
import com.synebo.importToSFDC.AbstractImportToSFDC;
import com.synebo.exception.SynchronizationException;
import com.synebo.SObject.Apartment_Type_Pricing__c;
import com.synebo.tools.CuttingCake;
import com.synebo.tools.Util;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by User on 26.02.2016.
 */
public class ImportApartmentTypePricingToSFDC extends AbstractImportToSFDC {

    private List<ApartmentTypePricingBean> apartmentTypePricingBeans;

    public ImportApartmentTypePricingToSFDC(EnterpriseConnection connection, Logger logger, String filePath) {
        super(connection, logger, filePath);
    }

    @Override
    protected void parseFromCSV() throws IOException, ParserException {
        BeanListProcessor<ApartmentTypePricingBean> rowProcessor = new BeanListProcessor<>(ApartmentTypePricingBean.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.setRowProcessor(rowProcessor);
        CsvParser parser = new CsvParser(parserSettings);
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        parser.parse(reader);
        reader.close();
        apartmentTypePricingBeans = rowProcessor.getBeans();
        if(apartmentTypePricingBeans.size() == 0)
            throw new ParserException(filePath + " is empty or doesn't contain any values.");
        logger.info("Import List of Apartment_Type_Pricing__c records from external file. Size " +
                apartmentTypePricingBeans.size());
    }

    @Override
    protected void loadDataToSFDC() throws ConnectionException, SynchronizationException {
        SObject[] apartmentTypePricingList = new SObject[apartmentTypePricingBeans.size()];
        Map<String, Apartment_Type_Pricing__c> forLookUp = new HashMap<>();
        Apartment_Type_Pricing__c apartmentTypePricing;
        int i = 0;

        for(ApartmentTypePricingBean apTypeBean : apartmentTypePricingBeans) {
            apartmentTypePricing = new Apartment_Type_Pricing__c();

            // add field
//            apartmentTypePricing.setApartment_Type_Priority_ID__c(apTypeBean.getUnique());
//            apartmentTypePricing.setActive__c(Util.convertStringToBoolean(apTypeBean.getACTIVEFLAG()));
//            apartmentTypePricing.setApartment_Type_Info__c(apTypeBean.getGAAP_APPTYPEDES());
//            apartmentTypePricing.setPrice_List_Code__c(apTypeBean.getMODELNAME());
//            apartmentTypePricing.setPrice_List_Opening_Date__c(Util.convertStringToDate(apTypeBean.getESTI_OPENDATE()));
//            apartmentTypePricing.setDeposit_Price_without_VAT__c(apTypeBean.getPRICE() == null ? null : Double.valueOf(apTypeBean.getPRICE()));
//            apartmentTypePricing.setDeposit_Price_with_VAT__c(apTypeBean.getGAAP_VATPRICE() == null ? null : Double.valueOf(apTypeBean.getGAAP_VATPRICE()));
//            apartmentTypePricing.setHolding_Price_without_VAT__c(apTypeBean.getHOLDING_Price() == null ? null : Double.valueOf(apTypeBean.getHOLDING_Price()));
//            apartmentTypePricing.setHolding_Price_with_VAT__c(apTypeBean.getHolding_VATPRICE() == null ? null : Double.valueOf(apTypeBean.getHolding_VATPRICE()));
//
//            forLookUp.put(apTypeBean.getUnique(), apartmentTypePricing);

            apartmentTypePricing.setApartment_Type_Priority_ID__c(apTypeBean.getAppTypeUnique());
            apartmentTypePricing.setHouse_ModelName__c(apTypeBean.getHouse_ModelName());
            apartmentTypePricing.setPricing_Priority_ID__c(apTypeBean.getPricingUnique());
            apartmentTypePricing.setApartment_Type_Info__c(apTypeBean.getAppTypeCode());
            apartmentTypePricing.setAppTypeDes__c(apTypeBean.getAppTypeDes());
            apartmentTypePricing.setPriceNo__c(apTypeBean.getPriceNo());
            apartmentTypePricing.setIndexDes__c(apTypeBean.getIndexDes());
            apartmentTypePricing.setCurrCode__c(apTypeBean.getCurrCode());
            apartmentTypePricing.setDate__c(Util.convertStringToDate(apTypeBean.getDate()));
            apartmentTypePricing.setModelName__c(apTypeBean.getModelName());
            apartmentTypePricing.setModelDes__c(apTypeBean.getModelDes());
            apartmentTypePricing.setActive__c(Util.convertStringToBoolean(apTypeBean.getActive()));
            apartmentTypePricing.setNqtx_gcxim__c(apTypeBean.getNqtx_gcxim());
            apartmentTypePricing.setRate__c(apTypeBean.getRate());
            apartmentTypePricing.setAppQnt__c(apTypeBean.getAppQnt());
            apartmentTypePricing.setOpenDate__c(Util.convertStringToDate(apTypeBean.getOpenDate()));
            apartmentTypePricing.setPartName__c(apTypeBean.getPartName());
            apartmentTypePricing.setQuant__c(apTypeBean.getQuant());
            apartmentTypePricing.setVAT_Price__c(apTypeBean.getVatPrice() == null ? null : Double.valueOf(apTypeBean.getVatPrice()));
            apartmentTypePricing.setPrice__c(apTypeBean.getPrice() == null ? null : Double.valueOf(apTypeBean.getPrice()));
            apartmentTypePricing.setInactive__c(Util.convertStringToBoolean(apTypeBean.getInactive()));
            apartmentTypePricing.setDeposit__c(Util.convertStringToBoolean(apTypeBean.getDeposit()));
            apartmentTypePricing.setMaintenanceFees__c(Util.convertStringToBoolean(apTypeBean.getMaintenanceFees()));

            forLookUp.put(apTypeBean.getAppTypeUnique(), apartmentTypePricing);

            apartmentTypePricingList[i] = apartmentTypePricing;
            i++;
        }

        QueryResult queryResult = connection.query("SELECT " +
                                                        "Id, " +
                                                        "Apartment_Type_Priority_ID__c " +
                                                    "FROM Apartment_Type__c");

        SObject[] records = queryResult.getRecords();
        for(SObject sObj : records) {
            Apartment_Type__c appType = (Apartment_Type__c) sObj;

            if(forLookUp.containsKey(appType.getApartment_Type_Priority_ID__c())) {
                Apartment_Type_Pricing__c apartmentTypePricing__c = forLookUp.get(appType.getApartment_Type_Priority_ID__c());
                apartmentTypePricing__c.setApartment_Type__c(appType.getId());
            }
        }

        CuttingCake cut = new CuttingCake(apartmentTypePricingList);
        while(cut.hasNext()) {
            UpsertResult[] upsertResults = connection.upsert("Pricing_Priority_ID__c", cut.next());
            for(UpsertResult upsertResult : upsertResults) {
                if(!upsertResult.isSuccess())
                    logger.warning(upsertResult.getId()+" have errors: "+ Arrays.toString(upsertResult.getErrors()));
            }
        }
    }

    @Override
    public String toString() {
        return "ImportApartmentTypePricingToSFDC";
    }
}
