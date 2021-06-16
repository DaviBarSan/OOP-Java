import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;


/**
 * Uses DrawFigure classes to paint geometric figures in a Canvas.
 * Objects are defined by arrays of points ordered counter clockwise
 *
 * @version 20210306136
 * @author hdaniel@ualg.pt
 */
public class Painter {
    protected static final int canvasX = 450;
    protected static final int canvasY = 500;
    protected static final String circleName = "Circle";
    protected static final String lineSegmentName = "Segment";
    protected static final String polygonName = "Polygon";


    /**
     * Parse an input line in the format:
     *      realNumber[[ ]*realNumber]*
     *
     * line != null
     *
     * @param line  a String to be parsed
     * @return      returns an array of Doubles
     */
    public static double[] parse(String line) {
        String[] strs = line.split("[(), ]+", 0);
        if (strs[0].equals(circleName) ||
            strs[0].equals(lineSegmentName)  ||
            strs[0].equals(polygonName)) {
                //Remove figure name at strs[0]
                strs = Arrays.copyOfRange(strs, 1, strs.length);
                //rejoin string without separators
                line = String.join(" ", strs);
                }
        return ParseString.getDoubles(line, " ");
    }

    /**
     * Paints on Canvas 2 figures.
     * Figures can be circles or polygons.
     *
     * @pre original.size() % 2 == 0    && original points ordered counter clockwise &&
     *      transformed.size() % 2 == 0 && transformed points ordered counter clockwise &&
     *      original.size() == transformed.size()
     *
     * @param original     a figure defined by an array of real numbers
     *                     in the format: realNumber[[ ]*realNumber]*
     * @param transformed  another figure defined in the same format
     */
    public static void paint(double[] original, double[] transformed) {
        Canvas canvas = new Canvas("Painter",canvasX, canvasY);
        canvas.axis();
        double scale = 100.0;
        Point origin = new Point(canvasX/(2*scale),canvasY/(2*scale));

        if (original.length == 3)
            canvas.add((new DrawCircle(original, origin, scale))
                                .outline().color(Palette.red0));
        else
            canvas.add((new DrawPolygon(original, origin, scale))
                                .outline().color(Palette.red0));

        if (transformed.length == 3)
            canvas.add((new DrawCircle(transformed, origin, scale))
                                .fill().color(Palette.blue0));
        else {
            if (original.length == 4)
                //Line as no inside, so fill() won't draw anything
                canvas.add((new DrawPolygon(transformed, origin, scale))
                        .outline().color(Palette.blue0).penThickness(3));
                else canvas.add((new DrawPolygon(transformed, origin, scale))
                        .fill().color(Palette.blue0));
        }

        canvas.show();
    }


    /**
     * Converts an array of doubles to a string
     *
     * @param arr arr of double
     * @param sep double separator character
     * @return    return the doubles in arr as a String
     */
    private static String arrayToString(double[] arr, String sep) {
        StringJoiner joiner = new StringJoiner(sep);
        for (double x : arr)
            joiner.add(String.format("%.2f", x));
        return joiner.toString();
    }


    /**
     * App entry point
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] original    = null;
        double[] transformed = null;

        try {
            original = parse(sc.nextLine());
            transformed = parse(sc.nextLine());
        }
        catch (Exception e) {
            System.exit(1);
        }

        sc.close();

        paint(original, transformed);

        //Just to run PainterTest
        //Use print "\n" to fix windows endline with "\r\n" with println
        System.out.print(arrayToString(original, " ")+"\n");
        System.out.print(arrayToString(transformed, " ")+"\n");
    }

}
