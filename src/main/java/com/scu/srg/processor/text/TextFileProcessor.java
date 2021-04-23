package com.scu.srg.processor.text;

import com.scu.srg.model.Project;
import com.scu.srg.model.ReportData;
import com.scu.srg.model.TextRow;
import com.scu.srg.processor.SigmaReportProcessor;

import java.util.List;

public class TextFileProcessor implements SigmaReportProcessor {

    private TextFileDataProcessor dataProcessor;
    private TextFileDataMapper mapper;


    public TextFileProcessor(TextFileDataProcessor dataProcessor, TextFileDataMapper mapper) {
        this.dataProcessor = dataProcessor;
        this.mapper = mapper;
    }

    @Override
    public ReportData processData(List<TextRow> inputData) {

        List<Project> projectData = dataProcessor.parseFileData(inputData);

        return mapper.mapReportData(projectData);

    }


}
