package com.scu.srg.report;

import com.scu.srg.model.InputRow;
import com.scu.srg.model.ReportData;
import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

import java.util.List;

public class SigmaReport {

    private final SigmaReportReader reader;
    private final SigmaReportProcessor processor;
    private final SigmaReportWriter writer;

    public SigmaReport(ReportBuilder builder) {
        this.reader = builder.reader;
        this.processor = builder.processor;
        this.writer = builder.writer;
    }

    public void generateSigmaReport(String fileName) {
        List<InputRow> inputRows = reader.readInput(fileName);
        ReportData reportData = processor.processData(inputRows);
        writer.writeReport(reportData);
    }

    public static class ReportBuilder implements com.scu.srg.report.ReportBuilder {

        public SigmaReportReader reader;
        public SigmaReportProcessor processor;
        public SigmaReportWriter writer;

        public ReportBuilder readWith(SigmaReportReader reader) {
            this.reader = reader;
            return this;
        }

        public ReportBuilder processWith(SigmaReportProcessor processor) {
            this.processor = processor;
            return this;
        }

        public ReportBuilder writeWith(SigmaReportWriter writer) {
            this.writer = writer;
            return this;
        }

        public SigmaReport build() {
            return new SigmaReport(this);
        }

    }
}
