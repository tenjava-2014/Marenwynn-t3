package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.tenjava.entries.Marenwynn.t3.Effects;
import com.tenjava.entries.Marenwynn.t3.Util;

public class FallDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent damageEvent) {
        // Only want to affect players that are damaged by falling/
        if (!(damageEvent.getEntity() instanceof Player) || damageEvent.getCause() != DamageCause.FALL)
            return;

        Player p = (Player) damageEvent.getEntity();
        double fallDamage = damageEvent.getDamage();

        // No point in breaking the player's leg if they're going to die anyway
        if (p.getHealth() - fallDamage <= 0)
            return;

        // Chance increases with fall damage
        int severity = (int) Math.round(fallDamage * 5D);
        int effectChance = 5 + severity;

        if (Util.getRandom().nextInt(100) <= effectChance)
            Effects.breakLegs(p, severity);
    }

}
