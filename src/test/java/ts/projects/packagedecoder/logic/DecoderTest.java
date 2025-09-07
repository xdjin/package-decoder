package ts.projects.packagedecoder.logic;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ts.projects.packagedecoder.logic.parser.PacketParser;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DecoderTest {

    @Test
    void test_decodeInput_generates_correct_result() {
        final PacketParser packetParser = new PacketParser();
        final Decoder testee = new Decoder(packetParser);

        final DecoderResult decoderResult = testee.decodeInput("C200B40A82");

        Assertions.assertThat(decoderResult.getVersionSum()).isEqualTo(14L);
        Assertions.assertThat(decoderResult.getPacketValue()).isEqualTo(3L);
    }
}