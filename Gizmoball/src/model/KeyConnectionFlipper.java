package model;

public class KeyConnectionFlipper {

    private int keyID;
    private IFlipper flipper;
    private String upDown;

    public KeyConnectionFlipper(int keyID, IFlipper flipper, String upDown) {
        this.keyID = keyID;
        this.flipper = flipper;
        this.upDown = upDown;
    }

    public int getKeyID() {
        return keyID;
    }

    public IFlipper getFlipper() {
        return flipper;
    }

    public String getUpDown() {
        return upDown;
    }
}
