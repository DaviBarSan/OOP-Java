import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    /*
    Mooshak performs black box tests
    Each row of the array represents one test
    First element in the row is the input (what to put in STDIN)
    Second (and last) element in the row is the expected output (at STDOUT)
     */
    static private String [][] stdiotests = {
            { "00:01:15\nasSeconds\n", "75\n"},
            { "75\nasTime\n", "00:01:15\n"},
            { "13:27:15\nadd\n1\n", "00:00:00\n"},
            { "13:27:15\nadd\n00:00:02\n", "00:00:01\n"},
    };

    //Redirect STDIN and STDOUT for Mooshak like black box tests
    static private ByteArrayOutputStream setIOstreams(String input){
        //Set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        //set  stdout
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        return os;

    }

    @Test
    public void testCase0() {
        for (String[] test : stdiotests) {
            String input = test[0];
            String expected = test[1];

            ByteArrayOutputStream os = setIOstreams(input);
            Client.main(null);
            assertEquals(expected, os.toString());
        }
    }


}
