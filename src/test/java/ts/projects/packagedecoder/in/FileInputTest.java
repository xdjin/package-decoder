package ts.projects.packagedecoder.in;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ts.projects.packagedecoder.config.PackageDecoderConfig;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FileInputTest {

    @Test
    void testReadContentFromSource() throws  IOException {
        final String expected = "test";
        Path file = Files.createTempFile("test", ".txt");
        Files.writeString(file, expected);

        PackageDecoderConfig packageDecoderConfig = new PackageDecoderConfig();
        packageDecoderConfig.setInputFilePath(file.toString());

        FileInput input = new FileInput(packageDecoderConfig);
        String result = input.readContentFromSource();

        Assertions.assertThat(result).isEqualTo(expected);
    }
}