package client;

import prod.Chord;
import prod.Note;
import prod.Scale;

public class ComposeB implements ICompose {
    Scale currScale;
    String lick;


    @Override
    public String compose(long seed) {
        Scale currScale = ICompose.fromSeedToScaleAndProgression(seed);
        int noChords = currScale.progression.length;
        this.currScale = currScale;
        int progIdx = 0;
        int octave = 4;
        String result = "";
        while (true) {
            int duration = (int) Math.pow(2, (seed % 3) + 1);
            seed = seed / 3;
            Chord currChord = currScale.progression[progIdx];
            currChord.setDuration(duration);
            currChord.setOctave(octave);
            if (++progIdx >= noChords) progIdx = 0;
            result += currChord.toString() + " ";
            int noNotes = (int) seed % 4;
            //iterates over notes and generates pseudo-randomly notes-octaves-durations from progression chords;
            for (int i = 0; i < noNotes; ++i) {
                //stop iteration and sets the final lick;
                if (seed < 10) return lick = result.trim();
                int noteIdx = (int) seed % 7;
                int noteDuration = (int) Math.pow(2, (seed % 3) + 1);
                seed = seed / 3;
                Note currNote = currScale.notes()[noteIdx];
                currNote.setDuration(noteDuration);
                currNote.setOctave(octave);
                result += currNote.toString() + " ";
            }
        }
    }
    @Override
    public Scale scale() {
        return currScale;
    }
}
