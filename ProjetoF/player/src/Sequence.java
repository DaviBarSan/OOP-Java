
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * A sequence of IWritablePlayable: notes, chords
 *
 * @author hdaniel@ualg.pt
 * @version 202104031029
 */
public class Sequence implements Iterable<IPlayable> {
    static private final String expStr0 = "Unknown music element";
    static private String separator = " ";
    private final List<IPlayable> sequence = new ArrayList<>();


    /**
     * Parse a string for IPlayables
     *
     * @param str String to be parsed
     */
    public Sequence(String str) {

        //Split IPlayables
        String[] parts = str.split(separator);

        //parse using Note.parse and Chord.parse
        for (String s : parts) {
            IPlayable e;

            //Try single note
            //then TriadChord
            //todo: generify for any type of chords maybe not needed after removing notes from chord
            try {
                e = new Note(s);
            }
            catch (IllegalArgumentException illexp) {
                try {
                    e = new TriadChord(s);
                }
                catch (Exception exp) {
                    throw new IllegalArgumentException(expStr0 + " " + s);
                }
            }

            //Add music element to string
            sequence.add(e);
        }
    }

    @Override
    public Iterator<IPlayable> iterator() {
        return sequence.iterator();
    }

    /**
     * @param n a IWritablePlayable
     */
    public void add(IPlayable n) { sequence.add(n); }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(separator);
        for (IPlayable n : sequence)
            out.add(n.toString());
        return out.toString();
    }
}
