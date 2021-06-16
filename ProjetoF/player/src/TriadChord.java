/**
 * Implements a group of notes ordered alphabetically starting at a given key
 *
 * @author hdaniel@ualg.pt
 * @version 202103311034
 */
public class TriadChord implements IPlayable {
    static private final int notesInChord = 3;

    static private final byte[] MajorIdxs = {0, 4, 7};
    static private final byte[] minorIdxs = {0, 3, 7};
    static private final byte[] diminishedIdxs = {0, 3, 6};
    static private final byte[] augmentedIdxs = {0, 4, 8};

    int root;
    char quality;
    int octave;
    Duration duration;
    byte[] relativeNotes;

    private MidiNote[] midiNotes() {
        MidiNote[] midiNotes = new MidiNote[notesInChord];

        for (int i = 0; i < notesInChord; ++i) {
            int pclass = (root+relativeNotes[i]) % ChromaticScale.size();
            midiNotes[i] = new MidiNote(new Note(pclass, octave, duration));
        }
        return midiNotes;
    }

    /**
     * Parse a Chord from a string
     *
     * @param s String to be parsed
     */
    public TriadChord(String s) {

        int qualIdx;

        //Split pitch class, octave and quality from duration
        String[] parts = s.split(CommonDefinitions.durationSeparator);
        if (parts.length != 2)
            throw new IllegalArgumentException(CommonDefinitions.errorStr13);

        //Get pitch class quality
        try {
            if ((parts[0].substring(1,2)).equals(CommonDefinitions.sharpSymbol)) qualIdx = 2;
            else qualIdx = 1;
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(CommonDefinitions.errorStr13);
        }

        //Pitch class
        try {
            root = ChromaticScale.pitchClass(parts[0].substring(0, qualIdx));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(CommonDefinitions.errorStr10);
        }

        //Quality (recast error)
        try {
            quality = parts[0].charAt(qualIdx);
            if (quality == CommonDefinitions.qualityMajor.charAt(0)) relativeNotes =MajorIdxs;
            else if (quality == CommonDefinitions.qualityMinor.charAt(0)) relativeNotes =minorIdxs;
            else if (quality == CommonDefinitions.qualityDiminished.charAt(0)) relativeNotes =diminishedIdxs;
            else if (quality == CommonDefinitions.qualityAugmented.charAt(0)) relativeNotes =augmentedIdxs;
            //sus2
            //sus4
            else throw new IllegalArgumentException(CommonDefinitions.errorStr11);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(CommonDefinitions.errorStr11);
        }

        //get octave
        try {
            octave = Integer.parseInt(s.substring(qualIdx+1,qualIdx+2));
        }
        catch (Exception e) {
            throw new IllegalArgumentException(CommonDefinitions.errorStr01);
        }

        //get duration
        duration = new Duration(parts[1]);
    }


    @Override
    public MidiNote[] asMidi() { return midiNotes(); }


    @Override
    public String toString() {
        return  ChromaticScale.pitchClass(root) + quality + octave +
                CommonDefinitions.durationSeparator + duration;
    }

}