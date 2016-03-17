package model;

public class KeyConnectionAbs {

    private int keyID;
    private IAbsorber abs;
    private String upDown;

    public KeyConnectionAbs(int keyID, IAbsorber abs, String upDown) {
        this.keyID = keyID;
        this.abs = abs;
        this.upDown = upDown;
    }

    public int getKeyID() {
        return keyID;
    }

    public IAbsorber getAbs() {
        return abs;
    }

    public String getUpDown() {
        return upDown;
    }
}
