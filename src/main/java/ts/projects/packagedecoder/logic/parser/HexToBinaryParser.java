/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.logic.parser;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to convert the hex string to binary string
 */
public class HexToBinaryParser {

    private static final Logger LOG = LoggerFactory.getLogger(HexToBinaryParser.class);
    private static final Map<Character, String> HEX_TO_BINARY_MAP = Map.ofEntries(
            Map.entry('0', "0000"),
            Map.entry('1', "0001"),
            Map.entry('2', "0010"),
            Map.entry('3', "0011"),
            Map.entry('4', "0100"),
            Map.entry('5', "0101"),
            Map.entry('6', "0110"),
            Map.entry('7', "0111"),
            Map.entry('8', "1000"),
            Map.entry('9', "1001"),
            Map.entry('A', "1010"),
            Map.entry('B', "1011"),
            Map.entry('C', "1100"),
            Map.entry('D', "1101"),
            Map.entry('E', "1110"),
            Map.entry('F', "1111")
    );

    public static String convertToBinaryString(final String hexString) {
        LOG.info("Convert hex to binary string: hexString: {}", hexString);
        return hexString
                .chars()
                .mapToObj(c -> (char) c)
                .map(hex -> {
                    final String binaryString = HEX_TO_BINARY_MAP.get(hex);

                    if (binaryString == null) {
                        throw  new ParsingException("Unable to parse hex to binary: Invalid hexadecimal character: " + hex);
                    }

                    return binaryString;
                })
                .collect(Collectors.joining("", "", ""));
    }


}
