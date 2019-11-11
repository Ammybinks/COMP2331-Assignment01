package UserCode.Behaviours;

import DataStructures.Vector2;
import DataStructures.Collision;

import UserCode.Interfaces.ICollisionManager;

/**
 * Write a description of class CollisionManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CollisionManager implements ICollisionManager
{
    private Vector2<Double> _aquariumSize = new Vector2<Double>(10d, 7.8d);

    /**
     * Constructor for objects of class CollisionManager
     */
    public CollisionManager(Vector2<Double> aquariumSize)
    {
        _aquariumSize = aquariumSize;
    }
    
    public Collision checkEdges(Vector2<Double> pos, Vector2<Double> size)
    {
        Collision collision = new Collision();
        
        collision._edges._x = checkXEdges(pos._x, size._x);
        collision._edges._y = checkYEdges(pos._y, size._y);
        
        if(collision._edges._x != 0 || collision._edges._y != 0)
        {
            collision._clamp = new Vector2<Double>(0d, 0d);
            
            if(collision._edges._x != 0)
            {
                collision._clamp._x = clampX(collision._edges._x, size._x);
            }
            if(collision._edges._y != 0)
            {
                collision._clamp._y = clampY(collision._edges._y, size._y);
            }
        }
        
        return collision;
    }
    public void checkEdges(Vector2<Double> pos)
    {
        checkEdges(pos, new Vector2<Double>(0d, 0d));
    }
    
    private int checkXEdges(double x, double width)
    {
        int result = 0;
        
        if(x > _aquariumSize._x - width)
        {
            result = 1;
        }
        else if(x < 0 + width)
        {
            result = -1;
        }
        
        return result;
    }
    private int checkYEdges(double y, double height)
    {
        int result = 0;
        
        if(y > _aquariumSize._y - height)
        {
            result = 1;
        }
        else if(y < 0 + height)
        {
            result = -1;
        }
        
        return result;
    }
    
    private double clampX(int edge, double width)
    {
        if(edge == -1)
        {
            return 0 + width;
        }
        else
        {
            return _aquariumSize._x - width;
        }
    }
    private double clampY(int edge, double height)
    {
        if(edge == -1)
        {
            return 0 + height;
        }
        else
        {
            return _aquariumSize._y - height;
        }
    }
}
