/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.logic.packet;

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

    public long calcVersionSum() {
        long sum = this.version;
        for (Packet subPacket : subPackets) {
            sum += subPacket.calcVersionSum();
        }
        return sum;
    }

}
