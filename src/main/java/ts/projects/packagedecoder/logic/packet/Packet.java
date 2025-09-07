package ts.projects.packagedecoder.logic.packet;

import static ts.projects.packagedecoder.logic.packet.CalculationFunctions.CALC_FUNCTIONS;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * The class representation of a parsed package
 */
@Getter
@Setter
public class Packet {
    private int version;
    private PacketType type;
    private long literal;
    List<Packet> subPackets = new ArrayList<>();

    /**
     * Calculates the sum of the version of this packet and all sub packets
     *
     * @return the sum of versions of the packets
     */
    public long calcVersionSum() {
        long sum = this.version;
        for (Packet subPacket : subPackets) {
            sum += subPacket.calcVersionSum();
        }
        return sum;
    }

    /**
     * Calculates the value of the packet by applying the calculation function given by its packet type
     *
     * @return the calculations value
     */
    public long calcValue() {
        return CALC_FUNCTIONS.get(type).apply(this);
    }

}
