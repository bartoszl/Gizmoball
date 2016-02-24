package model;

import physics.Vect;

public  class CollisionDetails {
    private double tuc;
    private Vect velo;
    private boolean absorbed;

    public CollisionDetails(double t, Vect v, boolean absorbed) {
        tuc = t;
        velo = v;
        this.absorbed = absorbed;
    }

    public double getTuc() {
        return tuc;
    }

    public Vect getVelo() {
        return velo;
    }

    public boolean isAbsorbed() {
        return absorbed;
    }
}