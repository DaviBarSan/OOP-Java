import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author hdaniel@ualg.pt
 * @version 202103311747
 */
class NoteTest {
    private static final int pc0  = ChromaticScale.pitchClass("C");
    private static final int pc1  = ChromaticScale.pitchClass("F#");
    private static final Note n0  = new Note(pc0, 4, new Duration(1, 1));
    private static final Note n1  = new Note(pc1, 0, new Duration(3, 8));

    @Test
    void note01() {
        assertEquals("C", ChromaticScale.pitchClass(n0.pitchClass()));
        assertEquals(4, n0.octave());
        assertEquals(1, n0.duration().num());
        assertEquals(1, n0.duration().den());
    }

    @Test
    void note02() {
        assertEquals("F#", ChromaticScale.pitchClass(n1.pitchClass()));
        assertEquals(0, n1.octave());
        assertEquals(3, n1.duration().num());
        assertEquals(8, n1.duration().den());
    }

    @Test
    void parse01() {
        String[] s0 = { "A4-3/8", "F#4-3/8" };
        String[] s1 = {
                "H8-1/2",  //error invalid pitch class
                "F#-1/2",  //error octave missing
                "",     //error note bad format
                "F#4",     //error note bad format
                "F#4-1/0", //error invalid duration
                "F#4-1\0"  //error duration bad format
        };
        String[] s2 = {
                "pitch class 'H' not known",
                CommonDefinitions.errorStr01,
                CommonDefinitions.errorStr00,
                CommonDefinitions.errorStr00,
                CommonDefinitions.errorStr20,
                CommonDefinitions.errorStr21
        };

        //Ok
        for (String s : s0) assertEquals(s, new Note(s).toString());

        //exceptions
        for (int i=0; i<s1.length; ++i) {
            String input = s1[i]; //need to access index here for lambda
            Exception e = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Note(input)
            );
            assertEquals(s2[i], e.getMessage());
        }

    }



    @Test
    void testToString01() {
        assertEquals("C4-1/1", n0.toString());
    }

    @Test
    void testToString02() {
        assertEquals("F#0-3/8", n1.toString());
    }
}