package UserCode.Behaviours;

import java.util.Random;

import UserCode.Interfaces.IStateManager;

/**
 * Provides functions and data members to track the behavioural state of a fish
 *
 * @author Nye Blythe
 * @version 1.0
 */
public class StateManager implements IStateManager
{
    /*
     * The list of different states and their enumerable index are as follows:
     *
     * 0; STOP
     * 1; START
     * 2; ACCELERATE
     * 3; SWIM
     * 4; DECELERATE
     */
    // DECLARE an integer to track the current state, call it '_state' and initialise it to the default value 3 (swim):
    private int _state = 3;
    // DECLARE an integer to define the total number of states, call it '_stateNum' and initialise it to the default value of 5:
    private int _stateNum = 5;
    // DECLARE a boolean to track if the state recently changed, call it '_switched':
    private boolean _switched = false;

    // DECLARE a two dimensional array to define the range of possible timer values, call it '_timerDefaults':
    private int[][] _timerDefaults;

    // DECLARE a double to store the current time between behaviours, call it '_timer':
    private double _timer;
    // DECLARE a double to store the time at the beginning of acceleration/deceleration behaviours, call it '_accelStartTime':
    private double _accelStartTime;

    // DECLARE a reference to the instance of the Random class, call it '_rand':
    private RandomRange _rand;

    /**
     * METHOD: Gets the value of _state
     *
     * @return The value of _state
     */
    public int State() { return _state; }
    /**
     * METHOD: Gets the value of _switched
     *
     * @return The value of _switched
     */
    public boolean Switched() { return _switched; }


    /**
     * Constructor for objects of class StateManager
     *
     * @param rand              Reference to an instance of a RandomRange object
     * @param timerDefaults     A two dimensional array representing the maximum and minimum amount of time each behaviour should last, in frames
     */
    public StateManager(RandomRange rand, int[][] timerDefaults)
    {
        // SET: data members to parameters
        // _rand:
        _rand = rand;
        // _timerDefaults:
        _timerDefaults = timerDefaults;

        // SET: _timer to a random value based on the timer defaults for the current state
        _timer = _rand.rangeInt(_timerDefaults[_state][0], _timerDefaults[_state][1]);
    }
    /**
     * Constructor for objects of class StateManager
     *
     * @param rand              Reference to an instance of a RandomRange object
     */
    public StateManager(RandomRange rand)
    {
        // Pass the given values up to the next most specific overload of this constructor, including default values for timerDefaults:
        this(rand,
             new int[][] {{30,300},
                          {0,0},
                          {30,300},
                          {30,300},
                          {30,300}});
    }

    /**
     * METHOD: Update the state, progressing it if necessary and ensuring all values are correctly set
     */
    public void updateState()
    {
        /*
            decrease timer
            ensure switched is off
            if timer is expired (<= 0):
                progress the state
        */

        // SET: decrement timer
        _timer--;

        // IF: _switched boolean is on
        if(_switched)
        {
            // SET: turn _switched off
            _switched = !_switched;
        }

        // IF: the timer has expired
        if(_timer <= 0)
        {
            // Progress to the next state in the cycle:
            progressState();
        }
    }

    /**
     * METHOD: Increment the current state by 1 and perform any necessary preparation for the next state
     */
    private void progressState()
    {
        /*
            increase the state counter
            set switched to true
            check to ensure the state counter is in a legal state
            start a new timer randomizing between the timing values from the current state
        */

        // SET: increment state
        _state++;
        // SET: _switched on
        _switched = true;

        // Check state is within the possible bounds
        checkState();

        // Reset timer using a random value between the possible values for the current state
        _timer = _rand.rangeInt(_timerDefaults[_state][0], _timerDefaults[_state][1]);
    }

    /**
     * METHOD: Check if state is any of the possible enumerations, looping the value if not
     */
    private void checkState()
    {
        /*
            if state is greater than the amount of states:
                reset state to 0
            if state is less than 0:
                reset state to the maximum state value
        */

        // IF: state is greater than the number of possible states
        if(_state >= _stateNum)
        {
            // SET: state to 0
            _state = 0;
        }
        // IF: state is less than 0
        else if(_state < 0)
        {
            // SET: state to the highest possible state
            _state = _stateNum;
        }
    }

    /**
     * METHOD: Set the beginning of this time period for the purpose of acceleration/deceleration
     */
    public void setAccelTime()
    {
        /*
            store the current timer in a seperate variable to track the start of the behaviour
        */

        // SET: accel start time to the current timer
        _accelStartTime = _timer;
    }

    /**
     * METHOD: Returns the distance between the current time and the end of the current state for the purpose of acceleration/deceleration
     *
     * @return A double value between 0 and 1 representing the current time as a proportion of the total time the state lasts
     */
    public double getAccelTime()
    {
        /*
            reduce the current time to a factor between 0 and 1 where 0 is the starting time and 1 is the end of the behaviour
            // (start time - current time) / start time
        */

        // Return the difference between the acceleration start time and current timer as a proportion of the acceleration start time:
        return (_accelStartTime - _timer) / _accelStartTime;
    }
}
