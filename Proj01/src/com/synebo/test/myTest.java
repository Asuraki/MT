package com.synebo.test;

import com.sforce.soap.enterprise.*;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.synebo.tools.CuttingCake;
import com.synebo.tools.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 25.02.2016.
 */
public class myTest {



    public static void main(String[] args) {

/*
        try {
            EnterpriseConnection connection = Connector.newConnection(username, password);

            GetUserInfoResult userInfo = connection.getUserInfo();

            Account acc = new Account();
            acc.setName("Name23");
            acc.setDescription("myTest23");

            SObject[] objs = new SObject[1];
            objs[0] = acc;

            SaveResult[] saveResults = connection.create(objs);

            System.out.println(saveResults);


            //clean data
            if(true) {
                QueryResult queryResult = connection.query("SELECT Id, Name FROM Account WHERE Name = 'Name23'");
                SObject[] records = queryResult.getRecords();
                System.out.println(records.length);
                if(records.length > 0) {
                    String[] ids = new String[records.length];
                    int i = 0;
                    for(SObject obj : records) {
                        ids[i] = obj.getId();
                        i++;
                    }
                    connection.delete(ids);
                }
            }

        } catch (ConnectionException e) {
            e.printStackTrace();
        }

        File file = new File("C:\\from\\test.txt");
        File to = new File("C:\\to");
        boolean b = file.renameTo(new File(to, file.getName()));

        File file = new File("C:\\from\\test.txt");
        File relocateTo = new File("C:\\"+"to");
        file.renameTo(new File(relocateTo, file.getName()));
        */

        //System.out.println("0034E000001fztG888".substring(0, 15));
//        Contact c1 = new Contact();
//        c1.setName("Name1");
//
//        Contact c2 = new Contact();
//        c2.setName("Name2");
//
//        Map<Contact, String> failedMap = new HashMap<>();
//        failedMap.put(c1, "messege1");
//        failedMap.put(c2, "messege2");
//
//        System.out.println(failedMap.get(c2));
//        int set = 523;
//        SObject[] mas = new SObject[set];
//        for(int i = 0; i<set; i++) {
//            mas[i]= new Account();
//        }
//        System.out.println(mas.length);
//
//        CuttingCake c = new CuttingCake(mas);
//        int as = 0;
//        while (c.hasNext()) {
//
//            as+=c.next().length;
//            System.out.println("ss");
//        }
//        System.out.println(as);

        //DDMMYY
//        Calendar calendar = Util.convertStringToDate("010116");
//        //System.out.println(calendar.getTime());
//
//        String s = Util.convertDateToString(calendar);
//        System.out.println(s);
        //System.out.println(Boolean.parseBoolean(null));
        //Calendar calendar = Util.convertStringToDate("24/03/16");
        //System.out.println(calendar.getTime());
        //System.out.println(Integer.valueOf("00040"));
//        int f = 0;
//        int g = 1;
//        for(int i = 0; i <=15; i++) {
//            System.out.println(f);
//            f = f+g;
//            g = f-g;
//        }

//        double t = 9.0;
//        while (Math.abs(t - 9.0/t) > .001) {
//            t = (9.0/t + t) /2.0;
//            System.out.printf("%.5f\n", t);
//        }
//        int sum = 0;
//        for( int i = 1; i < 1000; i++) {
//            for (int j = 0; j < i; j++)
//                sum++;
//        }
//        System.out.println('s'+4);
    }
}
