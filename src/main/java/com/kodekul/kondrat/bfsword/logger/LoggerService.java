package com.kodekul.kondrat.bfsword.logger;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements LoggerInterface {

    private static final Logger LOGGER = LogManager.getLogger(String.class);
    private Marker marker = MarkerManager.getMarker("START");

    @Override
    public void logError(LogerInfo message) {
        LOGGER.error(marker,message);
    }

    @Override
    public void logInfo(LogerInfo message) {
        LOGGER.info(marker,message);
    }
}
