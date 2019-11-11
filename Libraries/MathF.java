package Libraries;

import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class MathF here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MathF
{
    /**
     * Constructor for objects of class MathF
     */
    public MathF()
    {
    }

    public static double clampMin(double minValue, double value)
    {
        if(value < minValue)
        {
            value = minValue;
        }
        
        return value;
    }
    
    public static double clampMax(double maxValue, double value)
    {
        if(value > maxValue)
        {
            value = maxValue;
        }
        
        return value;
    }
    
    public static double clamp(double maxValue, double value)
    {
        if(0 > maxValue)
        {
            throw new IllegalArgumentException("Value conflict; upper bounds cannot be lower than 0. Value: " + maxValue);
        }
        
        value = clampMin(0, value);
        value = clampMax(maxValue, value);
        
        return value;
    }
    
    public static double clamp(double minValue, double maxValue, double value)
    {
        if(minValue > maxValue)
        {
            throw new IllegalArgumentException("Value conflict; upper bounds cannot be higher than lower bounds. Values: " + minValue + ", " + maxValue);
        }
        
        value = clampMin(minValue, value);
        value = clampMax(maxValue, value);
        
        return value;
    }
    
    public static double lerp(double p1, double p2, double pos)
    {
        return p1 - ((p1 - p2) * pos);
    }
    
    public static double rLerp(List<Double> p, double pos)
    {
        ArrayList<Double> tP;
        
        while(p.size() > 1)
        {
            tP = new ArrayList<Double>();
            
            for(int i = 0; i < p.size() - 1; i++)
            {
                tP.add(lerp(p.get(i), p.get(i + 1), pos));
            }
            
            p = tP;
        }
        
        return p.get(0);
    }
}
