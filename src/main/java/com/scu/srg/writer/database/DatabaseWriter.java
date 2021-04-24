package com.scu.srg.writer.database;

import com.scu.srg.model.ReportData;
import com.scu.srg.writer.SigmaReportWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DatabaseWriter implements SigmaReportWriter {

    private static final Logger logger = LogManager.getLogger(DatabaseWriter.class);

    @Override
    public void writeReport(ReportData reportData) {
        logger.info("Writing report into Database!!");
        //implementation code
    }
}
