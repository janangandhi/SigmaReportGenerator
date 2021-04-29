package com.scu.srg.builder;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

public interface SigmaReportBuilderInterface {

    SigmaReportBuilder readWith(SigmaReportReader reader);
    SigmaReportBuilder processWith(SigmaReportProcessor processor);
    SigmaReportBuilder writeWith(SigmaReportWriter writer);
    void generateSigmaReport(String fileName);
}
