package com.synebo.populate;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.synebo.exception.ParserException;
import com.synebo.exception.SynchronizationException;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by User on 29.02.2016.
 */
public abstract class AbstractPopulate implements Populate {

    protected EnterpriseConnection connection;
    protected Logger logger;
    private int countTry = 0;

    public AbstractPopulate(EnterpriseConnection connection, Logger logger) {
        this.connection = connection;
        this.logger = logger;
    }
    @Override
    public void execute() {
        try {
            logger.info("");
            logger.info("Start "+this);
            loadDataFromSFDC();
            parseFromCSV();
            populate();
            logger.info("Synchronization "+this+" is successful.");
        } catch (ConnectionException e) {
            if (countTry < 5) {
                logger.warning("Problems with the connection. I will try again.");
                countTry++;
                execute();
            } else
                e.printStackTrace();
        }
        catch (Exception e) {
            logger.warning("Synchronization " + this + " is unsuccessful. "+e.getClass().getName()+" Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected abstract void loadDataFromSFDC() throws ConnectionException, SynchronizationException;

    protected abstract void parseFromCSV() throws IOException, ParserException;

    protected abstract void populate() throws ConnectionException;

}
