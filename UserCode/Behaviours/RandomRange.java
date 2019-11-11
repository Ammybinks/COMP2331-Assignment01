package UserCode.Behaviours;

import java.util.Random;

/**
 * Write a description of class RandomRange here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomRange extends Random
{
    /**
     * Constructor for objects of class RandomRange
     */
    public RandomRange()
    {
        super();
    }
    public RandomRange(long seed)
    {
        super(seed);
    }

    public double rangeDouble(double min, double max)
    {
        return nextDouble() * (max - min) + min;
    }
}
