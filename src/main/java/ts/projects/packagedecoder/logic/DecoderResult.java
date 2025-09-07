package ts.projects.packagedecoder.logic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Result of a decoding
 */
@Data
@AllArgsConstructor
public class DecoderResult {
    private long versionSum;
    private long packetValue;
}
