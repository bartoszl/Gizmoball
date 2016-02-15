package model;

public class Connection {

    private String connectingFrom, connectingTo;

    public Connection(String connectingFrom, String connectingTo) {
        this.connectingFrom = connectingFrom;
        this.connectingTo = connectingTo;
    }

    public String getConnectingFrom() {
        return connectingFrom;
    }

    public String getConnectingTo() {
        return connectingTo;
    }
}
