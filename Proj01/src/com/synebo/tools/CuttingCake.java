package com.synebo.tools;

import com.sforce.soap.enterprise.sobject.SObject;

import java.util.Arrays;

/**
 * Created by User on 29.02.2016.
 */
public class CuttingCake {

    private SObject[] records;
    private SObject[] tmp;
    private int count = 0;

    public CuttingCake(SObject[] records) {
        this.records = records;
    }

    public boolean hasNext() {
        return records.length > count;
    }

    public SObject[] next() {
        int i = 199;
        int a = count+i;
        if(count + 199 > records.length)
            a = records.length;
        if(records.length < 199)
            a = records.length;
        tmp = Arrays.copyOfRange(records, count, a);
        count+=199;
        return tmp;
    }
}
