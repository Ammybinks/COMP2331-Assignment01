package UserCode;

import Framework.DisplayObject;

import DataStructures.Vector2;

import UserCode.Behaviours.RandomRange;

/**
 * The Urchin moves back and forth across the screen, randomly decelerating to a stop before starting again. Display of the Urchin doesn't change depending on its direction of movement
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class Urchin extends Fish
{
    /**
     * Constructor for objects of class Urchin
     *
     * @param rand          Reference to an instance of a random object
     * @param pos           The initial position of the fish
     * @param aquariumSize  The size of the aquarium the fish is being placed in
     */
    public Urchin(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        // Pass rand, pos and aquariumSize to parent Fish() including default values for modelPath and texturePath (passing path to Urchin texture):
        super(rand, pos, aquariumSize, "models/billboard/billboard.obj", "textures/javaFish/Urchin.png");

        // SET: _size to default values to match texture
        _size.set(0.9d, 0.8d);

        // SET: speed values to defaults for Urchin movement
        // To move the fish horizontally along the aquarium, vertical speed values should always be 0
        // _speed:
        _speed.set(0.05d, 0d);
        // _startSpeed:
        _startSpeed.set(0.03d, 0d);
        // _maxSpeed:
        _maxSpeed.set(0.05d, 0d);

        // SET: _direction to default values (1, 0) to begin the fish moving right
        _direction.set(1, 0);
    }


    /**
     * METHOD: Set the direction the fish moves horizontally
     * Overrides Fish.setXDirection(), removing the logic which changes the direction of the sprite
     *
     * @param newDirection      The new direction to face
     */
    protected void setXDirection(int newDirection)
    {
        /*
            set x direction to the given direction without rotating the sprite
        */

        // SET: x direction to newDirection
        _direction._x = newDirection;
    }
}
