package UserCode;

import java.util.List;
import java.util.Arrays;

import Libraries.MathF;

import DataStructures.Vector2;
import DataStructures.Collision;

import UserCode.Interfaces.IUpdatable;
import UserCode.Interfaces.ICollisionManager;
import UserCode.Interfaces.IStateManager;

import UserCode.Behaviours.CollisionManager;
import UserCode.Behaviours.StateManager;
import UserCode.Behaviours.RandomRange;

import Framework.DisplayObject;

/**
 * Contains all behaviours necessary to display a fish moving around a scene, each specific fish instance builds on and replaces behaviours inherited from this class to build their unique behaviours
 *
 * @author Nye Blythe
 * @version 1.1
 */
public class Fish extends DisplayObject implements IUpdatable
{
    // instance variables:
    // DECLARE a reference to the instance of the CollisionManager class, call it '_collisionManager':
    protected ICollisionManager _collisionManager;
    // DECLARE a reference to the instance of the StateManager class, call it '_stateManager':
    protected IStateManager _stateManager;

    // DECLARE an integer to store the base Y rotation of the fish, call it '_rotateBase' and initialise it to the default value of 180:
    protected int _rotateBase = 180;
    // DECLARE an integer to store the amount of rotation needed to invert the orientation of the fish, call it '_rotateFlip' and initialise it to the default value of 90:
    protected int _rotateFlip = 90;

    // DECLARE a reference to the instance of the RandomRange class, call it '_rand':
    protected RandomRange _rand;

    // DECLARE a Vector to store the virtual size of the fish, call it '_size' and initialise it:
    protected Vector2<Double> _size = new Vector2<Double>();

    // DECLARE a Vector to store the movement speed of the fish, call it '_speed' and initialise it:
    protected Vector2<Double> _speed = new Vector2<Double>();
    // DECLARE a Vector used to determine how fast the fish begins swimming, call it '_startSpeed' and initialise it:
    protected Vector2<Double> _startSpeed = new Vector2<Double>();
    // DECLARE a Vector to store the absolute maximum speed of the fish, call it '_maxSpeed' and initialise it:
    protected Vector2<Double> _maxSpeed = new Vector2<Double>();

    // DECLARE a Vector to store the direction the fish is swimming in, call it '_direction' and initialise it:
    protected Vector2<Integer> _direction = new Vector2<Integer>();

    // DECLARE a Vector of lists to store the sets of points which constitute the acceleration and deceleration curves, call it '_speedCurve' and initialise it:
    protected Vector2<List<Double>> _speedCurve = new Vector2<List<Double>>();

    /**
     * Constructor for objects of class JavaFish
     *
     * @param rand          Reference to an instance of a random object
     * @param pos           The initial position of the fish
     * @param aquariumSize  The size of the aquarium the fish is being placed in
     * @param modelPath     Relative path to the model used to display the fish
     * @param texturePath   Relative path to the texture used to display the fish
     */
    public Fish(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize, String modelPath, String texturePath)
    {
        // Pass modelPath and texturePath to parent DisplayObject(), including default value 0.4 for scale:
        super(modelPath, texturePath, 0.4);

        // INITIALISE instance variables:
        // _collisionManager:
        _collisionManager = new CollisionManager(aquariumSize);
        // _stateManager:
        _stateManager = new StateManager(rand);

        // SET: data members to parameters
        // _rand:
        _rand = rand;

        // x:
        x = pos._x;
        // y:
        y = pos._y;
        // SET: default value of 1
        z = 1.0;

        // SET: rotation to default values of (0, 270, 0);
        rotateX = 0;
        rotateY = 270;
        rotateZ = 0;
    }
    /**
     * Constructor for objects of class JavaFish
     *
     * @param rand          Reference to an instance of a RandomRange object
     * @param pos           The initial position of the fish
     * @param aquariumSize  The size of the aquarium the fish is being placed in
     */
    public Fish(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        // Pass the given values up to the next most specific overload of this constructor, including default values for modelPath and texturePath:
        this(rand, pos, aquariumSize, "models/billboard/billboard.obj", "textures/javaFish/JavaFish.png");
    }

    /**
     * METHOD: Update the token using behaviours based on the current state
     */
    public void update()
    {
        /*
            get state from state manager
            check state and begin the appropriate behaviour, for states 0, 1, 2 and 4
            update position based on speed and direction
            check if the fish has moved outside the aquarium, switching directions and moving back in if so
            update the state manager
        */

        // Get current state from state manager instance:
        int state = _stateManager.State();

        // CHECK STATE:
        // IF: state is 0 (stopped):
        if(state == 0)
        {
            // Prevent any movement until the start of the next swim cycle:
            stop();
        }
        // IF: state is 1 (starting):
        if(state == 1)
        {
            // Begin the next swim cycle:
            start();
        }
        // IF: state is 2 (accelerating):
        if(state == 2)
        {
            // Update the current speed values, accelerating the fish:
            accelerate();
        }
        // IF: state is 4 (decelerating):
        if(state == 4)
        {
            // Update the current speed values, decelerating the fish:
            decelerate();
        }

        // Update the fish's position:
        swim();

        // Ensure the fish is swimming in the correct direction and stays within the bounds of the aquarium:
        checkEdges();

        // Progress through the swim cycle, if necessary:
        _stateManager.updateState();
    }

    /**
     * METHOD: Stop the fish from moving, this is called only if determined by the state manager
     */
    protected void stop()
    {
        /*
            set x and y direction to 0, run only once
        */

        // IF: the state just changed to this:
        if(_stateManager.Switched())
        {
            //SET: _direction to 0
            _direction._x = 0;
            _direction._y = 0;
        }
    }
    /**
     * METHOD: Start the fish moving, beginning a swim cycle, this is called only if determined by the state manager
     */
    protected void start()
    {
        /*
            randomly decide a direction to start moving in
            randomise a speed to start moving at
        */

        // IF: random number is within a certain range (50% chance):
        if(_rand.nextDouble() >= 0.5)
        {
            // SET: x direction to face the fish right:
            setXDirection(1);
        }
        else
        {
            // SET: y direction to face the fish left:
            setXDirection(-1);
        }

        // SET: x speed to a random value between 0 and _startSpeed
        _speed._x = _rand.rangeDouble(0, _startSpeed._x);
    }
    /**
     * METHOD: Increase the fish's speed gradually, this is called only if determined by the state manager
     */
    protected void accelerate()
    {
        /*
            for the first update:
                make a curve between the current speed and max speed to determine how fast to accelerate at any point
                set a timer with state manager to know how far in the behaviour the fish is
            interpolate along the points on the curve based on how long the behaviour's been running, set x speed to that value
        */

        // IF: the state just changed to this:
        if(_stateManager.Switched())
        {
            // Calculate the value directly between current x speed and maximum x speed:
            double center = (_maxSpeed._x + _speed._x) / 2;
            // INSTANTIATE a new set of points indicating a curve to smooth from current x speed to maximum x speed:
            _speedCurve._x = Arrays.asList(_speed._x, center, center, _maxSpeed._x);

            // Start a timer for this behaviour:
            _stateManager.setAccelTime();
        }

        // Temporarily store the distance along the curve to interpolate:
        double lerpPos = _stateManager.getAccelTime();

        // SET: x speed to the point along the x speed curve determined by lerpPos:
        _speed._x = MathF.rLerp(_speedCurve._x, lerpPos);
    }
    /**
     * METHOD: Move the fish according to _speed and _direction
     */
    protected void swim()
    {
        /*
            move the fish according to speed and direction in each axis
        */

        // Move position on each axis by the speed value for that axis along the direction for that axis:
        x += _speed._x * _direction._x;
        y += _speed._y * _direction._y;
    }
    /**
     * METHOD: Decrease the fish's speed gradually, this is called only if determined by the state manager
     */
    protected void decelerate()
    {
        /*
            for the first update:
                make a curve between the current speed and min speed to determine how fast to decelerate at any point
                set a timer with state manager to know how far in the behaviour the fish is
            interpolate along the points on the curve based on how long the behaviour's been running, set x speed to that value
        */

        // IF: the state just changed to this:
        if(_stateManager.Switched())
        {
            // INSTANTIATE a new set of points indicating a curve to smooth from current x speed to 0:
            _speedCurve._x = Arrays.asList(_speed._x, _speed._x / 2, _speed._x / 2, 0d);

            // Start a timer for this behaviour:
            _stateManager.setAccelTime();
        }

        // Temporarily store the distance along the curve to interpolate:
        double lerpPos = _stateManager.getAccelTime();

        // SET: x speed to the point along the x speed curve determined by lerpPos:
        _speed._x = MathF.rLerp(_speedCurve._x, lerpPos);
    }

    /**
     * METHOD: Ensure the fish stays inside the bounds of the aquarium and check if it should change direction
     */
    protected void checkEdges()
    {
        /*
            check and store collision data from collision manager
            if a horizontal edge was hit:
                invert x direction
                clamp x position to clamp data from collision manager
            if a vertical edge was hit:
                invert y direction
                clamp y position to clamp data from collision manager
        */

        // INSTANTIATE a new collision and store collision data from collision manager's checkEdges() in it
        Collision collision = _collisionManager.checkEdges(new Vector2<Double>(x, y), _size);

        // IF: an edge has been hit on the x axis:
        if(collision._edges._x != 0)
        {
            // SET: x direction to face the fish away from the edge
            setXDirection(-collision._edges._x);

            // SET: x position inside the aquarium bounds using the collision's x clamp value
            x = collision._clamp._x;
        }
        if(collision._edges._y != 0)
        {
            // SET: y direction to face the fish away from the edge
            setYDirection(-collision._edges._y);

            // SET: y position inside the aquarium bounds using the collision's y clamp value
            y = collision._clamp._y;
        }
    }

    /**
     * METHOD: Set the direction the fish moves horizontally and rotate sprite to face the appropriate direction
     *
     * @param newDirection      The new direction to face
     */
    protected void setXDirection(int newDirection)
    {
        /*
            set x direction to the given direction
            set y rotation to a base rotation plus or minus an extra value (90) based on the new direction, to orientate the sprite correctly
        */

        // SET: y rotation to the base rotation +/- rotation flip value, based on newDirection
        rotateY = _rotateBase + (_rotateFlip * newDirection);

        // SET: x direction to newDirection
        _direction._x = newDirection;
    }
    /**
     * METHOD: Set the direction the fish moves vertically
     *
     * @param newDirection      The new direction to face
     */
    protected void setYDirection(int newDirection)
    {
        /*
            set y direction to the given direction
        */

        // SET: y direction to newDirection
        _direction._y = newDirection;
    }
}
