package DataStructures;


/**
 * Stores all relevant data for a collision between a display object and the edges of the aquarium
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class Collision
{
    // DECLARE a vector to store the state of collision with each edge, call it '_edges':
    // An edge is defined by an axis and integer value between -1 and 1, where the value determines if either the positive or negative bounds have been passed, if any
    public Vector2<Integer> _edges;
    // DECLARE a vector to store the clamp values for each edge collided with, call it '_clamp':
    // Clamp values will either be 0 or the position along the axis to clamp to, set only if an edge was passed
    public Vector2<Double> _clamp;

    /**
     * Constructor for objects of class Collision
     */
    public Collision()
    {
        // INITIALISE _edges to the default values (0, 0):
        _edges = new Vector2<Integer>(0, 0);
    }
    /**
     * Constructor for objects of class Collision
     *
     * @param edges          The current state of the edges in the collision for each axis
     * @param clamp          The clamp values for each axis
     */
    public Collision(Vector2<Integer> edges, Vector2<Double> clamp)
    {
        // SET: data members to parameters
        // _edges:
        _edges = edges;
        // _clamp:
        _clamp = clamp;
    }
}
