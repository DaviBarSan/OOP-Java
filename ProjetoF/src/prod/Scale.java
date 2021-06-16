package prod;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Scale {
    public Note[] currScaleNotes;
    public Chord[] currScaleChords;
    public Chord[] progression;
    public int scaleQuality;
    public Note rootNote;

    /**
     *
     * @param currScaleNotes
     * @param quality
     * @post Instantiated Scale with respective attributes
     */
    public Scale(Note[] currScaleNotes, int quality){
        this.currScaleNotes = currScaleNotes;
        this.scaleQuality = quality;
        this.rootNote = currScaleNotes[0];
        this.currScaleChords = setCurrScaleChords();
    }

    /**
     * @return Note[] currScaleNotes attribute, from current scale;
     */
    public Note[] notes(){
        return currScaleNotes;
    }

    /**
     *
     * @return formatted String from current scale of notes;
     */
    public String arrayOfNotesToString(){
        String joined = Arrays.stream(currScaleNotes)
                .map( note -> note.pitchClass)
                .collect(Collectors.joining(" "));
        return joined;
    }

    /**
     *
     * @return String with formatted name of scale, defined by root note and it's quality
     */
    public String name(){
        String resultName = rootNote.pitchClass;
        if (scaleQuality == 0) return resultName += " minor";
        return resultName += " major";
    }

    /**
     *
     * @return Chord[] using the scale quality patterns and current scale of Notes, generates ScaleOfChords
     */
    private Chord[] setCurrScaleChords() {
        this.currScaleChords = new Chord[7];
        final int[] minorPatternChord = {0,2,1,0,0,1,1};
        final int[] majorPatternChord = {1,0,0,1,1,0,2};
        int patternIdx;
        for (int i = 0; i < 7; i++) {
            if (scaleQuality == 0){
                patternIdx = minorPatternChord[i];
                currScaleChords[i] = new Chord(currScaleNotes[i], patternIdx);
            }
            else {
                patternIdx = majorPatternChord[i];
                currScaleChords[i] = new Chord(currScaleNotes[i], patternIdx);
            }
        }
        return currScaleChords;
    }

    /**
     *
     * @return formatted String from current scale of Chords
     */
    public String arrayOfChordsToString(){
        String resultJoined = Arrays.stream(currScaleChords)
                .map(currChord -> currChord.getNoteAndQuality())
                .collect(Collectors.joining(" "));

        return resultJoined;
    }

    /**
     *
     * @param noChords int
     * @param currSeed long
     * @post set the progression of Chords for current Scale
     */
    public void setProgression(int noChords, long currSeed){
        this.progression = new Chord[noChords];
        for (int i = 0; i < noChords; i++){
            int chordIdx = (int) currSeed % 7;
            currSeed = currSeed / 3;
            this.progression[i] = currScaleChords[chordIdx];
        }
    }

    /**
     *
     * @return formatted String from current progression of chords;
     */
    public String progressionToString(){
        String result = Arrays.stream(progression)
                .map(currChordInProg -> currChordInProg.getNoteAndQuality())
                .collect(Collectors.joining(" "));
        return result;
    }
}
