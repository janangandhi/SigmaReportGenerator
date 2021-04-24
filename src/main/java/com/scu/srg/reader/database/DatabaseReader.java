package com.scu.srg.reader.database;

import com.scu.srg.model.TextRow;
import com.scu.srg.reader.SigmaReportReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class DatabaseReader implements SigmaReportReader {

    private static final Logger logger = LogManager.getLogger(DatabaseReader.class);

    @Override
    public List<TextRow> readInput(String sourceName) {
        logger.info("Reading Database input!!");
        //implementation code
        return null;
    }
}
