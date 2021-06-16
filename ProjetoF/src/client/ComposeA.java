package client;

import prod.Chord;
import prod.Scale;

public class ComposeA implements ICompose{
    private Scale currScale;
    private String lick;

    @Override
    public String compose(long seed) {
        Scale currScale = ICompose.fromSeedToScaleAndProgression(seed);
        int noChords = currScale.progression.length;
        this.currScale = currScale;
        int progIdx = 0;
        int octave = 4;
        String result = "";
        while (true){
            if (seed < 10) break;
            int duration = (int) Math.pow(2,(seed % 3)+1);
            seed = seed/3;
            Chord chord = currScale.progression[progIdx];
            chord.setDuration(duration);
            chord.setOctave(octave);
            if(++progIdx >= noChords) progIdx = 0;
            result += chord.toString() + " ";
        }
        this.lick = result.trim();
        return lick;
    }

    @Override
    public Scale scale() {
        return currScale;
    }
}
