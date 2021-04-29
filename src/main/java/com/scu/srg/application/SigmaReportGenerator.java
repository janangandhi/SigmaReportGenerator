package com.scu.srg.application;

import com.scu.srg.builder.SigmaReportBuilderInterface;
import com.scu.srg.constant.Constants;
import com.scu.srg.constant.Properties;
import com.scu.srg.factory.SigmaReportFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SigmaReportGenerator implements ReportGenerator {

    private static final Logger logger = LogManager.getLogger(SigmaReportGenerator.class);

    private final SigmaReportBuilderInterface builder;

    public SigmaReportGenerator(SigmaReportBuilderInterface builder) {
        this.builder = builder;
    }

    public void generateReport(SigmaReportFactory reportFactory) {

        String filePath  = Properties.getInstance().getRootPath() +
                Properties.getInstance().getProperty(Constants.FILENAME_PROPERTY);

        logger.info("Generating report for file "+ filePath);

        builder.readWith(reportFactory.getSigmaReportReader())
                .processWith(reportFactory.getSigmaReportProcessor())
                .writeWith(reportFactory.getSigmaReportWriter())
                .generateSigmaReport(filePath);

        logger.info("Report generated successfully!");
    }
}
