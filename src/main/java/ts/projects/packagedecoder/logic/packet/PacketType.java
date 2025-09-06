/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.logic.packet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ts.projects.packagedecoder.logic.parser.ParsingException;

/**
 * Enum with the different packet types that exist
 */
public enum PacketType {
    LITERAL("100"),
    OPERATOR_SUM("000"),
    OPERATOR_PROD("001"),
    OPERATOR_MIN("010"),
    OPERATOR_MAX("011"),
    OPERATOR_GT("101"),
    OPERATOR_LT("110"),
    OPERATOR_EQ("111");

    private final String typeBinary;
    private static final Map<String, PacketType> PACKET_TYPE_MAP = new HashMap<>();

    static {
        Arrays.stream(PacketType.values())
                .forEach(packetType -> PACKET_TYPE_MAP.put(packetType.typeBinary, packetType));
    }

    /**
     * Constructor
     * @param typeBinary the type binary
     */
    PacketType(String typeBinary) {
        this.typeBinary = typeBinary;
    }

    /**
     * Get the typeBinary of the enum
     * @return the type binary
     */
    public String getTypeBinary() {
        return typeBinary;
    }

    /**
     * Generates a {@link PacketType} based on the type binary
     * @param typeBinary a string that represents the type binary
     * @return the {@link PacketType}
     */
    public static PacketType fromCode(String typeBinary) {
        final PacketType packetType = PACKET_TYPE_MAP.get(typeBinary);
        if (packetType == null) {
            throw new ParsingException("Unknown packet type: Unable to resolve packet type for binary: " + typeBinary);
        }
        return packetType;
    }
}
