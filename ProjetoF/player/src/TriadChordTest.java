import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hdaniel@ualg.pt
 * @version 202104011541
 */
class TriadChordTest {
    static private final TriadChord CsM = new TriadChord("C#M3-3/8");
    static private final TriadChord Asm = new TriadChord("A#m5-1/8");

    static private final Note[] CsM8 = {
            new Note(ChromaticScale.pitchClass("C#"), 3, new Duration(3, 8)),
            new Note(ChromaticScale.pitchClass("F"),  3, new Duration(3, 8)),
            new Note(ChromaticScale.pitchClass("G#"), 3, new Duration(3, 8))
    };
    static private final Note[] Asm5 = {
            new Note(ChromaticScale.pitchClass("A#"), 5, new Duration(1, 8)),
            new Note(ChromaticScale.pitchClass("C#"), 5, new Duration(1, 8)),
            new Note(ChromaticScale.pitchClass("F"),  5, new Duration(1, 8))
    };

    static private final MidiNote[] CsM8midi = {
            new MidiNote(CsM8[0]),
            new MidiNote(CsM8[1]),
            new MidiNote(CsM8[2])
    };

    static private final MidiNote[] Asm5midi = {
            new MidiNote(Asm5[0]),
            new MidiNote(Asm5[1]),
            new MidiNote(Asm5[2])
    };

    @Test
    void parse01() {
        String[] s0 = { "CM4-3/8", "F#M4-3/8", "Am3-1/4", "G#m2-1/8", "Bº3-1/1" };
        String[] s1 = {
                "H8-1/2",   //error invalid chord class
                "F#-1/2",   //error quality missing
                "F#M-1/2",  //error octave missing
                " ",        //error chord bad format
                "F#M4",     //error chord bad format
                "F#m4-1/0", //error invalid duration
                "F#m4-1\0", //error duration bad format
        };
        String[] s2 = {
                CommonDefinitions.errorStr10,
                CommonDefinitions.errorStr11,
                CommonDefinitions.errorStr01,
                CommonDefinitions.errorStr13,
                CommonDefinitions.errorStr13,
                CommonDefinitions.errorStr20,
                CommonDefinitions.errorStr21,
        };

        //Ok
        for (String s : s0) assertEquals(s, new TriadChord(s).toString());

        //exceptions
        for (int i=0; i<s1.length; ++i) {
            String input = s1[i]; //need to access index here for lambda
            Exception e = assertThrows(
                    IllegalArgumentException.class,
                    () -> new TriadChord(input)
            );
            assertEquals(s2[i], e.getMessage());
        }

    }

    @Test
    void testToString() {
        assertEquals("C#M3-3/8", CsM.toString());
        assertEquals("A#m5-1/8", Asm.toString());
    }

    @Test
    void testMidiNotes() {
        assertArrayEquals(CsM8midi, CsM.asMidi());
        assertArrayEquals(Asm5midi, Asm.asMidi());
    }

}