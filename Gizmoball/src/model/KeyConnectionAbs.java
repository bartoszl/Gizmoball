package model;

/**
 * 
 * @author Kiril Rupasov
 *
 */
public class KeyConnectionAbs {

    private int keyID;
    private IAbsorber abs;
    private String upDown;
    
    /**
     * 
     * @param keyID -> int, representing KeyID of the assigned key.
     * @param abs -> abs, representing Absorber, which action will be triggered.
     * @param upDown -> ???
     */
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
