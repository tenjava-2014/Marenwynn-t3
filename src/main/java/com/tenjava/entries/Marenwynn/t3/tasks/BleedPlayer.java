package com.tenjava.entries.Marenwynn.t3.tasks;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.Marenwynn.t3.Util;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;

public class BleedPlayer extends BukkitRunnable {

    private Player     p;
    private PlayerData pd;

    public BleedPlayer(Player p, PlayerData pd) {
        this.p = p;
        this.pd = pd;
    }

    @Override
    public void run() {
        if (pd.isBleeding()) {
            double hp = p.getHealth();
            double damage = 1D;

            if (hp - damage <= 0) {
                p.setHealth(0);
                this.cancel();
            } else {
                p.setHealth(hp - damage);
            }

            p.getWorld().playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10F, 0);
            Util.bleed(p);
        } else {
            this.cancel();
        }
    }

}
