/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import ts.projects.packagedecoder.PackageDecoderApplication;

/**
 * Config for the {@link PackageDecoderApplication}
 */
@Component
@ConfigurationProperties(prefix = "pd")
@Getter
@Setter
public class PackageDecoderConfig {
    /**
     * Defines the path of the input file that contains the BITS
     */
    private String inputFilePath;
}