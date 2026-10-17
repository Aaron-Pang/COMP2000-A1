package supplementary;

import java.util.Random;
import java.util.stream.IntStream;

public class RandGenerator {
    static IntStream s = new Random().ints(0, 100);

    public RandGenerator() {
    }

    public static int getRandomInt() {
        return s.findFirst().getAsInt();
    }
}
