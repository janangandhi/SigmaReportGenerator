package com.scu.srg.factory;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.processor.text.data.TextFileDataMapper;
import com.scu.srg.processor.text.data.TextFileDataProcessor;
import com.scu.srg.processor.text.TextFileProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.reader.text.TextFileReader;
import com.scu.srg.writer.SigmaReportWriter;
import com.scu.srg.writer.text.TextFileWriter;

public final class SigmaReportTextFactory implements SigmaReportFactory {

    private static final SigmaReportTextFactory instance = new SigmaReportTextFactory();

    private SigmaReportTextFactory(){}

    public static SigmaReportTextFactory getInstance() {
        return instance;
    }

    public SigmaReportReader getSigmaReportReader() {
        return new TextFileReader(new TextLineMapperFactory());
    }

    public SigmaReportProcessor getSigmaReportProcessor() {
        return new TextFileProcessor(new TextFileDataProcessor(), new TextFileDataMapper());
    }

    public SigmaReportWriter getSigmaReportWriter() {
        return new TextFileWriter();
    }
}
