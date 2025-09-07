package ts.projects.packagedecoder.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ts.projects.packagedecoder.in.InputSource;
import ts.projects.packagedecoder.in.InputSourceException;
import ts.projects.packagedecoder.logic.Decoder;
import ts.projects.packagedecoder.logic.DecoderResult;
import ts.projects.packagedecoder.logic.parser.ParsingException;
import ts.projects.packagedecoder.out.OutputMessageWriter;

/**
 * Runner class for the decoder
 */
@Component
public class DecoderRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(DecoderRunner.class);
    private final InputSource inputSource;
    private final Decoder decoder;

    /**
     * Constructor
     *
     * @param inputSource the input source where the content should be read from
     * @param decoder     the decoder that handles the decoding steps of packets
     */
    public DecoderRunner(InputSource inputSource, final Decoder decoder) {
        this.inputSource = inputSource;
        this.decoder = decoder;
    }

    /**
     * Main runner
     *
     * @param args arguments
     */
    @Override
    public void run(String... args) {
        try {
            final String hexString = inputSource.readContentFromSource();
            final DecoderResult decoderResult = decoder.decodeInput(hexString);
            OutputMessageWriter.writeResult(decoderResult.getVersionSum(), decoderResult.getPacketValue());
        } catch (InputSourceException | ParsingException e) {
            LOG.error("Failed decoding input: '{}'", e.getMessage());
            OutputMessageWriter.writeErrorMessage(e.getMessage());
        }
    }
}