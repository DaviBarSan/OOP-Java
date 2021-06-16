package tester;

import client.ComposeA;
import client.ComposeB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComposersTester {

    @Test
    public void TestComposeA0(){
        ComposeA composeA = new ComposeA();
        assertEquals("Am4-1/8 FM4-1/8 CM4-1/8 Gm4-1/4 FM4-1/4 Am4-1/4 FM4-1/4 CM4-1/2 Gm4-1/2 FM4-1/2 Am4-1/8",composeA.compose(1359233));
    }

    @Test
    public void TestComposeA1(){
        ComposeA composeA = new ComposeA();
        assertEquals("Eo4-1/8 Dm4-1/4 FM4-1/4 Gm4-1/2 Eo4-1/2 Dm4-1/4 FM4-1/2 Gm4-1/2 Eo4-1/2 Dm4-1/8 FM4-1/2 Gm4-1/4",composeA.compose(2342534));
    }

    @Test
    public void TestFullOutputComposeA0(){
        ComposeA composeA = new ComposeA();
        String lick = composeA.compose(2342534);
        assertEquals("D minor", composeA.scale().name());
        assertEquals("D E F G A A# C", composeA.scale().arrayOfNotesToString());
        assertEquals("Eo Dm FM Gm", composeA.scale().progressionToString());
        assertEquals("Eo4-1/8 Dm4-1/4 FM4-1/4 Gm4-1/2 Eo4-1/2 Dm4-1/4 FM4-1/2 Gm4-1/2 Eo4-1/2 Dm4-1/8 FM4-1/2 Gm4-1/4",lick);
    }

    @Test
    public void TestFullOutputComposeA1(){
        ComposeA composeA = new ComposeA();
        String lick = composeA.compose(1636322);
        assertEquals("D minor", composeA.scale().name());
        assertEquals("D E F G A A# C", composeA.scale().arrayOfNotesToString());
        assertEquals("Dm FM A#M CM", composeA.scale().progressionToString());
        assertEquals("Dm4-1/8 FM4-1/4 A#M4-1/4 CM4-1/4 Dm4-1/8 FM4-1/4 A#M4-1/2 CM4-1/4 Dm4-1/2 FM4-1/8 A#M4-1/2",lick);
    }

    @Test
    public void TestComposeB0(){
        ComposeB composeB = new ComposeB();
        String lick = composeB.compose(12);
        assertEquals("Gm4-1/2 Do4-1/4", lick);
    }
    @Test
    public void TestComposeB1(){
        ComposeB composeB = new ComposeB();
        String lick = composeB.compose(759854);
        assertEquals("Gm4-1/8 Eo4-1/2 FM4-1/8 F4-1/8 D4-1/8 Gm4-1/2 C4-1/4 A4-1/8 Eo4-1/4 G4-1/8 A#4-1/2 FM4-1/4", lick);
    }

    @Test
    public void TestFullOutputComposeB0(){
        ComposeB composeB = new ComposeB();
        String lick = composeB.compose(672526734);
        assertEquals("F# minor", composeB.scale().name());
        assertEquals("F# G# A B C# D E", composeB.scale().arrayOfNotesToString());
        assertEquals("C#m Bm Bm G#o AM", composeB.scale().progressionToString());
        assertEquals("C#m4-1/2 C#4-1/8 B4-1/4 Bm4-1/2 G#4-1/8 A4-1/2 B4-1/2 Bm4-1/8 A4-1/8 F#4-1/2 F#4-1/4 G#o4-1/4 D4-1/8 AM4-1/4 C#m4-1/8 C#4-1/4 G#4-1/2 Bm4-1/8", lick);
    }

    @Test
    public void TestFullOutputComposeB1(){
        ComposeB composeB = new ComposeB();
        String lick = composeB.compose(759854);
        assertEquals("D minor", composeB.scale().name());
        assertEquals("D E F G A A# C", composeB.scale().arrayOfNotesToString());
        assertEquals("Gm Eo FM", composeB.scale().progressionToString());
        assertEquals("Gm4-1/8 Eo4-1/2 FM4-1/8 F4-1/8 D4-1/8 Gm4-1/2 C4-1/4 A4-1/8 Eo4-1/4 G4-1/8 A#4-1/2 FM4-1/4", lick);
    }
}
