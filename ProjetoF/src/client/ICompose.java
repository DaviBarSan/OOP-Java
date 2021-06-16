package client;

import prod.CircleOfFifths;
import prod.Scale;

public interface ICompose {

    String compose(long seed);
    Scale scale();

    /**
     *
     * @param seed
     * @return generated Scale, from common steps in both strategies
     */
    static Scale fromSeedToScaleAndProgression(long seed){
        int quality = (int) seed % 2;
        int scaleIdx = (int) seed % 12;
        seed = seed/3;
        Scale currScale = CircleOfFifths.getScale(scaleIdx, quality);
        int noChords = (int) (seed % 3)+3;
        currScale.setProgression(noChords, seed);
        return currScale;
    }
}
