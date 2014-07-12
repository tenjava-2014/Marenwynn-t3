package com.tenjava.entries.Marenwynn.t3.data;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GridLocation implements Serializable {

    private static final long serialVersionUID = 8145130391381068812L;

    private String            worldName;
    private int               x, y, z;

    public GridLocation(Location loc) {
        setLocation(loc);
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    public void setLocation(Location loc) {
        worldName = loc.getWorld().getName();
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
    }

}