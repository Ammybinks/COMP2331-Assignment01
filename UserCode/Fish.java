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
 * Write a description of class Fish here.
 *
 * @author Nye Blythe
 * @version 0.1
 */
public class Fish extends DisplayObject implements IUpdatable
{   
    protected ICollisionManager _collisionManager;
    protected IStateManager _stateManager;
    
    protected Vector2 _spawnArea = new Vector2<double[]>(new double[] {0, 10}, new double[] {0, 8.7});
    
    protected int _rotateBase = 180;
    protected int _rotateFlip = 90;
    
    protected RandomRange _rand;
    
    protected Vector2<Double> _size = new Vector2<Double>();
    
    protected Vector2<Double> _speed = new Vector2<Double>();
    protected Vector2<Double> _startSpeed = new Vector2<Double>();
    protected Vector2<Double> _maxSpeed = new Vector2<Double>();

    protected Vector2<Integer> _direction = new Vector2<Integer>();
    
    protected Vector2<List<Double>> _speedCurve = new Vector2<List<Double>>();
    
    /**
     * Constructor for objects of class JavaFish
     */
    public Fish(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        this("models/billboard/billboard.obj", "textures/javaFish/JavaFish.png", rand, pos, aquariumSize);
    }
    public Fish(String modelPath, String texturePath, RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        super(modelPath, texturePath, 0.4);
        
        _collisionManager = new CollisionManager(aquariumSize);
        _stateManager = new StateManager(rand);
        
        _rand = rand;
        
        x = pos._x;
        y = pos._y;
        z = 1.0;
    }
    
    public void update()
    {
        int state = _stateManager.State();
        
        if(state == 0)
        {
            stop();
        }
        if(state == 1)
        {
            start();
        }
        if(state == 2)
        {
            accelerate();
        }
        if(state == 4)
        {
            decelerate();
        }
        
        swim();
            
        checkEdges();
        
        _stateManager.updateState();
    }
    
    protected void stop()
    {
        if(_stateManager.Switched())
        {
            _direction._x = 0;
            _direction._y = 0;
        }
    }
    protected void start()
    {
        if(_rand.nextDouble() >= 0.5)
        {
            setXDirection(1);
        }
        else
        {
            setXDirection(-1);
        }
        
        _speed._x = _rand.rangeDouble(0, _startSpeed._x);
        
        //progressState();
    }
    protected void accelerate()
    {
        if(_stateManager.Switched())
        {
            double center = (_maxSpeed._x + _speed._x) / 2;
            _speedCurve._x = Arrays.asList(_speed._x, center, center, _maxSpeed._x);
            
            _stateManager.setAccelTime();
        }
        
        double lerpPos = _stateManager.getAccelTime();
        
        _speed._x = MathF.rLerp(_speedCurve._x, lerpPos);
    }
    protected void swim()
    {
        x += _speed._x * _direction._x;
        y += _speed._y * _direction._y;
    }
    protected void decelerate()
    {
        if(_stateManager.Switched())
        {
            _speedCurve._x = Arrays.asList(_speed._x, _speed._x / 2, _speed._x / 2, 0d);
            
            _stateManager.setAccelTime();
        }
        
        double lerpPos = _stateManager.getAccelTime();
        
        _speed._x = MathF.rLerp(_speedCurve._x, lerpPos);
    }
    
    protected void checkEdges()
    {
        Collision collision = _collisionManager.checkEdges(new Vector2<Double>(x, y), _size);
        
        if(collision._edges._x != 0)
        {
            setXDirection(-collision._edges._x);
            
            x = collision._clamp._x;
        }
        if(collision._edges._y != 0)
        {
            setYDirection(-collision._edges._y);
            
            y = collision._clamp._y;
        }
    }
    
    protected void setXDirection(int newDirection)
    {
        rotateY = _rotateBase + (_rotateFlip * newDirection);
        
        _direction._x = newDirection;
    }
    protected void setYDirection(int newDirection)
    {
        _direction._y = newDirection;
    }
}
