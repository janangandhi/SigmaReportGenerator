package com.scu.srg.factory;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.processor.xml.XMLFileProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.reader.xml.XMLFileReader;
import com.scu.srg.writer.SigmaReportWriter;
import com.scu.srg.writer.xml.XMLFileWriter;

public final class SigmaReportXMLFactory implements SigmaReportFactory{

    private static SigmaReportXMLFactory instance;

    private SigmaReportXMLFactory(){}

    public static SigmaReportXMLFactory getInstance() {
        if (instance == null) {
            instance = new SigmaReportXMLFactory();
        }
        return instance;
    }

    @Override
    public SigmaReportReader getSigmaReportReader() {
        return new XMLFileReader();
    }

    @Override
    public SigmaReportProcessor getSigmaReportProcessor() {
        return new XMLFileProcessor();
    }

    @Override
    public SigmaReportWriter getSigmaReportWriter() {
        return new XMLFileWriter();
    }
}
