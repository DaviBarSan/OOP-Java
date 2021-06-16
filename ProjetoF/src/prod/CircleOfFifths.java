package prod;

public class CircleOfFifths {
    private static final int[] minorPattern = {0, 2, 9, 11, 1, 8, 10};
    private static final int[] majorPattern = {0, 2, 4, 11, 1, 3, 5};
    private static final String[] KEY_FINDER = {"C","C#","D","D#","E", "F", "F#", "G","G#","A","A#","B"};
    public static final String[] CIRCLE_OF_FIFHTS = {"C", "G", "D", "A", "E", "B", "F#", "C#", "G#", "D#", "A#", "F"};


    public static Scale getScale(int idxScale, int quality){
        Note[] currNoteScale = new Note[7];
        int patternIdx, circularIdx;
        String key = KEY_FINDER[idxScale];
        int keyIdxInCircleOfFifhts = 0;

        //finding the key index in circle of fifhts to use major and minor patterns
        for (int i = 0; i < CIRCLE_OF_FIFHTS.length; i++){
            if (CIRCLE_OF_FIFHTS[i] == key) keyIdxInCircleOfFifhts = i;
        }
        for(int i = 0; i < 7; i++){
            //minor = 0; major = 1;
            if (quality == 0) patternIdx = minorPattern[i];
            else patternIdx = majorPattern[i];
            circularIdx = (keyIdxInCircleOfFifhts + patternIdx) % KEY_FINDER.length;
            Note currNote = new Note(CIRCLE_OF_FIFHTS[circularIdx]);
            currNoteScale[i] = currNote;
        }
        return new Scale(currNoteScale, quality);
    }

}
