package com.scu.srg.factory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

public final class SigmaReportFactoryGenerator {

    private static SigmaReportFactoryGenerator instance;
    private final HashMap<String, SigmaReportFactory> map;

    private static final Logger logger = LogManager.getLogger(SigmaReportFactoryGenerator.class);

    private SigmaReportFactoryGenerator() {
        map = new HashMap<>();
        map.put("TEXT", SigmaReportTextFactory.getInstance());
        map.put("XML", SigmaReportXMLFactory.getInstance());
        map.put("DATABASE", SigmaReportDatabaseFactory.getInstance());
    }

    public static SigmaReportFactoryGenerator getInstance() {
        if (instance == null) {
            instance = new SigmaReportFactoryGenerator();
        }
        return instance;
    }

    public SigmaReportFactory getSigmaReportFactory(String type) {
        SigmaReportFactory factory = map.get(type.toUpperCase());

        if (factory == null) {
            logger.warn("Type "+type+" not supported. Defaulting to text factory.");
            factory = SigmaReportTextFactory.getInstance();
        }
        return factory;
    }
}
