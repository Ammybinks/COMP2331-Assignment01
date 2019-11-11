package DataStructures;


/**
 * Write a description of class Vector2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vector2<T>
{
    public T _x;
    public T _y;
    
    /**
     * Constructor for objects of class Vector2
     */
    public Vector2()
    {
    }
    public Vector2(T x, T y)
    {
        set(x, y);
    }
    
    public void set(T x, T y)
    {
        _x = x;
        _y = y;
    }
}
