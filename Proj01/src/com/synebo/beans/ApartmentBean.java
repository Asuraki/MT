package com.synebo.beans;

import com.univocity.parsers.annotations.Parsed;

/**
 * Created by User on 25.02.2016.
 */
public class ApartmentBean {
//Housecode,Housedes
    @Parsed(field = "Appname")//11
    private String Appname;

    @Parsed(field = "Appdes")//11
    private String Appdes;

    @Parsed(field = "AppTypeUnique")//11
    private String AppTypeUnique;

    @Parsed(field = "Grabstatusdes")//11
    private String Grabstatusdes;

    @Parsed(field = "Unique")//11
    private String Unique;

    @Parsed(field = "Appstatusname")//11
    private String Appstatusname;

    @Parsed(field = "Appstatusdes")//11
    private String Appstatusdes;

    @Parsed(field = "Gush")//11
    private String Gush;

    @Parsed(field = "Apptypecode")//11
    private String Apptypecode;

    @Parsed(field = "Apptypedes")//11
    private String Apptypedes;

    @Parsed(field = "Housecode")//--
    private String Housecode;

    @Parsed(field = "Housedes")//--
    private String Housedes;

    @Parsed(field = "Roomsnum")//11
    private String Roomsnum;

    @Parsed(field = "Building")//11
    private String Building;

    @Parsed(field = "Slav")//11
    private String Slav;

    @Parsed(field = "Area")//11
    private String Area;

    @Parsed(field = "Hazercode")//11
    private String Hazercode;

    @Parsed(field = "Hazerdes")//11
    private String Hazerdes;

    @Parsed(field = "Cleanminit")//11
    private String Cleanminit;

    @Parsed(field = "Remarks")//11
    private String Remarks;

    @Parsed(field = "Helka")//11
    private String Helka;

    @Parsed(field = "Floorname")//11
    private String Floorname;

    @Parsed(field = "Floordes")//11
    private String Floordes;

    @Parsed(field = "Porcharea")//11
    private String Porcharea;

    @Parsed(field = "Elevatornearneesdes")//11
    private String Elevatornearneesdes;

    @Parsed(field = "Directionsdes")//11
    private String Directionsdes;

    @Parsed(field = "Evacuationdate")//11
    private String Evacuationdate;

    @Parsed(field = "Renovationenddate")//11
    private String Renovationenddate;

    @Parsed(field = "Subtypecode")//11
    private String Subtypecode;

    @Parsed(field = "Subtypedes")//11
    private String Subtypedes;

    @Parsed(field = "Toarmourdate")//11
    private String Toarmourdate;

    @Parsed(field = "Custname")//11
    private String Custname;

    @Parsed(field = "Custdes")//11
    private String Custdes;

    @Parsed(field = "Enrtydate")//11
    private String Enrtydate;

    @Parsed(field = "Improvementstatcode")//11
    private String Improvementstatcode;

    @Parsed(field = "Improvementstatdes")//11
    private String Improvementstatdes;

    //@Parsed(field = "APARCODE")11
    //private String APARCODE;


    public String getAppname() {
        return Appname;
    }

    public String getAppdes() {
        return Appdes;
    }

    public String getGrabstatusdes() {
        return Grabstatusdes;
    }

    public String getAppTypeUnique() {
        return AppTypeUnique;
    }

    public String getUnique() {
        return Unique;
    }

    public String getAppstatusname() {
        return Appstatusname;
    }

    public String getAppstatusdes() {
        return Appstatusdes;
    }

    public String getGush() {
        return Gush;
    }

    public String getApptypecode() {
        return Apptypecode;
    }

    public String getApptypedes() {
        return Apptypedes;
    }

    public String getHousecode() {
        return Housecode;
    }

    public String getHousedes() {
        return Housedes;
    }

    public String getRoomsnum() {
        return Roomsnum;
    }

    public String getBuilding() {
        return Building;
    }

    public String getSlav() {
        return Slav;
    }

    public String getArea() {
        return Area;
    }

    public String getHazercode() {
        return Hazercode;
    }

    public String getHazerdes() {
        return Hazerdes;
    }

    public String getCleanminit() {
        return Cleanminit;
    }

    public String getRemarks() {
        return Remarks;
    }

    public String getHelka() {
        return Helka;
    }

    public String getFloorname() {
        return Floorname;
    }

    public String getFloordes() {
        return Floordes;
    }

    public String getPorcharea() {
        return Porcharea;
    }

    public String getElevatornearneesdes() {
        return Elevatornearneesdes;
    }

    public String getDirectionsdes() {
        return Directionsdes;
    }

    public String getEvacuationdate() {
        return Evacuationdate;
    }

    public String getRenovationenddate() {
        return Renovationenddate;
    }

    public String getSubtypecode() {
        return Subtypecode;
    }

    public String getSubtypedes() {
        return Subtypedes;
    }

    public String getToarmourdate() {
        return Toarmourdate;
    }

    public String getCustname() {
        return Custname;
    }

    public String getCustdes() {
        return Custdes;
    }

    public String getEnrtydate() {
        return Enrtydate;
    }

    public String getImprovementstatcode() {
        return Improvementstatcode;
    }

    public String getImprovementstatdes() {
        return Improvementstatdes;
    }
}


