package com.tenjava.entries.Marenwynn.t3.data;

import java.io.Serializable;

import org.bukkit.Location;

public class PlayerData implements Serializable {

    private static final long serialVersionUID = 9104156590029344629L;

    private boolean           bleeding, brokenLegs;
    private float             walkSpeed;
    private int               bleedSeverity;
    private GridLocation      lastSwingLoc;

    public PlayerData() {
        setBleeding(false);
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

    public boolean isBleeding() {
        return bleeding;
    }

    public void setBleeding(boolean bleeding) {
        this.bleeding = bleeding;
    }

    public int getBleedSeverity() {
        return bleedSeverity;
    }

    public void setBleedSeverity(int bleedSeverity) {
        this.bleedSeverity = bleedSeverity;
    }

    public Location getLastSwingLoc() {
        if (lastSwingLoc != null)
            return lastSwingLoc.getLocation();

        return null;
    }

    public void setLastSwingLoc(Location loc) {
        lastSwingLoc = new GridLocation(loc);
    }

}
