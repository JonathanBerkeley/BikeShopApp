package utils;
/**
 *
 * @author N00181859
 */
import java.util.Random;
public class Rangedrandom {
    public static int genInt(int min, int max, Random r) { //Generates pseudorandom numbers between the range of min and max
        return r.nextInt((max - min) + 1) + min;
    }
    public static double genDouble(int min, int max, Random r){ //Generates pseudorandom double between min and max with double value
        double appended = Math.round((r.nextDouble()) * 100.0) / 100.0;
        return (r.nextInt((max - min) + 1) + min) + appended;
    }
}
