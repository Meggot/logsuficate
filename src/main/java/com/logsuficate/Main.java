package com.logsuficate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        /**
         * TODO: WIP
         * This only works for objects - it just detects if there is a field on the object
         * with the annotation then hides the whole thing using the value in the annotation.
         *
         * 71872 [main] DEBUG com.logsuficate.Main - HIDE [ _USER_PASSWORD_ ] ==== DONT [ NonSensitiveField(username=SomeUsername) ]
         *
         * It does not only hide field names - it will not work with get() methods and will not work if you are appending
         * inside the format string in log ie: log.debug("some log: " + logme + " is there <--- ");
         *
         * It also does not continue logging in the same log level as provided. a big bummer.
         *
         */
        logger.info("HIDE [ {} ] ==== DONT [ {} ]", new SensitiveField(), new NonSensitiveField());
    }
}
