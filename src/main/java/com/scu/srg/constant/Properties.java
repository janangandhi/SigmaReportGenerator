package com.scu.srg.constant;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;

public final class Properties {

    private static final Logger logger = LogManager.getLogger(Properties.class);

    private static final Properties properties = new Properties();

    private final java.util.Properties fileProps;

    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private Properties() {
        String propFilePath = rootPath + "app.properties";
        fileProps = new java.util.Properties();
        logger.debug("Reading properties from file " + propFilePath);
        try {
            fileProps.load(new FileInputStream(propFilePath));
        } catch (Exception e) {
            logger.error("Error in loading configuration: " + e.getMessage());
        }
    }

    public static Properties getInstance() {
        return properties;
    }

    public String getProperty(String key) {
        return fileProps.getProperty(key);
    }

    public String getRootPath() {
        return rootPath;
    }
}
