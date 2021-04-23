package com.scu.srg.application;

import com.scu.srg.model.ReportData;
import com.scu.srg.model.TextRow;
import com.scu.srg.processor.text.TextFileDataMapper;
import com.scu.srg.processor.text.TextFileDataProcessor;
import com.scu.srg.processor.text.TextFileProcessor;
import com.scu.srg.reader.text.TextFileReader;
import com.scu.srg.writer.text.TextFileWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class ReportGenerator {

    private static final Logger logger = LogManager.getLogger(ReportGenerator.class);

    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String propFilePath = rootPath + "app.properties";

    Properties fileProps;

    public ReportGenerator() {
        loadProperties();
    }

    public void doSomething() {
        TextFileReader reader = new TextFileReader();
        TextFileProcessor processor = new TextFileProcessor(new TextFileDataProcessor(), new TextFileDataMapper());
        TextFileWriter writer = new TextFileWriter();
        String filename = rootPath + fileProps.getProperty("fileName");
        List<TextRow> textRows = reader.readInput(filename);
        ReportData reportData = processor.processData(textRows);
        writer.writeReport(reportData);
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
