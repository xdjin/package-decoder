package ts.projects.packagedecoder.logic;

import javax.xml.stream.events.StartDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ts.projects.packagedecoder.in.InputSource;
import ts.projects.packagedecoder.logic.packet.Packet;
import ts.projects.packagedecoder.logic.parser.PacketParser;

/**
 * Class that manages the orchestration of the decoding
 */
@Component
public class Decoder {
    private static final Logger LOG = LoggerFactory.getLogger(Decoder.class);

    final PacketParser packetParser;

    /**
     * Constructor
     *
     * @param packetParser the parser component
     */
    public Decoder(final PacketParser packetParser) {
        this.packetParser = packetParser;
    }

    /**
     * Orchestrates the decoding of a hex string that contains BITS encoded binary sequences
     *
     * @return the result of the decoding that contains the versionSum and packageValue
     */
    public DecoderResult decodeInput(final String hexString) {
        packetParser.initParser(hexString);
        LOG.info("Start parsing packet...");
        final Packet packet = packetParser.parsePacket();
        LOG.info("Finished parsing packet...");
        return new DecoderResult(packet.calcVersionSum(), packet.calcValue());
    }
}
