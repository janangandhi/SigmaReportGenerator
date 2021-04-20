package com.scu.srg.application;

import com.scu.srg.reader.TextFileReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ReportGenerator {

    private static final Logger logger = LogManager.getLogger(ReportGenerator.class);

    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String propFilePath = rootPath + "app.properties";

    Properties fileProps;

    public ReportGenerator() {
        loadProperties();
        fileProps.forEach((key, value) -> logger.debug("Key : " + key + ", Value : " + value));
    }

    public void doSomething() {
        TextFileReader reader = new TextFileReader();

        String filename = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        reader.readInput("/Users/janangandhi/IdeaProjects/SigmaReportGenerator/src/main/resources/ReportData.txt");
    }

    private void loadProperties() {
        fileProps = new Properties();
        logger.debug("Reading properties from file " + propFilePath);
        try {
            fileProps.load(new FileInputStream(propFilePath));
        } catch (Exception e) {
            logger.error("Error in loading configuration: " + e.getMessage());
        }
    }
}
