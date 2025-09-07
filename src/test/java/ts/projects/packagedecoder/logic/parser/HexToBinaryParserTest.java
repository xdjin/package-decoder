package ts.projects.packagedecoder.logic.parser;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HexToBinaryParserTest {

    @Test
    void convertToBinaryString_correct() {
        final String s = HexToBinaryParser.convertToBinaryString("FFF");
        Assertions.assertThat(s).isEqualTo("111111111111");
    }


    @Test
    void test_convertToBinaryString_throw_exception() {
        Assertions.assertThatExceptionOfType(ParsingException.class).isThrownBy(() -> HexToBinaryParser.convertToBinaryString("QQQ"));
    }
}