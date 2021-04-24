package com.scu.srg.reader.text;

import com.scu.srg.model.TextRow;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.reader.text.lineMapper.LineMapperFactory;
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

    @Override
    public List<TextRow> readInput(String fileName) {
        logger.info("Reading text input!!");
        List<TextRow> inputDetails = new ArrayList<>();
        logger.debug("Reading contents from file " + fileName);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> {
                TextRow lineDetails = parseLine(line);
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

    private TextRow parseLine(String line) {
        logger.debug("Parsing line: " + line);
        if (line.charAt(0) == '-') {
            logger.debug("Skipping line as it is a comment");
            return null;
        }

        String[] lineFields = line.split(",");

        if (lineFields.length < 2 || lineFields.length > 5) {
            logger.warn("Invalid number of fields in the line. Skipping line");
            return null;
        }

        lineFields = Arrays.stream(lineFields).map(String::trim).toArray(String[]::new);

        return new LineMapperFactory().mapLine(lineFields);
    }

}
