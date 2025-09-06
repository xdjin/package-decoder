/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.logic.parser;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ts.projects.packagedecoder.logic.packet.Packet;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PacketParserTest {

    @InjectMocks
    private PacketParser testee;

    @Test
    void test_parsePacket_8A004A801A8002F478_for_version_sum() {
        testee.initParser("8A004A801A8002F478");
        final Packet packet = testee.parsePacket();
    }
}