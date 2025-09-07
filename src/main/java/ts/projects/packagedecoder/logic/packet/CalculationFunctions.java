package ts.projects.packagedecoder.logic.packet;

import static ts.projects.packagedecoder.logic.packet.PacketType.LITERAL;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_EQ;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_GT;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_LT;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_MAX;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_MIN;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_PROD;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_SUM;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import ts.projects.packagedecoder.logic.parser.ParsingException;

/**
 * Holds different calculation functions for the different {@link PacketType}'s that can be applied to calculate packet values
 */
public class CalculationFunctions {

    static final Map<PacketType, Function<Packet, Long>> CALC_FUNCTIONS = new HashMap<>();


    static {
        CALC_FUNCTIONS.put(LITERAL, Packet::getLiteral);

        CALC_FUNCTIONS.put(OPERATOR_SUM, packet -> packet.subPackets.stream().mapToLong(Packet::calcValue).sum());

        CALC_FUNCTIONS.put(OPERATOR_PROD, packet -> packet.subPackets.stream().mapToLong(Packet::calcValue).reduce(1, (a, b) -> a * b));

        CALC_FUNCTIONS.put(OPERATOR_MIN, packet -> packet.subPackets.stream().mapToLong(Packet::calcValue).min()
                .orElseThrow(() -> new ParsingException("Unable to calculate packet value: Unable get min value on empty sub-packets")));

        CALC_FUNCTIONS.put(OPERATOR_MAX, packet -> packet.subPackets.stream().mapToLong(Packet::calcValue).max()
                .orElseThrow(() -> new ParsingException("Unable to calculate packet value: Unable get max value on empty sub-packets")));

        CALC_FUNCTIONS.put(OPERATOR_GT, packet -> {
            return compareSubPacketsOfPacketWithExactlyTwoSubPacketsByFunction(packet,
                    () -> packet.subPackets.get(0).calcValue() > packet.subPackets.get(1).calcValue() ? 1L : 0L,
                    "greater than"
            );
        });

        CALC_FUNCTIONS.put(OPERATOR_LT, packet -> {
            return compareSubPacketsOfPacketWithExactlyTwoSubPacketsByFunction(packet,
                    () -> packet.subPackets.get(0).calcValue() < packet.subPackets.get(1).calcValue() ? 1L : 0L,
                    "lower than"
            );
        });

        CALC_FUNCTIONS.put(OPERATOR_EQ, packet -> {
            return compareSubPacketsOfPacketWithExactlyTwoSubPacketsByFunction(packet,
                    () -> packet.subPackets.get(0).calcValue() == packet.subPackets.get(1).calcValue() ? 1L : 0L,
                    "equal to"
            );
        });

    }


    private static long compareSubPacketsOfPacketWithExactlyTwoSubPacketsByFunction(Packet packet,
                                                                                    Supplier<Long> comparisonSupplier,
                                                                                    String operatorName) {
        if (packet.subPackets.size() != 2) {
            throw new ParsingException(
                    "Unable to calculate packet value: Unable to apply '" + operatorName +
                            "' on a packet that contains more or less than two sub-packets");
        }
        return comparisonSupplier.get();
    }
}
