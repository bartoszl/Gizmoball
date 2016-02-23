package model;

public class KeyConnection {

    private int keyID;
    private String connectingTo;

    public KeyConnection(int keyID, String connectingTo) {
        this.keyID = keyID;
        this.connectingTo = connectingTo;
    }

    public int getKeyID() {
        return keyID;
    }

    public String getConnectingTo() {
        return connectingTo;
    }
}