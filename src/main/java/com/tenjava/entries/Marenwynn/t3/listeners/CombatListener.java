package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitTask;

import com.tenjava.entries.Marenwynn.t3.Effects;
import com.tenjava.entries.Marenwynn.t3.Util;
import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.tasks.BleedPlayer;

public class CombatListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent damageEvent) {
        if (damageEvent.getEntityType() != EntityType.PLAYER)
            return;

        Player p = (Player) damageEvent.getEntity();
        double damage = damageEvent.getDamage();

        // Blocking will prevent bleeds :)
        if (p.isBlocking() || p.getHealth() - damage <= 0)
            return;

        int severity = (int) Math.round(damage * 5D);
        int effectChance = 10 + severity;

        // Bleeds don't stack, but they can get worse
        if (Data.bleedTasks.containsKey(p.getUniqueId())) {
            BleedPlayer bleed = Data.bleedTasks.get(p.getUniqueId());

            if (bleed.getSeverity() >= severity)
                return;
        }

        if (Util.getRandom().nextInt(100) <= effectChance)
            Effects.bleedPlayer(p, severity);

        // Debug
        Bukkit.broadcastMessage("damage: " + damage);
        Bukkit.broadcastMessage("severity: " + severity);
        Bukkit.broadcastMessage("effect chance: " + effectChance);
    }

}
