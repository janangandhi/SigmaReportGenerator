package com.scu.srg.builder;

import com.scu.srg.model.ReportData;
import com.scu.srg.model.InputRow;
import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

import java.util.List;

public class SigmaReportBuilder implements SigmaReportBuilderInterface{

    private SigmaReportReader reader;
    private SigmaReportProcessor processor;
    private SigmaReportWriter writer;

    public SigmaReportBuilder readWith(SigmaReportReader reader) {
        this.reader = reader;
        return this;
    }

    public SigmaReportBuilder processWith(SigmaReportProcessor processor) {
        this.processor = processor;
        return this;
    }

    public SigmaReportBuilder writeWith(SigmaReportWriter writer) {
        this.writer = writer;
        return this;
    }

    public void generateSigmaReport(String fileName) {
        List<InputRow> inputRows = reader.readInput(fileName);
        ReportData reportData = processor.processData(inputRows);
        writer.writeReport(reportData);
    }
}
