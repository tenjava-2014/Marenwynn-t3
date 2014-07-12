package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class FallDamageListener implements Listener {

    public void onEntityDamage(EntityDamageEvent damageEvent) {
        if (!(damageEvent.getEntity() instanceof Player) || damageEvent.getCause() != DamageCause.FALL)
            return;
        
        Player p = (Player) damageEvent.getEntity();
    }

}
