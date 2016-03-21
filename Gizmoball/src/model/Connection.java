package model;

/**
 * 
 * @author Kiril Rupasov
 *
 */
public class Connection {

    private Bumper trigger;
    private Flipper flipper;
    
    /**
     * Constructor for connection.
     * 
     * @param trigger -> Bumper which will trigger an action in the connected flipper.
     * @param flipper -> Flipper, flipper that will be triggered after the CircularBumper gets hit.
     */
    public Connection(Bumper trigger, Flipper flipper) {
        this.trigger = trigger;
        this.flipper = flipper;
    }
    
    /**
     * Getter method for trigger.
     * 
     * @return  Bumper, representing the trigger.
     */
    public Bumper getTrigger() {
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

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Connection)) {
            return false;
        }
        Connection c = (Connection) object;
        if(( c.getTrigger().getCircles().equals(trigger.getCircles()))
                && (c.getFlipper()).getCircles().equals(flipper.getCircles())) {
            return true;
        } else {
            return false;
        }
    }
}
