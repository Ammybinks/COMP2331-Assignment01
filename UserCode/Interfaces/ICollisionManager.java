package UserCode.Interfaces;

import DataStructures.Vector2;
import DataStructures.Collision;

/**
 * Write a description of interface ICollisionManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface ICollisionManager
{
    Collision checkEdges(Vector2<Double> pos, Vector2<Double> size);
}
