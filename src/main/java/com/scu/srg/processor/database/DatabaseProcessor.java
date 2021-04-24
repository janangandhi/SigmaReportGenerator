package com.scu.srg.processor.database;

import com.scu.srg.model.ReportData;
import com.scu.srg.model.InputRow;
import com.scu.srg.processor.SigmaReportProcessor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class DatabaseProcessor implements SigmaReportProcessor {

    private static final Logger logger = LogManager.getLogger(DatabaseProcessor.class);

    @Override
    public ReportData processData(List<InputRow> inputData) {
        logger.info("Processing database input!!");
        //implementation code
        return null;
    }
}
