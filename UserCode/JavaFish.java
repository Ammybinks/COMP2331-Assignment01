package UserCode;

import Framework.DisplayObject;

import DataStructures.Vector2;

import UserCode.Behaviours.RandomRange;

/**
 * The JavaFish swims back and forth across the screen, randomly decelerating to a stop before starting again
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class JavaFish extends Fish
{
    /**
     * Constructor for objects of class JavaFish
     *
     * @param rand          Reference to an instance of a random object
     * @param pos           The initial position of the fish
     * @param aquariumSize  The size of the aquarium the fish is being placed in
     */
    public JavaFish(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        // Pass rand, pos and aquariumSize to parent Fish() including default values for modelPath and texturePath (passing path to JavaFish texture):
        super(rand, pos, aquariumSize, "models/billboard/billboard.obj", "textures/javaFish/JavaFish.png");

        // SET: _size to default values to match texture
        _size.set(0.9d, 0.8d);

        // SET: speed values to defaults for JavaFish movement
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
}
