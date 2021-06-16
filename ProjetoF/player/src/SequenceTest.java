import org.junit.jupiter.api.Test;
import java.util.StringJoiner;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author hdaniel@ualg.pt
 * @version 202104031036
 */
class SequenceTest {
    static private int pc0  = ChromaticScale.pitchClass("C");
    static private int pc1  = ChromaticScale.pitchClass("D");
    static private int pc2  = ChromaticScale.pitchClass("F#");
    static private IPlayable[] elems = {
            new Note(pc0, 4, new Duration(1, 1)),
            new Note(pc1, 4, new Duration(1, 2)),
            new Note(pc2, 0, new Duration(3, 8)),
            new TriadChord("C#M8-1/2")
            };

    static private String lick;
    static {
        StringJoiner j = new StringJoiner(" ");
        for (IPlayable e : elems)
            j.add(e.toString());
        lick = j.toString();
    }
/*
    @Test
    void add() {
        Sequence s = new Sequence();

        assertEquals("", s.toString());

        for (IPlayable e : elems)
            s.add(e);
        assertEquals(lick, s.toString());
    }
*/
    @Test
    void parseIterator() {
        Sequence s = new Sequence(lick);

        int i=0;
        for (IPlayable e : s) {
            assertEquals(elems[i++].toString(), e.toString());
        }
    }

    @Test
    void parseToString01() {
        Sequence s = new Sequence(lick);
        assertEquals(lick, s.toString());
    }

}