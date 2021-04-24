package com.scu.srg.reader.xml;

import com.scu.srg.model.TextRow;
import com.scu.srg.reader.SigmaReportReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class XMLFileReader implements SigmaReportReader {

    private static final Logger logger = LogManager.getLogger(XMLFileReader.class);

    @Override
    public List<TextRow> readInput(String sourceName) {
        logger.info("Reading XML input!!");
        //implementation code
        return null;
    }
}
