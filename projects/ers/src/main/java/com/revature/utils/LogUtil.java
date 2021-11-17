package com.revature.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    private static Logger log = LogManager.getRootLogger();
    public static void descriptiveError(String description){
        log.error(description);
    }
}