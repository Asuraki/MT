package com.synebo;

import com.synebo.synchronization.Synchronization;

import java.io.*;

public class Main {

    public static String basePath;
    public static boolean isRunImportAPAR;
    public static boolean isRunImportTYPE;
    public static boolean isRunImportPRICE;
    public static boolean isRunExport;
    public static boolean isRunPopulate;
    private static final String ABSOLUTE_PATH = new File("").getAbsolutePath();
    public String t;

    public static void main(String[] args) {

        if (args.length == 0) {
            basePath = ABSOLUTE_PATH;
            setAll();
            System.out.println("Set default base path:\n" + basePath);
            System.out.println("Run all process.");
        }
        if (args.length == 1) {
            if (args[0].contains("\\") || args[0].contains("/")) {
                basePath = args[0];
                setAll();
                System.out.println("Base path set from arguments:\n" + basePath);
                System.out.println("Run all process.");
            } else {
                basePath = ABSOLUTE_PATH;
                System.out.println("Set default base path:\n" + basePath);
                setRunType(args[0]);
            }
        }
        if (args.length == 2) {
            basePath = args[0];
            System.out.println("Base path set from arguments:\n" + basePath);
            setRunType(args[1]);
        }

        try (Synchronization synchronization = new Synchronization()) {
            synchronization.startProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void setRunType(String type) {
        switch (type) {
            case "importAPAR": // done
                isRunImportAPAR = true;
                System.out.println("Run ImportAPAR process.");
                break;
            case "importTYPE": // done
                isRunImportTYPE = true;
                System.out.println("Run ImportTYPE process.");
                break;
            case "importPRICE": // done
                isRunImportPRICE = true;
                System.out.println("Run ImportPRICE process.");
                break;
            case "export": // done (delete header)
                isRunExport = true;
                System.out.println("Run Export process.");
                break;
            case "populate": // done
                isRunPopulate = true;
                System.out.println("Run Populate process.");
                break;
            default:
                setAll();
                System.out.println("Run all process.");
                break;
        }
    }

    private static void setAll() {
        isRunImportAPAR = true;
        isRunImportTYPE = true;
        isRunImportPRICE = true;
        isRunExport = true;
        isRunPopulate = true;
    }

}