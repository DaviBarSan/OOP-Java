/**
 * Stores info of a midi note
 *
 * @author hdaniel@ualg.pt
 * @version 202104031120
 */
public class MidiNote {
    /*
        midi pitch

        127 G9                 12543.85 Hz
         69 A4   concert pitch   440.00 Hz
         60 C4   middle C        261.63 Hz
          0 C-1                    8.18 Hz
     */

    static private final byte defaultVelocity = 100;

    private final byte midipitch;
    private final double duration;
    private final byte velocity;    //velocity (and hardness) in midi [0, 127] [pppp, ffff]
                                    //https://en.wikipedia.org/wiki/Dynamics_(music)

    /**
     * @param p midi note code
     * @param d note duration as a real number
     * @param v note velocity (hardness of playing or pluck)
     */
    public MidiNote(int p, double d, int v) {
        midipitch = (byte) p;
        duration = d;
        velocity = (byte) v;
    }


    /**
     * @param n a Note
     */
    public MidiNote(Note n) {
        int idx = n.pitchClass();
        midipitch = (byte) (idx+(n.octave()+1)*ChromaticScale.size());
        duration = n.duration().ratio();
        velocity = defaultVelocity;
    }

    public int pitch()       { return this.midipitch; }
    public double duration() { return this.duration; }
    public int velocity()    { return this.velocity; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MidiNote)) return false;
        return  ((MidiNote) o).midipitch == midipitch &&
                ((MidiNote) o).duration == duration &&
                ((MidiNote) o).velocity == velocity;
    }

}
