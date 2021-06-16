package tester;

import org.junit.jupiter.api.Test;
import prod.CircleOfFifths;
import prod.Scale;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CircleOfFifthsTest {

    @Test
    public void testGenerateAScaleOfNotes0(){
        int scaleIdx = 5;
        int quality = 1;
        Scale currScale = (CircleOfFifths.getScale(scaleIdx,quality));
        assertEquals("F G A A# C D E", currScale.arrayOfNotesToString());
        assertEquals("F major", currScale.name());
    }

    @Test
    public void testGenerateAScaleOfNotes1(){
        int scaleIdx = 4;
        int quality = 0;
        Scale currScale = (CircleOfFifths.getScale(scaleIdx,quality));
        assertEquals("E F# G A B C D", currScale.arrayOfNotesToString());
        assertEquals("E minor", currScale.name());
    }
    @Test
    public void testGenerateAScaleOfNotes2(){
        int scaleIdx = 0;
        int quality = 0;
        Scale currScale = (CircleOfFifths.getScale(scaleIdx,quality));
        assertEquals("C D D# F G G# A#", currScale.arrayOfNotesToString());
        assertEquals("C minor", currScale.name());
    }

    @Test
    public void testGenerateAScaleOfChords0(){
        int scaleIdx = 5;
        int quality = 1;
        Scale currScale = (CircleOfFifths.getScale(scaleIdx,quality));
        assertEquals("FM Gm Am A#M CM Dm Eo", currScale.arrayOfChordsToString());
    }

    @Test
    public void testGenerateAScaleOfChords1(){
        int scaleIdx = 4;
        int quality = 0;
        Scale currScale = (CircleOfFifths.getScale(scaleIdx,quality));
        assertEquals("Em F#o GM Am Bm CM DM", currScale.arrayOfChordsToString());
    }

    @Test
    public void testGenerateAScaleOfChords2(){
        int scaleIdx = 0;
        int quality = 0;
        Scale currScale = (CircleOfFifths.getScale(scaleIdx,quality));
        assertEquals("Cm Do D#M Fm Gm G#M A#M", currScale.arrayOfChordsToString());
    }




}
