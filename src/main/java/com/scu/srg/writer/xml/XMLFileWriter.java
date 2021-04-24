package com.scu.srg.writer.xml;

import com.scu.srg.model.ReportData;
import com.scu.srg.writer.SigmaReportWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class XMLFileWriter implements SigmaReportWriter {

    private static final Logger logger = LogManager.getLogger(XMLFileWriter.class);


    @Override
    public void writeReport(ReportData reportData) {
        logger.info("Writing report in XML!!");
        //implementation code
    }
}
