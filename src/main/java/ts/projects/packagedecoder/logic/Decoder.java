/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.logic;

import org.springframework.stereotype.Component;

import ts.projects.packagedecoder.in.InputSource;

/**
 * Main class that handles the full decoding steps of packets
 */
@Component
public class Decoder {

    InputSource inputSource;

    /**
     * Constructor
     * @param inputSource the input source where the content should be read from
     */
    public Decoder(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public String decodeInput(){
        final String s = inputSource.readContentFromSource();
        return "decoded string: " + s;
    }
}
