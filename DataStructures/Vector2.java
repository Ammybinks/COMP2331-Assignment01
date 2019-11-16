package DataStructures;


/**
 * Stores a set of set of values, each defined by a different axis on a two-dimensional plane
 *
 * @author Nye Blythe
 * @version 1.1
 */
public class Vector2<T>
{
    // DECLARE data member of given type <T> to store the x value, call it '_x':
    public T _x;
    // DECLARE data member of given type <T> to store the y value, call it '_y':
    public T _y;

    /**
     * Constructor for objects of class Vector2
     */
    public Vector2()
    {
    }
    /**
     * Constructor for objects of class Vector2
     *
     * @param x     The x value to store
     * @param y     The y value to store
     */
    public Vector2(T x, T y)
    {
        // SET: values to parameters
        set(x, y);
    }

    /**
     * METHOD: Set the values in this vector to new ones
     *
     * @param x     The new x value
     * @param y     The new y value
     */
    public void set(T x, T y)
    {
        // SET: data members to parameters
        // _x:
        _x = x;
        // _y:
        _y = y;
    }
}
