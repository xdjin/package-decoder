/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.in;

/**
 * Exception for failures during reading from input source
 */
public class InputSourceException extends RuntimeException {

    /**
     * Exception with message
     *
     * @param message the failure message
     */
    public InputSourceException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause
     *
     * @param message the failure message
     * @param cause   the cause of failure
     */
    public InputSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
