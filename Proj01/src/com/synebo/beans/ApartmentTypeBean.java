package com.synebo.beans;

import com.univocity.parsers.annotations.Parsed;

/**
 * Created by User on 26.02.2016.
 */
public class ApartmentTypeBean {

    @Parsed(field = "AppTypeCode")
    private String AppTypeCode;

    @Parsed(field = "AppTypeDes")
    private String AppTypeDes;

    @Parsed(field = "RoomCount")
    private String RoomCount;

    @Parsed(field = "CleanInMinutes")
    private String CleanInMinutes;

    @Parsed(field = "Unique")
    private String Unique;

    @Parsed(field = "Active")
    private String Active;

    public String getAppTypeCode() {
        return AppTypeCode;
    }

    public String getAppTypeDes() {
        return AppTypeDes;
    }

    public String getRoomCount() {
        return RoomCount;
    }

    public String getCleanInMinutes() {
        return CleanInMinutes;
    }

    public String getUnique() {
        return Unique;
    }

    public String getActive() {
        return Active;
    }
}
