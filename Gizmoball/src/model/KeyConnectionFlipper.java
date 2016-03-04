package model;

public class KeyConnectionFlipper {

    private int keyID;
    private Flipper flipper;

    public KeyConnectionFlipper(int keyID, Flipper flipper) {
        this.keyID = keyID;
        this.flipper = flipper;
    }

    public int getKeyID() {
        return keyID;
    }

    public Flipper getFlipper() {
        return flipper;
    }
}
