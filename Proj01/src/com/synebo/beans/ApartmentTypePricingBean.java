package com.synebo.beans;

import com.univocity.parsers.annotations.Parsed;

/**
 * Created by User on 26.02.2016.
 */
public class ApartmentTypePricingBean {

    @Parsed(field = "Unique")
    private String Unique;

    @Parsed(field = "ACTIVEFLAG")
    private String ACTIVEFLAG;

    @Parsed(field = "GAAP_APPTYPEDES")
    private String GAAP_APPTYPEDES;

    @Parsed(field = "MODELNAME")
    private String MODELNAME;

    @Parsed(field = "ESTI_OPENDATE")
    private String ESTI_OPENDATE;

    @Parsed(field = "PRICE")
    private String PRICE;

    @Parsed(field = "GAAP_VATPRICE")
    private String GAAP_VATPRICE;

    @Parsed(field = "HOLDING_Price")
    private String HOLDING_Price;

    @Parsed(field = "Holding_VATPRICE")
    private String Holding_VATPRICE;

    public String getUnique() {
        return Unique;
    }

    public String getACTIVEFLAG() {
        return ACTIVEFLAG;
    }

    public String getGAAP_APPTYPEDES() {
        return GAAP_APPTYPEDES;
    }

    public String getMODELNAME() {
        return MODELNAME;
    }

    public String getESTI_OPENDATE() {
        return ESTI_OPENDATE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public String getGAAP_VATPRICE() {
        return GAAP_VATPRICE;
    }

    public String getHOLDING_Price() {
        return HOLDING_Price;
    }

    public String getHolding_VATPRICE() {
        return Holding_VATPRICE;
    }
}
