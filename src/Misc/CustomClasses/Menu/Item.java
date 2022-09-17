package Misc.CustomClasses.Menu;

/**
 * Interface that specifies what can be added to a menu object,
 * and the methods that must be specified.
 *
 * @author Jacob Swineford
 */
public interface Item {

    /**
     * Action for an item that is being navigated on to.
     */
    void navigatedOn();

    /**
     * Action for an item being navigated off of.
     */
    void navigatedOff();

    /**
     * Action for an item upon being navigated to and selected.
     */
    void execute();
}
