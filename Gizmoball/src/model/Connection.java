package model;

public class Connection {

    private CircularBumper trigger;
    private Flipper flipper;

    public Connection(CircularBumper trigger, Flipper flipper) {
        this.trigger = trigger;
        this.flipper = flipper;
    }

    public CircularBumper getTrigger() {
        return trigger;
    }

    public Flipper getFlipper() {
        return flipper;
    }

    public void setFlipper(Flipper flipper) { this.flipper = flipper; }
}
