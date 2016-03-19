package model;

/**
 * 
 * @author Kiril Rupasov
 *
 */
public class Connection {

    private CircularBumper trigger;
    private Flipper flipper;
    
    /**
     * Constructor for connection.
     * 
     * @param trigger -> CircularBumper which will trigger an action in the connected flipper.
     * @param flipper -> Flipper, flipper that will be triggered after the CircularBumper gets hit.
     */
    public Connection(CircularBumper trigger, Flipper flipper) {
        this.trigger = trigger;
        this.flipper = flipper;
    }
    
    /**
     * Getter method for trigger.
     * 
     * @return Circular Bumper, representing the trigger.
     */
    public CircularBumper getTrigger() {
        return trigger;
    }
    
    /**
     * Getter method for flipper.
     * 
     * @return Flipper, representing flipper which action will be triggered.
     */
    public Flipper getFlipper() {
        return flipper;
    }
    
    /**
     * Setter method for flipper.
     * 
     * @param flipper -> Flipper, representing a flipper which action will be triggered.
     */
    public void setFlipper(Flipper flipper) {
    	this.flipper = flipper; 
    }
}
