package com.scu.srg.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BusinessException extends Exception {

    private static final Logger logger = LogManager.getLogger(BusinessException.class);

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        logger.error("Business Exception occured: "+ message);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
