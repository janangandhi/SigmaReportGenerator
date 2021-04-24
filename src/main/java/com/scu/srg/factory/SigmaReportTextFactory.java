package com.scu.srg.factory;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.processor.text.TextFileDataMapper;
import com.scu.srg.processor.text.TextFileDataProcessor;
import com.scu.srg.processor.text.TextFileProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.reader.text.TextFileReader;
import com.scu.srg.writer.SigmaReportWriter;
import com.scu.srg.writer.text.TextFileWriter;

public class SigmaReportTextFactory implements SigmaReportFactory {

    public SigmaReportReader getSigmaReportReader() {
        return new TextFileReader();
    }

    public SigmaReportProcessor getSigmaReportProcessor() {
        return new TextFileProcessor(new TextFileDataProcessor(), new TextFileDataMapper());
    }

    public SigmaReportWriter getSigmaReportWriter() {
        return new TextFileWriter();
    }
}
