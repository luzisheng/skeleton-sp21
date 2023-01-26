package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound
 * @author Mr. Lu
 */
public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int KEYS_AMOUNT = KEYBOARD.length();

    public static void main(String[] args) {
        GuitarString[] guitarStrings = new GuitarString[KEYS_AMOUNT];
        /* create guitar strings*/

        for (int i = 0; i < KEYS_AMOUNT; i += 1) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int indexOfKey = KEYBOARD.indexOf(key);

                if (indexOfKey >= 0) {
                    guitarStrings[indexOfKey].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = .0;
            for (GuitarString x : guitarStrings) {
                sample += x.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString x : guitarStrings) {
                x.tic();
            }
        }
    }
}

