package com.synebo.beans;

import com.univocity.parsers.annotations.Parsed;

/**
 * Created by User on 26.02.2016.
 */
public class ApartmentTypePricingBean {

//    @Parsed(field = "Unique")
//    private String Unique;
//
//    @Parsed(field = "ACTIVEFLAG")
//    private String ACTIVEFLAG;
//
//    @Parsed(field = "GAAP_APPTYPEDES")
//    private String GAAP_APPTYPEDES;
//
//    @Parsed(field = "MODELNAME")
//    private String MODELNAME;
//
//    @Parsed(field = "ESTI_OPENDATE")
//    private String ESTI_OPENDATE;
//
//    @Parsed(field = "PRICE")
//    private String PRICE;
//
//    @Parsed(field = "GAAP_VATPRICE")
//    private String GAAP_VATPRICE;
//
//    @Parsed(field = "HOLDING_Price")
//    private String HOLDING_Price;
//
//    @Parsed(field = "Holding_VATPRICE")
//    private String Holding_VATPRICE;
//
//    public String getUnique() {
//        return Unique;
//    }
//
//    public String getACTIVEFLAG() {
//        return ACTIVEFLAG;
//    }
//
//    public String getGAAP_APPTYPEDES() {
//        return GAAP_APPTYPEDES;
//    }
//
//    public String getMODELNAME() {
//        return MODELNAME;
//    }
//
//    public String getESTI_OPENDATE() {
//        return ESTI_OPENDATE;
//    }
//
//    public String getPRICE() {
//        return PRICE;
//    }
//
//    public String getGAAP_VATPRICE() {
//        return GAAP_VATPRICE;
//    }
//
//    public String getHOLDING_Price() {
//        return HOLDING_Price;
//    }
//
//    public String getHolding_VATPRICE() {
//        return Holding_VATPRICE;
//    }

    @Parsed(field = "AppTypeUnique")
    private String AppTypeUnique;

    @Parsed(field = "House_ModelName")
    private String House_ModelName;

    @Parsed(field = "PricingUnique")
    private String PricingUnique;

    @Parsed(field = "AppTypeCode")
    private String AppTypeCode;

    @Parsed(field = "AppTypeDes")
    private String AppTypeDes;

    @Parsed(field = "PriceNo")
    private String PriceNo;

    @Parsed(field = "IndexDes")
    private String IndexDes;

    @Parsed(field = "CurrCode")
    private String CurrCode;

    @Parsed(field = "Date")
    private String Date;

    @Parsed(field = "ModelName")
    private String ModelName;

    @Parsed(field = "ModelDes")
    private String ModelDes;

    @Parsed(field = "Active")
    private String Active;

    @Parsed(field = "RoomsNum")
    private String RoomsNum;

    @Parsed(field = "Rate")
    private String Rate;

    @Parsed(field = "AppQnt")
    private String AppQnt;

    @Parsed(field = "OpenDate")
    private String OpenDate;

    @Parsed(field = "PartName")
    private String PartName;

    @Parsed(field = "Quant")
    private String Quant;

    @Parsed(field = "VatPrice")
    private String VatPrice;

    @Parsed(field = "Price")
    private String Price;

    @Parsed(field = "Inactive")
    private String Inactive;

    @Parsed(field = "Deposit")
    private String Deposit;

    @Parsed(field = "MaintenanceFees")
    private String MaintenanceFees;

    public String getHouse_ModelName() {
        return House_ModelName;
    }

    public String getPricingUnique() {
        return PricingUnique;
    }

    public String getAppTypeCode() {
        return AppTypeCode;
    }

    public String getAppTypeDes() {
        return AppTypeDes;
    }

    public String getPriceNo() {
        return PriceNo;
    }

    public String getIndexDes() {
        return IndexDes;
    }

    public String getCurrCode() {
        return CurrCode;
    }

    public String getDate() {
        return Date;
    }

    public String getModelName() {
        return ModelName;
    }

    public String getModelDes() {
        return ModelDes;
    }

    public String getActive() {
        return Active;
    }

    public String getRoomsNum() {
        return RoomsNum;
    }

    public String getRate() {
        return Rate;
    }

    public String getAppQnt() {
        return AppQnt;
    }

    public String getOpenDate() {
        return OpenDate;
    }

    public String getPartName() {
        return PartName;
    }

    public String getQuant() {
        return Quant;
    }

    public String getVatPrice() {
        return VatPrice;
    }

    public String getPrice() {
        return Price;
    }

    public String getInactive() {
        return Inactive;
    }

    public String getDeposit() {
        return Deposit;
    }

    public String getMaintenanceFees() {
        return MaintenanceFees;
    }

    public String getAppTypeUnique() {
        return AppTypeUnique;
    }
}
