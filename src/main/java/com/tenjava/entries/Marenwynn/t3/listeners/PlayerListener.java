package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        Player p = joinEvent.getPlayer();
        PlayerData pd = Data.loadPlayer(p.getUniqueId());

        if (pd.hasBrokenLegs())
            p.setWalkSpeed(pd.getOriginalWalkSpeed() / 2F);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent quitEvent) {
        Data.savePlayer(quitEvent.getPlayer().getUniqueId());
        Data.unloadPlayer(quitEvent.getPlayer().getUniqueId());
    }

}
