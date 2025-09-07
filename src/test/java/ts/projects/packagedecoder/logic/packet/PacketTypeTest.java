package ts.projects.packagedecoder.logic.packet;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ts.projects.packagedecoder.logic.parser.ParsingException;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PacketTypeTest {

    @Test
    void test_resolvePacketType_to_correct_types() {
        final PacketType packetTypeSum = PacketType.resolvePacketType("000");
        final PacketType packetTypeProd = PacketType.resolvePacketType("001");
        final PacketType packetTypeMin = PacketType.resolvePacketType("010");
        final PacketType packetTypeMax = PacketType.resolvePacketType("011");
        final PacketType packetTypeGt = PacketType.resolvePacketType("101");
        final PacketType packetTypeLt = PacketType.resolvePacketType("110");
        final PacketType packetTypeEq = PacketType.resolvePacketType("111");

        Assertions.assertThat(packetTypeSum).isEqualTo(PacketType.OPERATOR_SUM);
        Assertions.assertThat(packetTypeProd).isEqualTo(PacketType.OPERATOR_PROD);
        Assertions.assertThat(packetTypeMin).isEqualTo(PacketType.OPERATOR_MIN);
        Assertions.assertThat(packetTypeMax).isEqualTo(PacketType.OPERATOR_MAX);
        Assertions.assertThat(packetTypeGt).isEqualTo(PacketType.OPERATOR_GT);
        Assertions.assertThat(packetTypeLt).isEqualTo(PacketType.OPERATOR_LT);
        Assertions.assertThat(packetTypeEq).isEqualTo(PacketType.OPERATOR_EQ);
    }

    @Test
    void test_resolvePacketType_to_wrong_type_throw_exception() {
        Assertions.assertThatExceptionOfType(ParsingException.class).isThrownBy(() -> PacketType.resolvePacketType("212"));
    }
}