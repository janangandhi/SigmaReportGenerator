package com.scu.srg.reader.text;

import com.scu.srg.exception.BusinessException;
import com.scu.srg.factory.LineMapperFactory;
import com.scu.srg.model.InputRow;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.factory.TextLineMapperFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TextFileReader implements SigmaReportReader {

    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    private final LineMapperFactory mapperFactory;

    public TextFileReader(LineMapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public List<InputRow> readInput(String fileName) {
        logger.info("Reading text input!!");
        List<InputRow> inputDetails = new ArrayList<>();
        logger.debug("Reading contents from file " + fileName);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> {
                InputRow lineDetails = null;
                try {
                    lineDetails = parseLine(line);
                } catch (BusinessException businessException) {
                    logger.error("Error in reading line. Skipping and moving to next line.");
                }
                if (lineDetails != null) {
                    inputDetails.add(lineDetails);
                }
            });
        } catch (IOException e) {
            logger.error("Exception in reading file contents: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Unexpected Error in parsing files: " + e.getMessage());
            return null;
        }
        return inputDetails;
    }

    private InputRow parseLine(String line) throws BusinessException {
        logger.debug("Parsing line: " + line);
        if (line.charAt(0) == '-') {
            logger.debug("Skipping line as it is a comment");
            return null;
        }

        String[] lineFields = line.split(",");

        if (lineFields.length < 2 || lineFields.length > 5) {
            throw new BusinessException("Invalid separators in the file for line : "+ line);
        }

        lineFields = Arrays.stream(lineFields).map(String::trim).toArray(String[]::new);

        return mapperFactory.getLineMapper(lineFields.length).mapLine(lineFields);
    }

}
