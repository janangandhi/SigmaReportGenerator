package com.scu.srg.report;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

public class SigmaReportBuilder implements ReportBuilder {

    private SigmaReportReader reader;
    private SigmaReportProcessor processor;
    private SigmaReportWriter writer;

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
        return new SigmaReport(reader, processor, writer);
    }
}
