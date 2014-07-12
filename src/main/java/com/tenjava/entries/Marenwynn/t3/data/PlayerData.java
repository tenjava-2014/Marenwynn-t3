package com.tenjava.entries.Marenwynn.t3.data;

import java.io.Serializable;

public class PlayerData implements Serializable {

    private static final long serialVersionUID = 9104156590029344629L;

    private boolean           brokenLegs;
    private float             walkSpeed;

    public PlayerData() {
        setBrokenLegs(false);
        setWalkSpeed(0.2F);
    }

    public boolean hasBrokenLegs() {
        return brokenLegs;
    }

    public void setBrokenLegs(boolean brokenLegs) {
        this.brokenLegs = brokenLegs;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

}
