package com.scu.srg.report;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

public interface ReportBuilder {

    ReportBuilder readWith(SigmaReportReader reader);

    ReportBuilder processWith(SigmaReportProcessor processor);

    ReportBuilder writeWith(SigmaReportWriter writer);

    SigmaReport build();
}
