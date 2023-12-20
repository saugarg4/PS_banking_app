package org.example;

import java.util.logging.*;

public class Logging {
    private final LogManager lgmngr ;
    private final Logger log;
    private static Logging instance;
    private Logging(){
        lgmngr =  LogManager.getLogManager();
        log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    public static Logging getInstance() {
        // create the instance if it doesn't exist
        if (instance == null) {
            instance = new Logging();
        }
        return instance;
    }
    public Logger getLog(){
        return log;
    }
}
