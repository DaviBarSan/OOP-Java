package prod;

public class Note {
    String pitchClass;
    int octave;
    int duration;

    public Note(String currNote, int octave, int duration){
        this.pitchClass = currNote;
        this.octave = 0;
        this.duration = 0;
    }

    public Note(String currNote) {
        this.pitchClass = currNote;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return pitchClass + octave + "-1/" +duration;
    }
}
