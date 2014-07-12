package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.entity.Player;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.data.Msg;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;

public class Effects {

    private static TenJava tj;

    public static void init(TenJava tj) {
        Effects.tj = tj;
    }

    public static void breakLegs(Player p, int severity, boolean renew) {
        PlayerData pd = Data.getPlayerData(p.getUniqueId());

        if (!pd.hasBrokenLegs()) {
            pd.setBrokenLegs(true);
            p.setWalkSpeed(0.1F);

            // The graver the injury, the louder one would yell
            if (!renew)
                Util.playerYell(p, Msg.YELL_BROKEN_LEG, severity);

            Msg.NOTICE_BROKEN_LEGS.sendTo(p);
        }
    }

}
