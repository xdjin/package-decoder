package ts.projects.packagedecoder.logic.parser;

/**
 * Exception that can be thrown on parsing failures
 */
public class ParsingException extends RuntimeException {
    /**
     * Construct new {@link ParsingException} with message
     *
     * @param message the message
     */
    public ParsingException(String message) {
        super(message);
    }
}
