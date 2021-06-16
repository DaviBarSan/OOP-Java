import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author hdaniel@ualg.pt
 * @version 202103311747
 */
class MidiNoteTest {
    private static final int C  = ChromaticScale.pitchClass("C");
    private static final int A  = ChromaticScale.pitchClass("A");
    private static final int G  = ChromaticScale.pitchClass("G");

    private static final Note nC4  = new Note(C, 4, new Duration(1, 1));
    private static final Note nA4  = new Note(A, 4, new Duration(1, 2));
    private static final Note nG9  = new Note(G, 9, new Duration(3, 8));

    private static final MidiNote C_1 = new MidiNote(0, 0.5, 84);
    private static final MidiNote C4  = new MidiNote(nC4);
    private static final MidiNote A4  = new MidiNote(nA4);
    private static final MidiNote G9  = new MidiNote(nG9);

    @Test
    void note01() {
        assertEquals(0,  C_1.pitch());
        assertEquals(0.5,  C_1.duration());
        assertEquals(84, C_1.velocity());
    }

    @Test
    void note02() {
        assertEquals(69, A4.pitch());
        assertEquals(0.5, A4.duration());
        assertEquals(100, A4.velocity());
    }

}