package model;

import java.security.Key;

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

    public void setFlipper(Flipper flipper) { this.flipper = flipper; }

    public String getUpDown() {
        return upDown;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof KeyConnectionFlipper)) {

            return false;
        }
        KeyConnectionFlipper kcf = (KeyConnectionFlipper) object;
        if(( kcf.getKeyID() == keyID)
                && (kcf.getFlipper().getCircles().equals(flipper.getCircles()))) {
            return true;
        } else {
            return false;
        }
    }
}
