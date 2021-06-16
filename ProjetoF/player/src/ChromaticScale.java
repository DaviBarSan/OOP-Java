/**
 * Implements a Chromatic scale with 12 pitch classes
 * (sometimes pitch classes are addressed as notes but notes have octave and duration specified)
 * sorted as:
 *
 *  "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"
 *
 * @author hdaniel@ualg.pt
 * @version 202103291258
 */
public class ChromaticScale {
    static private final String[] pitchClassNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    /**
     * @param name name of pitch class
     * @return the idx of pitchClass name in the Chromatic scale
     */
    static public int pitchClass(String name) {
        int i=0;
        for (; i < size(); i++)
            if(pitchClassNames[i].equals(name)) break;

        if (i >= pitchClassNames.length)
            throw new IllegalArgumentException("pitch class '" + name + "' not known");

        return i;
    }

    /**
     * @pre  0 <= idx <= size()
     *
     * @param idx index of pitch class in ChromaticScale
     * @return
     */
    static public String pitchClass(int idx) {
        return pitchClassNames[idx];
    }

    /**
     * @return number of notes in ChromaticScale
     */
    static int size() { return pitchClassNames.length; }
}

