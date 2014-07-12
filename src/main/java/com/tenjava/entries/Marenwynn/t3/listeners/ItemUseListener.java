package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.data.Msg;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;

public class ItemUseListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent useEvent) {
        Player p = useEvent.getPlayer();
        Action a = useEvent.getAction();

        if (a != Action.RIGHT_CLICK_AIR && a != Action.RIGHT_CLICK_BLOCK)
            return;

        ItemStack is = useEvent.getItem();

        if (is == null)
            return;

        PlayerData pd = Data.getPlayerData(p.getUniqueId());

        // Restore player's mobility when they use a splint
        if (pd.hasBrokenLegs() && is.isSimilar(Data.customItems.get("splint"))) {
            // May have other slowing afflictions, so only removing speed
            // reduction from broken legs
            pd.setBrokenLegs(false);
            pd.setWalkSpeed(pd.getWalkSpeed() + 0.1F);
            Data.savePlayer(p.getUniqueId());

            useItemInHand(p, is);
            p.setWalkSpeed(pd.getWalkSpeed());
            useEvent.setCancelled(true);
            Msg.MEND_LEGS.sendTo(p);
        }
    }

    public void useItemInHand(Player p, ItemStack is) {
        is.setAmount(is.getAmount() - 1);
        p.setItemInHand(is);
    }

}
