package UserCode;

import java.util.ArrayList;

import env3d.Env;

import Framework.Core;

import DataStructures.Vector2;

import UserCode.Interfaces.IUpdatable;

import UserCode.Behaviours.RandomRange;

/**
 * Simulation is the top-level class for the Aquarium simulation.
 * 
 * @author Nye Blythe & Marc Price
 * @version 1.1
 */
public class Simulation
{
    // instance variables:
    // DECLARE a reference to the instance of the Core class, call it '_core':
    private Core _core;
    
    // DECLARE a reference to the instance of the 'Env' (environment) class, call it '_world':
    private Env _world;
        
    // DECLARE a boolean that signals when the simulation loop should be exited:
    private boolean endSim = false;
    
    // DECLARE a vector that stores the current size of the visible screen area, call it '_aquariumSize' and initialise it to its default value (10, 7.8):
    private Vector2<Double> _aquariumSize = new Vector2<Double>(10d, 7.8d);
    
    // DECLARE a reference to the instance of Random class, call it '_rand':
    private RandomRange _rand;
    
    // DECLARE an array list to store a reference of each fish in the simulation, call it '_fish':
    private ArrayList<Fish> _fish;

    public static void main(String[] args)
    {
        System.out.println("Starting");
        
        Simulation sim = new Simulation();
        
        sim.populate();
        
        sim.run();
    }

    /**
     * Constructor for objects of class Simulation
     */
    public Simulation()
    {
        // INITIALISE instance variables:
        // _core:
        _core = new Core();
        
        _rand = new RandomRange();
        
        _fish = new ArrayList<Fish>();
    }

    public void populate()
    {
        addJavaFish();
        addSeaHorse();
        addUrchin();
    }
    
    public void addJavaFish()
    {
        addFish(new JavaFish(_rand, new Vector2<Double>(_rand.rangeDouble(0, _aquariumSize._x), _rand.rangeDouble(0, _aquariumSize._y)), _aquariumSize));
    }
    public void addSeaHorse()
    {
        addFish(new SeaHorse(_rand, new Vector2<Double>(_rand.rangeDouble(0, _aquariumSize._x), _rand.rangeDouble(0, _aquariumSize._y)), _aquariumSize));
    }
    public void addUrchin()
    {
        addFish(new Urchin(_rand, new Vector2<Double>(_rand.rangeDouble(0, _aquariumSize._x), 0d), _aquariumSize));
    }
    
    public void addFish(Fish fish)
    {
        _core.addDisplayObject(fish);
        
        _fish.add(fish);
    }

    /**
     * METHOD: Run the simulation loop.  User presses escape to exit.
     *
     */
    public void run()
    {
        // CREATE the environment:
        _world = _core.createWorld();
        
        
        // Start simulation loop:
        while (!endSim)
        {
            // UPDATE STAGE:
            // IF: user has requested simulation loop exit (ie escape pressed):
            if (_world.getKey() == 1)
            {
                // SET: render loop exit condition
                endSim = true;
            }
                        
            // UPDATE: the environment
            _core.updateWorld();
        }
        
        // EXIT: cleanly by closing-down the environment:
        _core.destroyWorld();
    }

}
