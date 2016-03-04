package model;

public class KeyConnectionAbs {

    private int keyID;
    private Absorber abs;

    public KeyConnectionAbs(int keyID, Absorber abs) {
        this.keyID = keyID;
        this.abs = abs;
    }

    public int getKeyID() {
        return keyID;
    }

    public Absorber getAbs() {
        return abs;
    }
}
