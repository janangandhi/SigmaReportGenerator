package com.scu.srg.factory;

import com.scu.srg.model.ReportData;
import com.scu.srg.model.TextRow;
import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

import java.util.List;

public class SigmaReportBuilder {

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
        List<TextRow> textRows = reader.readInput(fileName);
        ReportData reportData = processor.processData(textRows);
        writer.writeReport(reportData);
    }
}
