package UserCode;

import java.util.Arrays;

import Framework.DisplayObject;

import Libraries.MathF;

import DataStructures.Vector2;

import UserCode.Behaviours.RandomRange;

/**
 * The SeaHorse swims diagonally around the screen, randomly decelerating and sinking before starting upwards again
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class SeaHorse extends Fish
{
    // DECLARE a double to store the speed at which the SeaHorse should sink when not moving, call it '_sinkSpeed' and initialise it to 0.005:
    private double _sinkSpeed = 0.005;

    /**
     * Constructor for objects of class SeaHorse
     *
     * @param rand          Reference to an instance of a random object
     * @param pos           The initial position of the fish
     * @param aquariumSize  The size of the aquarium the fish is being placed in
     */
    public SeaHorse(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        // Pass rand, pos and aquariumSize to parent Fish() including default values for modelPath and texturePath (passing path to SeaHorse texture):
        super(rand, pos, aquariumSize, "models/billboard/billboard.obj", "textures/javaFish/Seahorse.png");

        // SET: rotateZ to flip z rotation of the fish
        // This is necessary to correctly orientate the texture 'Seahorse.png'
        rotateZ = 180;


        // SET: _size to default values to match texture
        _size.set(0.8d, 0.95d);

        // SET: speed values to defaults for JavaFish movement
        // _speed:
        _speed.set(0.03d, 0.03d);
        // _startSpeed:
        _startSpeed.set(0.03d, 0.03d);
        // _maxSpeed:
        _maxSpeed.set(0.03d, 0.03d);

        // SET: _direction to default values (1, 1) to begin the fish moving right and up
        _direction.set(1, 1);
    }

    /**
     * METHOD: Stop the fish from moving, this is called only if determined by the state manager<br>
     * Overrides Fish.stop(), stopping only x movement instead of y movement, to facilitate sinking behaviour
     */
    protected void stop()
    {
        /*
            set x direction to 0 on the first update only
        */

        // IF: the state just changed to this:
        if(_stateManager.Switched())
        {
            _direction._x = 0;
        }
    }
    /**
     * METHOD: Start the fish moving, beginning a swim cycle, this is called only if determined by the state manager<br>
     * Overrides Fish.start(), extending it to apply to vertical movement as well as horizontal movement
     */
    protected void start()
    {
        /*
            call base start behaviour to start moving randomly in a random direction on the x axis
            additionally randomise a starting y speed and start the fish moving upwards
        */

        super.start();

        _speed._y = _rand.rangeDouble(0, _startSpeed._y);

        _direction._y = 1;
    }
    /**
     * METHOD: Increase the fish's speed gradually, this is called only if determined by the state manager<br>
     * Overrides Fish.accelerate(), extending it to apply to vertical movement as well as horizontal movement
     */
    protected void accelerate()
    {
        /*
            call base behaviour to accelerate along the x axis
            additionally:
                make a curve between current y speed and the maximum y speed on the first update only
                interpolate along the curve to determine y speed in the same way as base behaviour
        */

        super.accelerate();

            // IF: the state just changed to this:
        if(_stateManager.Switched())
        {
            double range = _maxSpeed._y - _speed._y;
            double center = _speed._y + (range / 2);
            _speedCurve._y = Arrays.asList(_speed._y, center, center, _maxSpeed._y);
        }

        double lerpPos = _stateManager.getAccelTime();

        _speed._y = MathF.rLerp(_speedCurve._y, lerpPos);
    }
    /**
     * METHOD: Decrease the fish's speed gradually and cause the fish to sink over time, this is called only if determined by the state manager<br>
     * Overrides Fish.decelerate() to implement sinking behaviour
     */
    protected void decelerate()
    {
        /*
            for the first update:
                if fish is moving upwards:
                    invert y direction AND speed to maintain its current trajectory and allow for 'deceleration' to a positive downwards speed
                make a curve between the current speed and min speed on both axes to determine how fast to decelerate at any point
                set a timer with state manager to know how far in the behaviour the fish is
            for every update after:
                if fish is moving upwards:
                    invert y direction AND speed to maintain its current trajectory and allow for 'deceleration' to a positive downwards speed
                    recalculate y curve with new speeds
            interpolate along the points on the curves based on how long the behaviour's been running, set speeds to those values
        */

        // IF: the state just changed to this:
        if(_stateManager.Switched())
        {
            // IF: the fish is swimming upwards
            if(_direction._y == 1)
            {
                // SET: invert y speed
                _speed._y *= -1;
                // SET: y direction to -1 (downwards)
                _direction._y = -1;
            }

            // INSTANTIATE a new set of points indicating a curve to smooth from current x speed to 0:
            _speedCurve._x = Arrays.asList(_speed._x, _speed._x / 2, _speed._x / 2, 0d);
            // INSTANTIATE a new set of points indicating a curve to smooth from current y speed to sink speed:
            _speedCurve._y = Arrays.asList(_speed._y, _speed._y, _sinkSpeed);

            // Start a timer for this behaviour:
            _stateManager.setAccelTime();
        }
        // IF: the fish is swimming upwards
        else if(_direction._y == 1)
        {
            // SET: invert y speed
            _speed._y *= -1;
            // SET: y direction to -1 (downwards)
            _direction._y = -1;

            // INSTANTIATE a new set of points indicating a curve to smooth from current y speed to sink speed:
            _speedCurve._y = Arrays.asList(_speed._y, _speed._y, _sinkSpeed);
        }

        // Temporarily store the distance along the curve to interpolate:
        double lerpPos = _stateManager.getAccelTime();

        // SET: x speed to the point along the x speed curve determined by lerpPos:
        _speed._x = MathF.rLerp(_speedCurve._x, lerpPos);
        // SET: y speed to the point along the y speed curve determined by lerpPos:
        _speed._y = MathF.rLerp(_speedCurve._y, lerpPos);
    }
}
