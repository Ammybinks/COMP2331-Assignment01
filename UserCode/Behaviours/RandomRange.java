package UserCode.Behaviours;

import java.util.Random;

/**
 * Wrapper class for java.util.Random, adding functionality to easily randomise numbers between two values
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class RandomRange extends Random
{
    /**
     * Constructor for objects of class RandomRange
     */
    public RandomRange()
    {
        // Call constructor of parent Random()
        super();
    }
    public RandomRange(long seed)
    {
        // Call constructor of parent Random(), passing the value of given seed
        super(seed);
    }

    /**
     * METHOD: Returns the next pseudorandom, uniformly distributed int value between the two given values from this random number generator's sequence
     *
     * @param min       Inclusive minimum value of the range
     * @param max       Exclusive maximum value of the range
     *
     * @return The next pseudorandom, uniformly distributed int value between the two given values from this random number generator's sequence
     * @see java.util.Random.nextInt()
     */
    public double rangeInt(int min, int max)
    {
        return nextInt(max - min) + min;
    }
    /**
     * METHOD: Returns the next pseudorandom, uniformly distributed double value between the two given values from this random number generator's sequence
     *
     * @param min       Inclusive minimum value of the range
     * @param max       Exclusive maximum value of the range
     *
     * @return The next pseudorandom, uniformly distributed double value between the two given values from this random number generator's sequence
     * @see java.util.Random.nextDouble()
     */
    public double rangeDouble(double min, double max)
    {
        return nextDouble() * (max - min) + min;
    }
}
