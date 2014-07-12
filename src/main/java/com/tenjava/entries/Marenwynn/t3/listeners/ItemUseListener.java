package com.tenjava.entries.Marenwynn.t3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.data.Msg;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;

public class ItemUseListener implements Listener {

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent useEvent) {
        if (!(useEvent.getRightClicked() instanceof Player))
            return;

        Player p = useEvent.getPlayer();
        Player t = (Player) useEvent.getRightClicked();

        ItemStack is = p.getItemInHand();

        // Player can use items on other players to heal them while sneaking
        if (!p.isSneaking() || is == null)
            return;

        if (is.isSimilar(Data.customItems.get("splint"))) {
            mendLegs(p, t);
            useEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent useEvent) {
        Player p = useEvent.getPlayer();
        Action a = useEvent.getAction();

        if (a != Action.RIGHT_CLICK_AIR && a != Action.RIGHT_CLICK_BLOCK)
            return;

        ItemStack is = useEvent.getItem();

        if (is == null)
            return;

        if (is.isSimilar(Data.customItems.get("splint"))) {
            mendLegs(p, p);
            useEvent.setCancelled(true);
        }
    }

    public void mendLegs(Player p, Player t) {
        PlayerData td = Data.getPlayerData(t.getUniqueId());

        if (td.hasBrokenLegs()) {
            td.setWalkSpeed(td.getWalkSpeed() + 0.1F);
            td.setBrokenLegs(false);
            Data.savePlayer(t.getUniqueId());

            useItemInHand(p);
            t.setWalkSpeed(td.getWalkSpeed());

            if (p == t) {
                Msg.MEND_LEGS.sendTo(p);
            } else {
                Msg.MEND_LEGS_OTHER.sendTo(p, t.getName());
                Msg.MEND_LEGS_OTHER_NOTICE.sendTo(t, p.getName());
            }
        }
    }

    public void useItemInHand(Player p) {
        ItemStack is = p.getItemInHand();
        is.setAmount(is.getAmount() - 1);
        p.setItemInHand(is);
    }

}
