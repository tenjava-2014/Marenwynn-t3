package com.tenjava.entries.Marenwynn.t3.data;

import java.io.Serializable;

public class PlayerData implements Serializable {

    private static final long serialVersionUID = 9104156590029344629L;

    private boolean           brokenLegs;
    private float             originalWalkSpeed;

    public PlayerData() {
        setBrokenLegs(false);
    }

    public boolean hasBrokenLegs() {
        return brokenLegs;
    }

    public void setBrokenLegs(boolean brokenLegs) {
        this.brokenLegs = brokenLegs;
    }

    public float getOriginalWalkSpeed() {
        return originalWalkSpeed;
    }

    public void setOriginalWalkSpeed(float originalWalkSpeed) {
        this.originalWalkSpeed = originalWalkSpeed;
    }

}
