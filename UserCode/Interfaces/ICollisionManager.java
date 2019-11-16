package UserCode.Interfaces;

import DataStructures.Vector2;
import DataStructures.Collision;

/**
 * Interface attatched to the CollisionManager class providing references needed for use by collidable objects
 *
 * @author Nye Blythe
 * @version 1.0
 */
public interface ICollisionManager
{
    Collision checkEdges(Vector2<Double> pos, Vector2<Double> size);
}
