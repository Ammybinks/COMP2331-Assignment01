package Libraries;

import java.util.List;
import java.util.ArrayList;

/**
 * The MathFish class contains a variety of mathematical functions commonly used throughout the program
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class MathF
{
    /**
     * Constructor for objects of class MathF
     */
    public MathF()
    {
    }

    /**
     * METHOD: Clamp a value to a given negative bound
     *
     * @param minValue      The negative bound to clamp to
     * @param value         The value to clamp
     *
     * @return The clamped value
     */
    public static double clampMin(double minValue, double value)
    {
        /*
            if the value is less than the minimum:
                the value is equal to the minimum
            return the clamped value
        */

        // IF: the value is less than the given bound
        if(value < minValue)
        {
            // SET: value to bound
            value = minValue;
        }

        // Return value
        return value;
    }

    /**
     * METHOD: Clamp a value to a given positive bound
     *
     * @param maxValue      The positive bound to clamp to
     * @param value         The value to clamp
     *
     * @return The clamped value
     */
    public static double clampMax(double maxValue, double value)
    {
        /*
            if the value is less than the maximum:
                the value is equal to the maximum
            return the clamped value
        */

        // IF: the value is more than the given bound
        if(value > maxValue)
        {
            // SET: value to bound
            value = maxValue;
        }

        // Return value
        return value;
    }

    /**
     * METHOD: Clamp a value to a given positive bound
     *
     * @param maxValue      The positive bound to clamp to
     * @param value         The value to clamp
     *
     * @return The clamped value
     */
    public static double clamp(double maxValue, double value)
    {
        return clamp(0, maxValue, value);
    }

    /**
     * METHOD: Clamp a value to given positive and negative bounds
     *
     * @param minValue      The negative bound to clamp to
     * @param maxValue      The positive bound to clamp to
     * @param value         The value to clamp
     *
     * @throws IllegalArgumentException     If upper and lower bounds result in a range of less than 0
     *
     * @return The clamped value
     */
    public static double clamp(double minValue, double maxValue, double value)
    {
        /*
            if minimum and maximum values overlap:
                throw an exception -- otherwise value will always be equal to the maximum
            if the value is less than the minimum:
                the value is equal to the minimum
            if the value is less than the maximum:
                the value is equal to the maximum
            return the clamped value
        */

        // IF: minimum and maximum bounds overlap
        if(minValue > maxValue)
        {
            // Throw an IllegalArgumentException:
            throw new IllegalArgumentException("Value conflict; upper bounds must be higher than lower bounds. Values: " + minValue + ", " + maxValue);
        }

        // Clamp the given value to the negative bound:
        value = clampMin(minValue, value);
        // Clamp the given value to the positive bound:
        value = clampMax(maxValue, value);

        return value;
    }

    /**
     * METHOD: Linearly interpolate to a value a given distance between two given values
     *
     * @param p1      The first value to interpolate between
     * @param p2      The second value to interpolate between
     * @param pos     The position between the two points as a value between 0 and 1, with 0 representing p1 and 1 representing p2
     *
     * @return The value a distance along the line (p1, p2) of pos
     */
    public static double lerp(double p1, double p2, double pos)
    {
        /*
            interpolate across a line given by p1 and p2 by scaling the difference according to pos and subtracting it from p1, return the result
        */

        return p1 - ((p1 - p2) * pos);
    }

    /**
     * METHOD: Recursively linearly interpolate between each value in the given list to find the value the given distance between each of them
     *
     * @param p     The list of vales to interpolate between
     * @param pos   The distance along each line as a value between 0 and 1, with 0 representing the start of each set of points and 1 representing the end
     *
     * @return The value interpolated pos distance between each point in p
     */
    public static double rLerp(List<Double> p, double pos)
    {
        /*
            while there's more than one value to interpolate together:
                create a list to temporarily store the values
                for each value but the last:
                    interpolate between the current value and the next one and add it to the temporary list, compressing two values into one interpolated value to interpolate further
                set the global list to the temporary list
              return the single value remaining in the global list
        */

        // DECLARE an array list to store temporary values while iterating over the given list, call it 'tP':
        ArrayList<Double> tP;

        // WHILE: there are more values to interpolate between
        while(p.size() > 1)
        {
            // INSTANTIATE a new array list for temporary values:
            tP = new ArrayList<Double>();

            // FOR: each value in p but the final one
            for(int i = 0; i < p.size() - 1; i++)
            {
                // Interpolate between the current value and the next one, store it in tP:
                tP.add(lerp(p.get(i), p.get(i + 1), pos));
            }

            // SET: p to tP
            p = tP;
        }
        // After looping, p should hold only a single value

        // RETURN the remaining value of p:
        return p.get(0);
    }
}
