package UserCode.Interfaces;


/**
 * Interface attatched to the StateManager class providing references needed for use by updatable Fish tokens
 *
 * @author Nye Blythe
 * @version 1.0
 */
public interface IStateManager
{
    int State();
    boolean Switched();


    void updateState();

    void setAccelTime();
    double getAccelTime();
}
