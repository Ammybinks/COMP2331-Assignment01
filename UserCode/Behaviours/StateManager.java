package UserCode.Behaviours;

import java.util.Random;

import UserCode.Interfaces.IStateManager;

/**
 * Write a description of class StateManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StateManager implements IStateManager
{
    /*
     * SWIMMING STATES:
     * 
     * 0; STOP
     * 1; START
     * 2; ACCELERATE
     * 3; SWIM
     * 4; DECELERATE
     */
    private int _state = 3;
    private int _stateNum = 5;
    private int _switchChance = 100;
    private boolean _switched = false;
    
    private int[][] _timerDefaults = new int[][] {{30,300},
                                                  {0,0},
                                                  {30,300},
                                                  {30,300},
                                                  {30,300}};
                                                  
    private double _timer;
    private double _accelStartTime;
    
    private Random _rand;

    public int State() { return _state; }
    public boolean Switched() { return _switched; }

    
    /**
     * Constructor for objects of class StateManager
     */
    public StateManager(Random rand)
    {
        _rand = rand;
        
        _timer = newTimer(_timerDefaults[_state][0], _timerDefaults[_state][1]);
    }
    
    public void updateState()
    {
        _timer--;
        
        if(_switched)
        {
            _switched = !_switched;
        }
        
        if(_timer <= 0)
        {
            progressState();
        }
    }
    
    private void progressState()
    {
        _state++;
        _switched = true;
        
        checkState();
        
        _timer = newTimer(_timerDefaults[_state][0], _timerDefaults[_state][1]);
    }
    
    private void checkState()
    {
        if(_state >= _stateNum)
        {
            _state = 0;
        }
        else if(_state < 0)
        {
            _state = _stateNum;
        }
    }
    
    private double newTimer(int minRange, int maxRange)
    {
        return (_rand.nextDouble() * (maxRange - minRange)) + minRange + 1;
    }
    
    public void setAccelTime()
    {
        _accelStartTime = _timer;
    }
    
    public double getAccelTime()
    {
        return (_accelStartTime - _timer) / _accelStartTime;
    }
}
