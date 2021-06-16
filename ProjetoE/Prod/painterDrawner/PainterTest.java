import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Painter class black box unit tests
 * Tests are setup in a Mooshak fashion
 *
 * @version 202103091842
 * @author hdaniel@ualg.pt
 */
class PainterTest {

    /* Mooshak like black box tests
     * Each row of the array represents one test
     * First element in the row is the input (what to put in STDIN)
     * Second (and last) element in the row is the expected output (at STDOUT)
     */
    static final private String [][] testCases = {

            { "Circle((1.10,0.50),0.75)\nCircle((-0.90,-0.50),0.75)\n",
                    "1.10 0.50 0.75\n-0.90 -0.50 0.75\n" },
            { "Segment((1.00,1.00),(2.00,2.00))\nSegment((1.00,0.29),(1.00,1.71))\n",
                    "1.00 1.00 2.00 2.00\n1.00 0.29 1.00 1.71\n" },
            { "Polygon((1.00,1.00),(1.50,1.00),(1.25,1.50))\nPolygon((0.19,-0.13),(0.54,0.23),(0.01,0.40))\n",
                    "1.00 1.00 1.50 1.00 1.25 1.50\n0.19 -0.13 0.54 0.23 0.01 0.40\n" },
            { "Polygon((1.30,1.00),(1.50,1.00),(1.40,1.50))\nPolygon((1.70,1.48),(1.84,1.62),(1.41,1.90))\n" ,
                    "1.30 1.00 1.50 1.00 1.40 1.50\n1.70 1.48 1.84 1.62 1.41 1.90\n" },
            { "Polygon((-1.00,-1.00),(1.00,-1.00),(1.00,1.00),(-1.00,1.00)\n"+
                    "Polygon((0.13,-0.87),(1.87,0.13),(0.87,1.87),(-0.87,0.87))\n",
                    "-1.00 -1.00 1.00 -1.00 1.00 1.00 -1.00 1.00\n"+
                    "0.13 -0.87 1.87 0.13 0.87 1.87 -0.87 0.87\n" },
            { "Polygon((-1.19,1.40),(-0.99,1.10),(-0.89,0.50),(-0.79,0.40),(-0.33,0.30),(-0.11,0.10))\n"+
                    "Polygon((2.18,1.51),(1.83,1.55),(1.30,1.86),(1.16,1.85),(0.79,1.56),(0.49,1.52))\n",
                    "-1.19 1.40 -0.99 1.10 -0.89 0.50 -0.79 0.40 -0.33 0.30 -0.11 0.10\n" +
                    "2.18 1.51 1.83 1.55 1.30 1.86 1.16 1.85 0.79 1.56 0.49 1.52\n" },
    };


    /**
     * Redirect STDIN and STDOUT for Mooshak like black box tests
     *
     * @param input input String for STDIN
     * @return      ByteArrayOutputString that refeences STDOUT
     */
    static private ByteArrayOutputStream setIOstreams(String input) {
        //set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        //set stdout
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        return os;
    }


    /*
     * Mooshak like black box tests
     */
    private void test(String[][] tests) {
        for (String[] test : tests) {
            String input    = test[0];
            String expected = test[1];

            ByteArrayOutputStream os = setIOstreams(input);
            Painter.main(null);  //call Main()
            assertEquals(expected, os.toString());
        }
    }

    @Test
    public void testParse0() {
        test(testCases);
    }

}