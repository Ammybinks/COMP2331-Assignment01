package UserCode;

import java.util.ArrayList; // new code

import env3d.Env;

import Framework.Core;

// new code begins
import DataStructures.Vector2;

import UserCode.Interfaces.IUpdatable;

import UserCode.Behaviours.RandomRange;
// new code ends

/**
 * Simulation is the top-level class for the Aquarium simulation.
 *
 * @author Nye Blythe & Marc Price
 * @version 1.2
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

    // new code begins
    // DECLARE a vector that stores the current size of the visible screen area, call it '_aquariumSize' and initialise it to the default value of (10, 7.8):
    private Vector2<Double> _aquariumSize = new Vector2<Double>(10d, 7.8d);

    // DECLARE a reference to the instance of Random class, call it '_rand':
    private RandomRange _rand;

    // DECLARE an array list to store a reference of each fish in the simulation, call it '_fish':
    private ArrayList<Fish> _fish;

    /**
     * METHOD: Main method used to run the program
     *
     * @param args      Text arguments passed when the program is run
     */
    public static void main(String[] args)
    {
        /*
            create a new simulation
            add fish to the simulation
            run the simulation
        */

        // INSTANTIATE a new Simulation to run:
        Simulation sim = new Simulation();

        // Place a default set of tokens in the scene:
        sim.populate();

        // Begin the simulation:
        sim.run();
    }
    // new code ends

    /**
     * Constructor for objects of class Simulation
     */
    public Simulation()
    {
        // INITIALISE instance variables:
        // _core:
        _core = new Core();

        // new code begins

        // _rand:
        _rand = new RandomRange();

        // _fish:
        _fish = new ArrayList<Fish>();
    }

    /**
     * METHOD: Add one each of; JavaFish, SeaHorse and Urchin to the aquarium
     */
    public void populate()
    {
        /*
            add a JavaFish to the aquarium
            add a SeaHorse to the aquarium
            add an Urchin to the aquarium
        */

        // Add each type of fish to the scene:
        // JavaFish:
        addJavaFish();
        // SeaHorse:
        addSeaHorse();
        // Urchin:
        addUrchin();
    }

    /**
     * METHOD: Instantiate a new JavaFish and place it at a random point in the aquarium
     */
    public void addJavaFish()
    {
        /*
            add a new JavaFish to the aquarium, randomizing the position and passing a random object
        */

        // INSTANTIATE new JavaFish, passing the global Random instance, a random point inside the bounds of the aquarium and the size of the aquarium to the constructor:
        // CALL addFish(), passing the newly instantiated JavaFish to add it to the scene:
        addFish(new JavaFish(_rand, new Vector2<Double>(_rand.rangeDouble(0, _aquariumSize._x), _rand.rangeDouble(0, _aquariumSize._y)), _aquariumSize));
    }
    /**
     * METHOD: Instantiate a new SeaHorse and place it at a random point in the aquarium
     */
    public void addSeaHorse()
    {
        /*
            add a new SeaHorse to the aquarium, randomizing the position and passing a random object
        */

        // INSTANTIATE new SeaHorse, passing the global Random instance, a random point inside the bounds of the aquarium and the size of the aquarium to the constructor:
        // CALL addFish(), passing the newly instantiated SeaHorse to add it to the scene:
        addFish(new SeaHorse(_rand, new Vector2<Double>(_rand.rangeDouble(0, _aquariumSize._x), _rand.rangeDouble(0, _aquariumSize._y)), _aquariumSize));
    }
    /**
     * METHOD: Instantiate a new Urchin and place it at a random point along the bottom of the aquarium
     */
    public void addUrchin()
    {
        /*
            add a new Urchin to the aquarium, randomizing the position on the bottom of the screen and passing a random object
        */

        // INSTANTIATE new Urchin, passing the global Random instance, a random point along the bottom (y position 0) of the aquarium and the size of the aquarium to the constructor:
        // CALL addFish(), passing the newly instantiated Urchin to add it to the scene:
        addFish(new Urchin(_rand, new Vector2<Double>(_rand.rangeDouble(0, _aquariumSize._x), 0d), _aquariumSize));
    }

    /**
     * METHOD: Add a new fish token to the aquarium while storing a reference in _fish
     *
     * @param fish      The fish to add to the aquarium
     */
    public void addFish(Fish fish)
    {
        /*
            add the given fish to the scene and local fish list
        */

        // Add the fish token to the scene:
        _core.addDisplayObject(fish);

        // Add the fish token to _fish:
        _fish.add(fish);
    }

    // new code ends

    /**
     * METHOD: Run the simulation loop.  User presses escape to exit.
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
