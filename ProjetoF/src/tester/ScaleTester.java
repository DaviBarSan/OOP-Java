package tester;

import org.junit.jupiter.api.Test;
import prod.CircleOfFifths;
import prod.Scale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleTester {

    @Test
    public void testAProgression(){
        int quality = 1;
        int scaleIdx = 5;
        int noChords = 5;
        long currSeed = 453077;
        Scale currScale = CircleOfFifths.getScale(scaleIdx, quality);
        currScale.setProgression(5,currSeed);
        assertEquals("Am FM CM Gm FM", currScale.progressionToString());
    }

    @Test
    public void testAProgression1(){
        int quality = 0;
        int scaleIdx = 4;
        int noChords = 3;
        long currSeed = 15232617;
        Scale currScale = CircleOfFifths.getScale(scaleIdx, quality);
        currScale.setProgression(noChords,currSeed);
        assertEquals("F#o CM Bm", currScale.progressionToString());
    }

}
