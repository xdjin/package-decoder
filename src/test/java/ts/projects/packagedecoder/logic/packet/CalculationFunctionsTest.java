package ts.projects.packagedecoder.logic.packet;


import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_EQ;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_GT;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_LT;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_MAX;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_MIN;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_PROD;
import static ts.projects.packagedecoder.logic.packet.PacketType.OPERATOR_SUM;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculationFunctionsTest {

    @Test
    void test_function_literal_on_package() {
        final long val = generateLitTestPackage(10).calcValue();
        Assertions.assertThat(val).isEqualTo(10);
    }

    @Test
    void test_function_sum_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(10);
        final Packet testPacket = generateOpTestPackage(OPERATOR_SUM, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(20);
    }

    @Test
    void test_function_prod_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(10);
        final Packet testPacket = generateOpTestPackage(OPERATOR_PROD, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(100);
    }

    @Test
    void test_function_min_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(12);
        final Packet testPacket = generateOpTestPackage(OPERATOR_MIN, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(10);
    }


    @Test
    void test_function_max_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(12);
        final Packet testPacket = generateOpTestPackage(OPERATOR_MAX, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(12);
    }

    @Test
    void test_function_gt_true_on_package() {
        final Packet sub1 = generateLitTestPackage(12);
        final Packet sub2 = generateLitTestPackage(10);
        final Packet testPacket = generateOpTestPackage(OPERATOR_GT, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(1);
    }

    @Test
    void test_function_gt_false_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(12);
        final Packet testPacket = generateOpTestPackage(OPERATOR_GT, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(0);
    }

    @Test
    void test_function_lt_true_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(12);
        final Packet testPacket = generateOpTestPackage(OPERATOR_LT, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(1);
    }

    @Test
    void test_function_lt_false_on_package() {
        final Packet sub1 = generateLitTestPackage(12);
        final Packet sub2 = generateLitTestPackage(10);
        final Packet testPacket = generateOpTestPackage(OPERATOR_LT, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(0);
    }


    @Test
    void test_function_eq_true_on_package() {
        final Packet sub1 = generateLitTestPackage(10);
        final Packet sub2 = generateLitTestPackage(10);
        final Packet testPacket = generateOpTestPackage(OPERATOR_EQ, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(1);
    }

    @Test
    void test_function_eq_false_on_package() {
        final Packet sub1 = generateLitTestPackage(12);
        final Packet sub2 = generateLitTestPackage(10);
        final Packet testPacket = generateOpTestPackage(OPERATOR_EQ, sub1, sub2);

        Assertions.assertThat(testPacket.calcValue()).isEqualTo(0);
    }


    private Packet generateLitTestPackage(final long val) {
        final Packet packet = new Packet();
        packet.setType(PacketType.LITERAL);
        packet.setVersion(1);
        packet.setLiteral(val);

        return packet;
    }

    private Packet generateOpTestPackage(PacketType packetType, final Packet subPacket1, final Packet subPacket2) {
        final Packet packet = new Packet();
        packet.setType(packetType);
        packet.setVersion(1);
        packet.setSubPackets(List.of(subPacket1, subPacket2));

        return packet;
    }
}