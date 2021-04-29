package com.scu.srg.application;

import com.scu.srg.builder.SigmaReportBuilder;
import com.scu.srg.builder.SigmaReportBuilderInterface;
import com.scu.srg.factory.SigmaReportFactory;
import com.scu.srg.factory.SigmaReportFactoryGenerator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ReportGenerator {

    private static final Logger logger = LogManager.getLogger(ReportGenerator.class);

    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String propFilePath = rootPath + "app.properties";
    private final SigmaReportBuilderInterface builder;

    Properties fileProps;

    public ReportGenerator(SigmaReportBuilderInterface builder) {
        loadProperties();
        this.builder = builder;
    }

    public void generateReport() {
        String fileName = rootPath + fileProps.getProperty("fileName");

        SigmaReportFactory reportFactory = SigmaReportFactoryGenerator.getInstance()
                .getSigmaReportFactory(fileProps.getProperty("fileType"));

        createReport(reportFactory, fileName);
    }

    public void createReport(SigmaReportFactory reportFactory, String fileName) {
        builder.readWith(reportFactory.getSigmaReportReader())
                .processWith(reportFactory.getSigmaReportProcessor())
                .writeWith(reportFactory.getSigmaReportWriter())
                .generateSigmaReport(fileName);
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
