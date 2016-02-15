package model;


import java.util.List;

public interface iGBallModel {

    public boolean addGizmo(iGizmo gizmo);

    public List<iGizmo> getGizmos();

    public boolean addAbsorber(iAbsorber absorber);

    public boolean addBall(iBall ball);
}
