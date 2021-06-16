/**
 * Represents a musical note with pitch class, octave and duration
 *
 * pitch class and octave gives the pitch (frequency) of the note
 * in a specific tuning system
 *
 * @author hdaniel@ualg.pt
 * @version 202103311108
 */
public class Note implements IPlayable {
    private final byte pclass;        //pitch class of note in ChromaticScale
    private final byte octave;        //middle octave is 4
    private final Duration duration;  //duration as a fraction

    /**
     * Parse a note from a string
     *
     * @param s String to be parsed
     * @return a Note if successful
     */
    public Note (String s) {
        //Split pitch class and octave from duration
        String[] parts1 = s.split(CommonDefinitions.durationSeparator);
        if (parts1.length != 2)
            throw new IllegalArgumentException(CommonDefinitions.errorStr00);

        //Split pitch class from octave
        String[] parts2 = parts1[0].split("(?<=\\D)(?=\\d)");
        if (parts2.length != 2)
            throw new IllegalArgumentException(CommonDefinitions.errorStr01);

        pclass   = (byte) ChromaticScale.pitchClass(parts2[0]);
        octave   = (byte)  Integer.parseInt(parts2[1]);
        duration = new Duration(parts1[1]);
    }

    /**
     * Create a new Note
     *
     * @pre 0 <= pclass < 12
     * @pre octave    >= 0
     * @pre nduration >= 1
     * @pre dduration >= 1
     *
     * @param pclass     pitch class index in ChromaticScale
     * @param octave     of note, being 4 the middle octave with A4=440 Hz
     * @param duration   note duration as a fraction
     */
    public Note(int pclass, int octave, Duration duration) {
        this.pclass   = (byte) pclass;
        this.octave   = (byte) octave;
        this.duration = duration;
    }

    public int pitchClass()    { return pclass; }
    public int octave()        { return octave;  }
    public Duration duration() { return duration; }

    @Override
    public MidiNote[] asMidi() {
        return new MidiNote[]{ new MidiNote(this) };
    }

    /**
     * @return Returns the note in scientific pitch notation plus the duration
     *
     * <pitchClass><octave>-<nduration>/<dduration>
     *
     * Eg. A C# form octave 3 with duration 1/4
     *     C#3-1/4
     *
     * Does NOT print dynamic (velocity)
     *
     * https://en.wikipedia.org/wiki/Scientific_pitch_notation
     */
    @Override
    public String toString() {
        return  ChromaticScale.pitchClass(pclass) + octave +
                CommonDefinitions.durationSeparator + duration;
    }
}
