import javax.sound.midi.*;
import java.util.Scanner;

/**
 * @author hdaniel@ualg.pt
 * @version 202104031110
 */
public class MidiPlayer {
    /**
     * Tempo by default:
     * <p>
     * 120 bpm for a 1/4 note (seminima)
     * 1/1 note has duration 2 secs
     * 1/2 note has duration 1 secs
     * 1/4 note has duration 0.5 secs
     * 1/8 note has duration 0.25 secs
     * (...)
     * time sig: 4/4 -> four 1/4 notes in one measure
     */
    static private double tempo = 2000;  //2000 for 120 BPMs (1/4 note 500 ms)
    //static private double tempo = 1000;  //1000 for 60 BPMs (1/4 note 250 ms)

    static private int arpeggioDelay = 50;  //ms
    static private int[] instruments = {0, 25}; //Piano, Steel Guitar


    /**
     * Simple constructor
     *
     * @param sequence Tune to play
     * @param verbose  if true print note names and duration
     */
    static public void play(Sequence sequence, int instrument, boolean arpeggio, boolean verbose)
            throws InterruptedException, MidiUnavailableException {

        Synthesizer midiSynth = MidiSystem.getSynthesizer();
        midiSynth.open();

        //load default instrument and channel lists
        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
        MidiChannel[] mChannels = midiSynth.getChannels();

        //Select an instrument
        if (mChannels[0] != null) {
            //selecting: bank, instrument number
            //mChannels[0].programChange(0, 25);
            //or:
            mChannels[0].programChange(instr[instrument].getPatch().getProgram());

            //play single note or chord
            for (IPlayable playable : sequence) {
                MidiNote[] midiNotes = playable.asMidi();

                //duration is the one of root note
                long duration = (long) (tempo * midiNotes[0].duration());

                //write
                if (verbose) System.out.print(playable + " ");

                //Start play
                for (MidiNote midiNote : midiNotes) {
                    mChannels[0].noteOn(midiNote.pitch(), midiNote.velocity()); //play midiNote
                    if (arpeggio) Thread.sleep(arpeggioDelay);
                }

                //Wait for root duration
                Thread.sleep(duration);  //duration in ms

                //turn of all notes
                mChannels[0].allNotesOff();
                //could also turn one by one:
                //for (MidiNote midiNote : midiNotes)
                //    mChannels[0].noteOff(midiNote.pitch());
            }
        }
    }

    /**
     *   Run player from command line
     */
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        String instrumentName = sc.nextLine();
        String riff = sc.nextLine();
        Sequence seq = new Sequence(riff);

        //by default play guitar
        int instrument = 1;
        boolean arpeggio = true;
        if (instrumentName.equalsIgnoreCase("piano")) {
            instrument = 0;
            arpeggio = false;
        }

        //Play and show notes while playing
        play(seq, instrument, arpeggio, true);
    }
}
