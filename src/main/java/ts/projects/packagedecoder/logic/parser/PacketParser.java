/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.logic.parser;

import static ts.projects.packagedecoder.logic.packet.PacketType.LITERAL;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ts.projects.packagedecoder.logic.packet.Packet;
import ts.projects.packagedecoder.logic.packet.PacketType;

/**
 * Class to parse the input hex string content to a {@link Packet} (that contains the packet hierarchy)
 * Not thread-safe
 */
@Service
public class PacketParser {
    private static final Logger LOG = LoggerFactory.getLogger(PacketParser.class);

    private String binaryString;
    private int currentPosition = 0;

    /**
     * Initializes the parser with a new binary string to create a {@link Packet} from
     *
     * @param hexString
     */
    public void initParser(final String hexString) {
        LOG.info("Initializing packet parser with new binary string");
        binaryString = HexToBinaryParser.convertToBinaryString(hexString);
    }

    /**
     * Resets the parser so new packets can be parsed
     */
    public void resetParser() {
        currentPosition = 0;
        binaryString = "";
    }


    /**
     * Parses the content of a package based
     */
    public Packet parsePacket() {
        final Packet packet = new Packet();
        packet.setVersion(parseVersion());
        packet.setType(parseType());

        if (packet.getType() == LITERAL) {
            packet.setLiteral(parseLiteral());
        } else {
            packet.setSubPackets(parseOperator());
        }

        return packet;
    }

    private int parseVersion() {
        final String versionBinary = binaryString.substring(currentPosition, currentPosition + 3);
        currentPosition += 3;
        return Integer.parseInt(versionBinary, 2);
    }

    private PacketType parseType() {
        final String typeBinary = binaryString.substring(currentPosition, currentPosition + 3);
        currentPosition += 3;
        return PacketType.resolvePacketType(typeBinary);
    }

    private long parseLiteral() {
        long literal = 0;
        String currentSubstring;
        final String isLast = "0";

        do {
            currentSubstring = binaryString.substring(currentPosition, currentPosition + 1);
            currentPosition += 1;

            final String literalValuePart = binaryString.substring(currentPosition, currentPosition + 4);
            literal += Long.parseLong(literalValuePart, 2);
            currentPosition += 4;

        } while (!currentSubstring.equals(isLast));

        return literal;
    }

    private List<Packet> parseOperator() {
        final List<Packet> subPackets = new ArrayList<>();
        final String lengthType = binaryString.substring(currentPosition, currentPosition + 1);
        currentPosition += 1;

        if (lengthType.equals("0")) {
            final String subPacketsLenghtBinary = binaryString.substring(currentPosition, currentPosition + 15);
            int lengthOfSubPackets = Integer.parseInt(subPacketsLenghtBinary, 2);
            currentPosition += 15;
            int begOfSubPackets = currentPosition;

            while (lengthOfSubPackets > currentPosition - begOfSubPackets) {
                subPackets.add(parsePacket());
            }
        } else {
            final String subPacketsCountBinary = binaryString.substring(currentPosition, currentPosition + 11);
            int subPacketsCount = Integer.parseInt(subPacketsCountBinary, 2);
            currentPosition += 11;

            for (int i = 0; i < subPacketsCount; i++) {
                subPackets.add(parsePacket());
            }
        }

        return subPackets;
    }


}
