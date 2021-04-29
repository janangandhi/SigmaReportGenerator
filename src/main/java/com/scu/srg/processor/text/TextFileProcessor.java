package com.scu.srg.processor.text;

import com.scu.srg.model.InputRow;
import com.scu.srg.model.Project;
import com.scu.srg.model.ReportData;
import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.processor.text.data.TextFileDataMapper;
import com.scu.srg.processor.text.data.TextFileDataProcessor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class TextFileProcessor implements SigmaReportProcessor {

    private final TextFileDataProcessor dataProcessor;
    private final TextFileDataMapper mapper;
    private static final Logger logger = LogManager.getLogger(TextFileProcessor.class);

    public TextFileProcessor(TextFileDataProcessor dataProcessor, TextFileDataMapper mapper) {
        this.dataProcessor = dataProcessor;
        this.mapper = mapper;
    }

    @Override
    public ReportData processData(List<InputRow> inputData) {

        logger.info("Processing text input!!");

        List<Project> projectData = dataProcessor.parseFileData(inputData);

        return mapper.mapReportData(projectData);

    }


}
