package ts.projects.packagedecoder.out;

/**
 * Generates the output message's and writes it to console
 */
public class OutputMessageWriter {


    /**
     * Writes the error message to console
     *
     * @param message the information that caused the failure
     */
    public static void writeErrorMessage(final String message) {
        System.out.println("Failed to decode content: " + message);
    }


    /**
     * Writes the result message of a successful decoding to the console
     *
     * @param versionSum       the result of the version sum calculation
     * @param packageCalcValue the result of the package value calculation
     */
    public static void writeResult(final long versionSum, final long packageCalcValue) {
        System.out.println("Decoding completed...");
        System.out.println("Version sum: " + Long.toString(versionSum, 10));
        System.out.println("Package value calculation result: " + Long.toString(packageCalcValue, 10));
    }

}
