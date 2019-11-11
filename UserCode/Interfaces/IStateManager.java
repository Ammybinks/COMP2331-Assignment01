package UserCode.Interfaces;


/**
 * Write a description of interface IStateManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface IStateManager
{
    int State();
    boolean Switched();
    
    
    void updateState();
    
    void setAccelTime();
    double getAccelTime();
}
