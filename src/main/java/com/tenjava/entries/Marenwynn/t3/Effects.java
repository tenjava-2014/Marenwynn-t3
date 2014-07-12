package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.tenjava.entries.Marenwynn.t3.data.Msg;

public class Effects {

    private static TenJava tj;

    public static void init(TenJava tj) {
        Effects.tj = tj;
    }

    public static void breakLegs(Player p, int severity) {
        if (!p.hasMetadata("broken.legs")) {
            p.setWalkSpeed(p.getWalkSpeed() / 2F);
            p.setMetadata("broken.legs", new FixedMetadataValue(tj, true));

            // The graver the injury, the louder one would yell
            Util.playerYell(p, Msg.YELL_BROKEN_LEG, severity);
        }
    }

}
