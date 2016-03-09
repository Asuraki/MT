package com.synebo.synchronization;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.synebo.Main;
import com.synebo.exception.ParserException;
import com.synebo.exception.SynchronizationException;
import com.synebo.populate.components.PopulateZipCode;
import com.synebo.tools.SingleLineFormatter;
import com.synebo.beans.SettingsBean;
import com.synebo.exportFromSFDC.components.ExportContactFromSFDC;
import com.synebo.importToSFDC.components.ImportApartmentToSFDC;
import com.synebo.importToSFDC.components.ImportApartmentTypePricingToSFDC;
import com.synebo.importToSFDC.components.ImportApartmentTypeToSFDC;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Created by User on 26.02.2016.
 */
public class Synchronization implements AutoCloseable {

    private Logger logger;
    private EnterpriseConnection connection;

    private int countTry = 0;

    public static final String DATE_MARK = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    public static final String SF_WAITING_FOR_SYNC_PATH = Main.basePath + "\\SF_Waiting_For_Sync";
    public static final String SALESFORCE_HISTORY_PATH = Main.basePath + "\\Salesforce_History";
    public static final String PRIORITY_WAITING_FOR_SYNC_PATH = Main.basePath + "\\Priority_Waiting_for_sync";
    public static final String LOGS_PATH = Main.basePath+"\\logs";
    public static final String CONFIG_PATH = Main.basePath + "\\config\\config.csv";
    public static final String ZIP_CODE_PATH = Main.basePath + "\\Zip_Code\\zipcodes_f.csv";

    public Synchronization() throws SynchronizationException {
        logger = setUpLogger();
        connect();
    }

    public void startProcess() {
        if(Main.isRunImportAPAR)
            for (String filePath : loadingFilePath("APAR"))
                new ImportApartmentToSFDC(connection, logger, filePath).execute();

        if(Main.isRunImportTYPE)
            for (String filePath : loadingFilePath("TYPE"))
                new ImportApartmentTypeToSFDC(connection, logger, filePath).execute();

        if(Main.isRunImportPRICE)
            for (String filePath : loadingFilePath("PRICE"))
                new ImportApartmentTypePricingToSFDC(connection, logger, filePath).execute();

        if(Main.isRunExport)
            new ExportContactFromSFDC(connection, logger).execute();

        if(Main.isRunPopulate)
            new PopulateZipCode(connection, logger).execute();

        System.err.flush();
        System.out.println("\nSynchronization finished.\n\nSee "+DATE_MARK+".log in your logs folder:\n"+LOGS_PATH);
        logger.info("");
    }

    @Override
    public void close() throws Exception {
        boolean isLogout = false;
        try {
            connection.logout();
            isLogout = true;
            logger.info("Logged out.");
        } catch (Exception e) {
            throw new Exception("Can not log out.", e);
        } finally {
            if(!isLogout)
                logger.warning("Can not log out.");
        }
    }

    private Logger setUpLogger() throws SynchronizationException {
        Logger logger = Logger.getLogger("Meditowers");
        File logsDir = new File(LOGS_PATH);
        logsDir.mkdir();
        try {
            FileHandler fh = new FileHandler(logsDir.getAbsolutePath() + "\\" + DATE_MARK + ".log");
            fh.setFormatter(new SingleLineFormatter());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            logger.info("Start logging.");
        } catch (Exception e) {
            throw new SynchronizationException("Can not start logging. Stop working.", e);
        }
        return logger;
    }

    private void connect() throws SynchronizationException {
        BeanListProcessor<SettingsBean> rowProcessor = new BeanListProcessor<>(SettingsBean.class);
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.setRowProcessor(rowProcessor);
        CsvParser parser = new CsvParser(parserSettings);
        SettingsBean sett;
        try (Reader r = new FileReader(CONFIG_PATH)) {
            parser.parse(r);
            List<SettingsBean> setts = rowProcessor.getBeans();
            if(setts.size() != 0)
                sett = setts.get(0);
            else
                throw new ParserException("config.csv is empty or doesn't contain any values.");
        } catch (Exception e) {
            logger.severe("Can not parse settings: " + e.getMessage());
            logger.severe("Can not establish connection to Salesforce. Stop working.");
            throw new SynchronizationException("Can not establish connection to Salesforce. Stop working.", e);
        }

        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(sett.getLogin());
        config.setPassword(sett.getPassword() + sett.getToken());
        try {
            connection = Connector.newConnection(config);
            logger.info("Successfully connected to Salesforce.");
            logger.info("Auth EndPoint:" + config.getAuthEndpoint());
            logger.info("Service EndPoint:" + config.getServiceEndpoint());
            logger.info("Username: " + config.getUsername());
            logger.info("SessionId: " + config.getSessionId());
        } catch (ConnectionException e) {
            logger.severe("Connection refused: " + e.getMessage());
            if (countTry > 5)
                throw new SynchronizationException("Can not establish connection to Salesforce. Stop working.", e);
            logger.info("I will try again.");
            countTry++;
            connect();
        }
    }

    private List<String> loadingFilePath(String prefix) {
        List<String> result = new ArrayList<>();
        File dir = new File(SF_WAITING_FOR_SYNC_PATH);
        File[] files = dir.listFiles();
        if (files != null) {
            if (files.length == 0) {
                logger.info("");
                logger.warning(SF_WAITING_FOR_SYNC_PATH + " is empty.");
                return result;
            } else {
                List<String> tmp = new ArrayList<>();
                for (File file : files)
                    tmp.add(file.getAbsolutePath());

                for (String filePath : tmp)
                    if (filePath.contains(prefix))
                        result.add(filePath);
            }
        }

        if (result.size() == 0) {
            logger.info("");
            logger.warning(SF_WAITING_FOR_SYNC_PATH + " does not contain " + prefix);
        }

        return result;
    }

}
