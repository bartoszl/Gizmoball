package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GBallModel extends Observable implements iGBallModel {

    private List<iGizmo> gizmos;

    public GBallModel() {
        gizmos = new ArrayList<>();
    }

    public boolean addGizmo(iGizmo gizmo) {
        return false;
    }

    public boolean addAbsorber(iAbsorber absorber) {return false;}

    public boolean addBall(iBall ball) {
        return false;
    }

    public List<iGizmo> getGizmos() {
        return gizmos;
    }


    private boolean safeToAddGizmo(iGizmo gizmo) {
        return false;
    }
}
