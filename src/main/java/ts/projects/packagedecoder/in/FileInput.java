/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.in;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ts.projects.packagedecoder.config.PackageDecoderConfig;

/**
 * Class to read content from a file
 */
@Component
public class FileInput implements InputSource {
    private static final Logger LOG = LoggerFactory.getLogger(FileInput.class);

    PackageDecoderConfig packageDecoderConfig;

    public FileInput(final PackageDecoderConfig packageDecoderConfig) {
        this.packageDecoderConfig = packageDecoderConfig;
    }

    public String readContentFromSource() {
        final String inputFilePath = packageDecoderConfig.getInputFilePath();
        Path path = Paths.get(inputFilePath);
        try {
            byte[] data = Files.readAllBytes(path);
            return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOG.error("Unable to read bytes from {}", path, e);
            throw new InputSourceException("Unable to read contents from " + path);
        }
    }
}
