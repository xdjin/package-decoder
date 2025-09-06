package ts.projects.packagedecoder.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ts.projects.packagedecoder.logic.Decoder;

@Component
public class DecoderRunner implements CommandLineRunner {

    private final Decoder decoder;
    /**
     * Constructor
     * @param decoder the decoder that handles the decoding steps of packets
     */
    public DecoderRunner(final Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void run(String... args) {
        final String result = decoder.decodeInput();
        System.out.println(result);
    }
}