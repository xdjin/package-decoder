/*
 * Copyright (c) 2025 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 *
 */
package ts.projects.packagedecoder.in;

/**
 * Interface to read content from different input sources
 */
public interface InputSource {
    /**
     * Reads content from a source
     *
     * @return the content as string
     */
     String readContentFromSource();
}
