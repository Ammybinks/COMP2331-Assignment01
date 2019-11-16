package UserCode.Behaviours;

import DataStructures.Vector2;
import DataStructures.Collision;

import UserCode.Interfaces.ICollisionManager;

/**
 * Provides functions to check if an object of a given position and size is outside the bounds of the given aquarium
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class CollisionManager implements ICollisionManager
{
    // DECLARE a vector to store the size of the aquarium to check collisions against, call it '_aquariumSize':
    private Vector2<Double> _aquariumSize;

    /**
     * Constructor for objects of class CollisionManager
     *
     * @param aquariumSize        Size of the aquarium to check collisions against
     */
    public CollisionManager(Vector2<Double> aquariumSize)
    {
        // SET: _aquariumSize to parameter aquariumSize
        _aquariumSize = aquariumSize;
    }

    /**
     * METHOD: Check the collision state of an object with the given position and size
     *
     * @param pos       The position of the object being checked
     * @param size      The size of the object being checked
     *
     * @return A wrapper object containing all collision data from the check
     */
    public Collision checkEdges(Vector2<Double> pos, Vector2<Double> size)
    {
        /*
            make a collision object to store collision data in
            check each edge and store the state in the collision object
            if either edge was hit:
                calculate the clamp values to keep the token on screen and store them in the collision object
            return the collision object
        */

        // INSTANTIATE a new Collision object to store collision data:
        Collision collision = new Collision();

        // Get and store collision data for x axis:
        collision._edges._x = checkXEdges(pos._x, size._x);
        // Get and store collision data for y axis:
        collision._edges._y = checkYEdges(pos._y, size._y);

        // IF: a collision was found on the edge checks
        if(collision._edges._x != 0 || collision._edges._y != 0)
        {
            // INSTANTIATE a new empty vector to store clamp data:
            collision._clamp = new Vector2<Double>(0d, 0d);

            // IF: the collision was on a horizontal edge
            if(collision._edges._x != 0)
            {
                // SET: x clamp to the correct value at the edge of the aquarium
                collision._clamp._x = clampX(collision._edges._x, size._x);
            }
            // IF: the collision was on a vertical edge
            if(collision._edges._y != 0)
            {
                // SET: y clamp to the correct value at the edge of the aquarium
                collision._clamp._y = clampY(collision._edges._y, size._y);
            }
        }

        // Return all collision data:
        return collision;
    }
    /**
     * METHOD: Check the collision state of an object with the given position
     *
     * @param pos       The position of the object being checked
     *
     * @return A wrapper object containing all collision data from the check
     */
    public Collision checkEdges(Vector2<Double> pos)
    {
        // Return collision data from the next most specific overload of this method, passing the default values (0, 0) for size:
        return checkEdges(pos, new Vector2<Double>(0d, 0d));
    }

    /**
     * METHOD: Check if an object at the given position with the given width passes either horizontal boundary of the aquarium
     *
     * @param x       X position of the object to check
     * @param width   Width of the object to check
     *
     * @return An integer between -1 and 1 determining which edge, if any, the object is beyond (0 indicates no boundary has been passed)
     */
    private int checkXEdges(double x, double width)
    {
        /*
            if the fish is past the right side (aquarium width):
                return 1 to indicate the right edge was hit
            if the fish is past the left side (0):
                return -1 to indicate the left edge was hit
            otherwise:
                return 0 to indicate no edge was hit
        */

        // IF: the object is beyond the right edge
        if(x > _aquariumSize._x - width)
        {
            // Return 1 (right edge):
            return 1;
        }
        // IF: the object is beyond the left edge
        else if(x < 0 + width)
        {
            // Return -1 (left edge):
            return -1;
        }

        // Return 0 (no edge):
        return 0;
    }
    /**
     * METHOD: Check if an object at the given position with the given height passes either vertical boundary of the aquarium
     *
     * @param y         Y position of the object to check
     * @param height    Height of the object to check
     *
     * @return An integer between -1 and 1 determining which edge, if any, the object is beyond (0 indicates no boundary has been passed)
     */
    private int checkYEdges(double y, double height)
    {
        /*
            if the fish is past the top side (aquarium height):
                return 1 to indicate the top edge was hit
            if the fish is past the bottom side (0):
                return -1 to indicate the bottom edge was hit
            otherwise:
                return 0 to indicate no edge was hit
        */

        // IF: the object is beyond the top edge
        if(y > _aquariumSize._y - height)
        {
            // Return 1 (right edge):
            return 1;
        }
        // IF: the object is beyond the bottom edge
        else if(y < 0 + height)
        {
            // Return -1 (left edge):
            return -1;
        }

        // Return 0 (no edge):
        return 0;
    }

    /**
     * METHOD: Calculate the absolute x position an object of the given width should be placed at when collided with the given horizontal edge
     *
     * @param edge        Integer of either -1 or 1, representing the edge that was hit
     * @param width       Width of the object being checked
     *
     * @return The position to clamp to
     */
    private double clampX(int edge, double width)
    {
        /*
            if left edge was hit:
                return position of left edge (0)
            otherwise (right edge was hit):
                return position of right edge (aquarium width)
        */

        // IF: edge is left edge
        if(edge == -1)
        {
            // Return left edge position + object width:
            return 0 + width;
        }
        // IF: edge is right edge
        else
        {
            // Return right edge position - object width:
            return _aquariumSize._x - width;
        }
    }
    /**
     * METHOD: Calculate the absolute y position an object of the given width should be placed at when collided with the given vertical edge
     *
     * @param edge        Integer of either -1 or 1, representing the edge that was hit
     * @param height      Height of the object being checked
     *
     * @return The position to clamp to
     */
    private double clampY(int edge, double height)
    {
        /*
            if bottom edge was hit:
                return position of bottom edge (0)
            otherwise (top edge was hit):
                return position of top edge (aquarium height)
        */

        // IF: edge is bottom edge
        if(edge == -1)
        {
            // Return bottom edge position + object height:
            return 0 + height;
        }
        // IF: edge is top edge
        else
        {
            // Return top edge position - object height:
            return _aquariumSize._y - height;
        }
    }
}
