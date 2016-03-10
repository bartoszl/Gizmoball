package model;

public class KeyConnectionAbs {

    private int keyID;
    private Absorber abs;
    private String upDown;

    public KeyConnectionAbs(int keyID, Absorber abs, String upDown) {
        this.keyID = keyID;
        this.abs = abs;
        this.upDown = upDown;
    }

    public int getKeyID() {
        return keyID;
    }

    public Absorber getAbs() {
        return abs;
    }
}
