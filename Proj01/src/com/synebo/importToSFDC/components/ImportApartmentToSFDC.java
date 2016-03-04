package com.synebo.importToSFDC.components;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Apartment_Type__c;
import com.sforce.soap.enterprise.sobject.Apartment__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.synebo.exception.ParserException;
import com.synebo.beans.ApartmentBean;
import com.synebo.importToSFDC.AbstractImportToSFDC;
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
 * Created by User on 25.02.2016.
 */
public class ImportApartmentToSFDC extends AbstractImportToSFDC {

    private List<ApartmentBean> apartmentBeans;

    public ImportApartmentToSFDC(EnterpriseConnection connection, Logger logger, String filePath) {
        super(connection, logger, filePath);
    }

    @Override
    protected void parseFromCSV() throws IOException, ParserException {
        BeanListProcessor<ApartmentBean> rowProcessor = new BeanListProcessor<>(ApartmentBean.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.setRowProcessor(rowProcessor);
        CsvParser parser = new CsvParser(parserSettings);
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        parser.parse(reader);
        reader.close();
        apartmentBeans = rowProcessor.getBeans();
        if(apartmentBeans.size() == 0)
            throw new ParserException(filePath + " is empty or doesn't contain any values.");
        logger.info("Import List of Apartment__c  records from external file. Size " + apartmentBeans.size());
    }

    @Override
    protected void loadDataToSFDC() throws ConnectionException {
        SObject[] apartmentList = new SObject[apartmentBeans.size()];
        Map<String, Apartment__c> forLookUp = new HashMap<>();
        Apartment__c apartment;
        int i = 0;

        for(ApartmentBean apBean : apartmentBeans) {
            apartment = new Apartment__c();

            // add field
            apartment.setName(apBean.getAppname());
            apartment.setApartment_Info__c(apBean.getAppdes());
            apartment.setApartment_Status__c(apBean.getGrabstatusdes());
            apartment.setApartment_Type_Priority_ID__c(apBean.getUnique()); // ?? external id
            forLookUp.put(apBean.getUnique(), apartment); // lookup
            apartment.setApartment_Priority_ID__c(apBean.getUnique()); // ?? external id
            apartment.setCenter_Code__c(Util.getLastTwoDigits(filePath));
            apartment.setOccupation__c(apBean.getAppstatusname());
            apartment.setOccupation_Info__c(apBean.getAppstatusdes());
            apartment.setBlock__c(apBean.getGush());
            //apartment.set???(apBean.getAPPTYPECODE()); tbd
            apartment.setApartment_Type_Info__c(apBean.getApptypedes());
            //apartment.setHouse_Code__c(apBean.getHousecode());
            //apartment.setHouse_Info__c(apBean.getHousedes());
            apartment.setRooms__c(apBean.getRoomsnum() == null ? null : Double.valueOf(apBean.getRoomsnum()));
            apartment.setBuilding__c(apBean.getBuilding());
            apartment.setStage__c(apBean.getSlav());
            apartment.setArea__c(apBean.getArea() == null ? null : Double.valueOf(apBean.getArea()));
            apartment.setYard_Balcony__c(apBean.getHazercode());
            //apartment.set???(apBean.getHAZERDES());
            apartment.setCleaning_stint_in_minutes__c(apBean.getCleanminit() == null ? null : Double.valueOf(apBean.getCleanminit()));
            apartment.setApartment_Comments__c(apBean.getRemarks());
            apartment.setPlot__c(apBean.getHelka());
            apartment.setFloor__c(apBean.getFloorname());
            apartment.setFloor_Info__c(apBean.getFloordes());
            apartment.setBalcony_Area__c(apBean.getPorcharea());
            apartment.setElevator_Proximity__c(apBean.getElevatornearneesdes());
            apartment.setAir_Ways__c(apBean.getDirectionsdes());
            apartment.setCheck_out__c(Util.convertStringToDate(apBean.getEvacuationdate()));
            apartment.setRenovation_Completion_Date__c(Util.convertStringToDate(apBean.getRenovationenddate()));
            apartment.setSubtype_Code__c(apBean.getSubtypecode());
            apartment.setSubtype__c(apBean.getSubtypedes());
            apartment.setReserved_Until__c(Util.convertStringToDate(apBean.getToarmourdate()));
            apartment.setCustomer_Number__c(apBean.getCustname());
            apartment.setCustomer_Name__c(apBean.getCustdes());
            apartment.setCheck_In__c(Util.convertStringToDate(apBean.getEnrtydate()));
            apartment.setRenovation_Status_Code__c(apBean.getImprovementstatcode());
            apartment.setRenovation_Status__c(apBean.getImprovementstatdes());

            apartmentList[i] = apartment;
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
                Apartment__c apartment__c = forLookUp.get(appType.getApartment_Type_Priority_ID__c());
                apartment__c.setApartment_Type__c(appType.getId());
            }
        }

        CuttingCake cut = new CuttingCake(apartmentList);
        while(cut.hasNext()) {
            UpsertResult[] upsertResults = connection.upsert("Apartment_Priority_ID__c", cut.next());
            for(UpsertResult upsertResult : upsertResults) {
                if(!upsertResult.isSuccess())
                    logger.warning(upsertResult.getId()+" have errors: "+ Arrays.toString(upsertResult.getErrors()));
            }
        }
    }

    @Override
    public String toString() {
        return "ImportApartmentToSFDC";
    }
}
