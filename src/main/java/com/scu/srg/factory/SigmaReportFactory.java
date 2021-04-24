package com.scu.srg.factory;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.writer.SigmaReportWriter;

public interface SigmaReportFactory {

    SigmaReportReader getSigmaReportReader();

    SigmaReportProcessor getSigmaReportProcessor();

    SigmaReportWriter getSigmaReportWriter();

}
