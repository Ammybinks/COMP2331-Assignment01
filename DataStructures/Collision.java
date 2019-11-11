package DataStructures;


/**
 * Write a description of class Collision here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Collision
{
    public Vector2<Integer> _edges;
    public Vector2<Double> _clamp;

    /**
     * Constructor for objects of class Collision
     */
    public Collision()
    {
        _edges = new Vector2<Integer>(0, 0);
    }
    public Collision(Vector2<Integer> edges, Vector2<Double> clamp)
    {
        _edges = edges;
        _clamp = clamp;
    }
}
