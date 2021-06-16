/**
 * @version 202103080844
 * @author hdaniel@ualg.pt
 */
public class ParseString {
    /**
     * Extracts real numbers from a String
     *
     * @param str a String containing real numbers defined by digits [0-9],
     *            and the decimal separator "."
     *            Real numbers are separated by sep characters
     * @param sep a separator character
     * @return    an array of Doubles found in str
     */
    public static int[] getInts(String str, String sep) throws NumberFormatException {
        String[] strs = str.split(sep, 0);

        int[] out = new int[strs.length];
        for (int i = 0; i < strs.length; ++i)
            try {
                out[i] = Integer.parseInt(strs[i]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid characters '" + strs[i] + "' in integer literal");
            }
        return out;
    }

    /**
     * Extracts integer numbers from a String
     *
     * @param line a String containing integer numbers defined by digits [0-9],
     *             separated by sep characters.
     * @param sep  a separator character
     * @return     an array of Doubles found in line
     */
    public static double[] getDoubles(String line, String sep) throws NumberFormatException {
        String[] strs = line.split(sep, 0);

        double[] out = new double[strs.length];
        for (int i = 0; i < strs.length; ++i)
            try {
                out[i] = Double.parseDouble(strs[i]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid characters '" + strs[i] + "' in double literal");
            }
        return out;
    }

}
