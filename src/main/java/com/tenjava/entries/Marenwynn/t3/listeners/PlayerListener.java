package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.data.Msg;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {
        Player p = joinEvent.getPlayer();
        PlayerData pd = Data.loadPlayer(p.getUniqueId());

        p.setWalkSpeed(pd.getWalkSpeed());

        if (pd.hasBrokenLegs())
            Msg.NOTICE_BROKEN_LEGS.sendTo(p);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent quitEvent) {
        Data.savePlayer(quitEvent.getPlayer().getUniqueId());
        Data.unloadPlayer(quitEvent.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent) {
        Player p = deathEvent.getEntity();
        PlayerData pd = Data.getPlayerData(p.getUniqueId());

        if (pd.hasBrokenLegs()) {
            p.setWalkSpeed(0.2F);
            pd.setBrokenLegs(false);
            Data.savePlayer(p.getUniqueId());
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent worldEvent) {
        Player p = worldEvent.getPlayer();
        PlayerData pd = Data.loadPlayer(p.getUniqueId());

        p.setWalkSpeed(pd.getWalkSpeed());

        if (pd.hasBrokenLegs())
            Msg.NOTICE_BROKEN_LEGS.sendTo(p);
    }

}
