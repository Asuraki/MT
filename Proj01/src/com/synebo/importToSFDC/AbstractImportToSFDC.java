package com.synebo.importToSFDC;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.synebo.exception.ParserException;
import com.synebo.synchronization.Synchronization;
import com.synebo.exception.SynchronizationException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by User on 26.02.2016.
 */
public abstract class AbstractImportToSFDC implements ImportToSFDC {

    protected EnterpriseConnection connection;
    protected Logger logger;
    protected String filePath;
    private int countTry = 0;

    public AbstractImportToSFDC(EnterpriseConnection connection, Logger logger, String filePath) {
        this.connection = connection;
        this.logger = logger;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            logger.info("");
            logger.info("Start " + this);
            parseFromCSV();
            loadDataToSFDC();
            relocateCSV();
            logger.info("Synchronization " + this + " is successful.");
        } catch (ConnectionException e) {
            if (countTry < 5) {
                logger.warning("Problems with the connection. I will try again.");
                countTry++;
                execute();
            } else
                e.printStackTrace();
        } catch (Exception e) {
            logger.warning("Synchronization " + this + " is unsuccessful. Message: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    protected abstract void parseFromCSV() throws SynchronizationException, IOException, ParserException;

    protected abstract void loadDataToSFDC() throws ConnectionException, SynchronizationException;

    protected void relocateCSV() {
        logger.info("Relocate " + filePath + " to " + Synchronization.SALESFORCE_HISTORY_PATH);
        File file = new File(filePath);
        File relocateTo = new File(Synchronization.SALESFORCE_HISTORY_PATH);
        file.renameTo(new File(relocateTo, file.getName()));
    }

}
