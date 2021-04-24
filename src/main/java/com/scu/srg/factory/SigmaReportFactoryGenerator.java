package com.scu.srg.factory;

import java.util.HashMap;

public final class SigmaReportFactoryGenerator {

    private static SigmaReportFactoryGenerator instance;
    private HashMap<String, SigmaReportFactory> map;

    private SigmaReportFactoryGenerator() {
        map = new HashMap<>();
        map.put("TEXT", new SigmaReportTextFactory());
        map.put("XML", new SigmaReportTextFactory());
        map.put("RDBMS", new SigmaReportTextFactory());
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
            factory = new SigmaReportTextFactory();
        }
        return factory;
    }
}
