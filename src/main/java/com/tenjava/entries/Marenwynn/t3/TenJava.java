package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.Marenwynn.t3.data.Data;

public class TenJava extends JavaPlugin {

    @Override
    public void onEnable() {
        Data.init(this);
    }

}
