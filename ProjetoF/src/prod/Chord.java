package prod;

public class Chord {
    Note rootNote;
    int quality;
    int octave;
    int duration;

    public Chord(Note rootNote, int quality){
        this.rootNote = rootNote;
        this.quality = quality;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        rootNote.setDuration(duration);
    }
    public void setOctave(int octave) {
        this.octave = octave;
        rootNote.setOctave(octave);
    }

    public String getNoteAndQuality() {
        String major = rootNote.pitchClass + "M";
        String minor = rootNote.pitchClass + "m";
        String dimished = rootNote.pitchClass + "o";
        if (quality == 1) return major;
        else if (quality == 2) return dimished;
        return minor;
    }

    @Override
    public String toString() {
        return getNoteAndQuality() + octave + "-1/" + duration;
    }


}
