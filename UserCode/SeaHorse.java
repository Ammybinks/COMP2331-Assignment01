package UserCode;

import java.util.Arrays;

import Framework.DisplayObject;

import Libraries.MathF;

import DataStructures.Vector2;

import UserCode.Behaviours.RandomRange;

/**
 * Write a description of class SeaHorse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SeaHorse extends Fish
{
    private double _sinkSpeed = 0.005;
    
    private Vector2<Double> _decelMod = new Vector2<Double>(1d, 1d);
    
    /**
     * Constructor for objects of class SeaHorse
     */
    public SeaHorse(RandomRange rand, Vector2<Double> pos, Vector2<Double> aquariumSize)
    {
        super("models/billboard/billboard.obj", "textures/javaFish/Seahorse.png", rand, pos, aquariumSize);
        
        rotateX = 0;
        rotateY = 270;
        rotateZ = 180;
        
        _size.set(0.9d, 0.8d);
        
        _speed.set(0.05d, 0.05d);
        _startSpeed.set(0.03d, 0.03d);
        _maxSpeed.set(0.05d, 0.05d);
        
        _direction.set(1, 1);
    }
    
    protected void stop()
    {
        if(_stateManager.Switched())
        {
            _direction._x = 0;
        }
    }
    
    protected void start()
    {
        super.start();
        
        _speed._y = _rand.rangeDouble(0, _startSpeed._y);
        
        _direction._y = 1;
    }
    
    protected void accelerate()
    {
        super.accelerate();
        
        if(_stateManager.Switched())
        {
            double range = _maxSpeed._y - _speed._y;
            double center = _speed._y + (range / 2);
            _speedCurve._y = Arrays.asList(_speed._y, center, center, _maxSpeed._y);
        }
        
        double lerpPos = _stateManager.getAccelTime();
        
        _speed._y = MathF.rLerp(_speedCurve._y, lerpPos);
    }
    
    protected void decelerate()
    {
        if(_stateManager.Switched())
        {
            if(_direction._y == 1)
            {
                _speed._y *= -1;
                _direction._y = -1;
            }
            
            _speedCurve._x = Arrays.asList(_speed._x, _speed._x / 2, _speed._x / 2, 0d);
            _speedCurve._y = Arrays.asList(_speed._y, _speed._y, _sinkSpeed);
            
            _stateManager.setAccelTime();
        }
        else if(_direction._y == 1)
        {
            _speed._y *= -1;
            _direction._y = -1;
            
            _speedCurve._y = Arrays.asList(_speed._y, _speed._y, _sinkSpeed);
        }
        
        double lerpPos = _stateManager.getAccelTime();
        
        double xLerpPos = MathF.clampMax(1, lerpPos / _decelMod._x);
        double yLerpPos = MathF.clampMax(1, lerpPos / _decelMod._y);
        
        _speed._x = MathF.rLerp(_speedCurve._x, xLerpPos);
        _speed._y = MathF.rLerp(_speedCurve._y, yLerpPos);
    }
}
