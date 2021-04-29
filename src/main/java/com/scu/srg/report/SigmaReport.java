package com.scu.srg.report;

import com.scu.srg.model.InputRow;
import com.scu.srg.model.ReportData;
import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SigmaReport {

    private final SigmaReportReader reader;
    private final SigmaReportProcessor processor;
    private final SigmaReportWriter writer;

    public void create(String fileName) {
        List<InputRow> inputRows = reader.readInput(fileName);
        ReportData reportData = processor.processData(inputRows);
        writer.writeReport(reportData);
    }

}
