package model;

public class KeyConnectionFlipper {

    private int keyID;
    private Flipper flipper;
    private String upDown;

    public KeyConnectionFlipper(int keyID, Flipper flipper, String upDown) {
        this.keyID = keyID;
        this.flipper = flipper;
        this.upDown = upDown;
    }

    public int getKeyID() {
        return keyID;
    }

    public Flipper getFlipper() {
        return flipper;
    }

    public String getUpDown() {
        return upDown;
    }
}
