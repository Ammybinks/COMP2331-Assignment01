package UserCode;

import Framework.DisplayObject;

import DataStructures.Vector2;

import UserCode.Behaviours.RandomRange;

/**
 * Write a description of class Urchin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Urchin extends Fish
{
    public Urchin(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        super("models/billboard/billboard.obj", "textures/javaFish/Urchin.png", rand, pos, aquariumSize);
        
        rotateX = 0;
        rotateY = 270;
        rotateZ = 0;
        
        _size.set(0.9d, 0.8d);
        
        _speed.set(0.05d, 0d);
        _startSpeed.set(0.03d, 0d);
        _maxSpeed.set(0.05d, 0d);
        
        _direction.set(1, 0);
    }
    
    protected void setXDirection(int newDirection)
    {
        _direction._x = newDirection;
    }
}
