package com.synebo.exportFromSFDC;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.synebo.exception.SynchronizationException;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by User on 26.02.2016.
 */
public abstract class AbstractExportFromSFDC implements ExportFromSFDC {

    protected EnterpriseConnection connection;
    protected Logger logger;

    public AbstractExportFromSFDC(EnterpriseConnection connection, Logger logger) {
        this.connection = connection;
        this.logger = logger;
    }

    @Override
    public void execute() {
        try {
            logger.info("");
            logger.info("Start "+this);
            loadDataFromSFDC();
            writeCSV();
            updateStatus();
            logger.info("Synchronization " + this + " is successful.");
        } catch (Exception e) {
            logger.warning("Synchronization " + this + " is unsuccessful. Message: " + e.getMessage());
        }
    }

    protected abstract void loadDataFromSFDC() throws ConnectionException;

    protected abstract void writeCSV() throws IOException, SynchronizationException;

    protected abstract void updateStatus() throws ConnectionException;

}
