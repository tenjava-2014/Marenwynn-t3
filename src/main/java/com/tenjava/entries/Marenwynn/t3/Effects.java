package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.entity.Player;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.data.Msg;
import com.tenjava.entries.Marenwynn.t3.data.PlayerData;
import com.tenjava.entries.Marenwynn.t3.tasks.BleedPlayer;

public class Effects {

    private static TenJava tj;

    public static void init(TenJava tj) {
        Effects.tj = tj;
    }

    public static void bleedPlayer(Player p, int severity) {
        // Severity makes bleeding more frequent
        Data.bleedTasks.put(p.getUniqueId(),
                (BleedPlayer) new BleedPlayer(p, severity).runTaskTimer(tj, 0, 20L * (100 - severity)));
    }

    public static void breakLegs(Player p, int severity) {
        PlayerData pd = Data.getPlayerData(p.getUniqueId());

        if (!pd.hasBrokenLegs()) {
            pd.setWalkSpeed(pd.getWalkSpeed() - 0.1F);
            pd.setBrokenLegs(true);
            Data.savePlayer(p.getUniqueId());

            p.setWalkSpeed(pd.getWalkSpeed());
            // The graver the injury, the louder one would yell
            Util.playerYell(p, Msg.YELL_BROKEN_LEG, severity);
            Msg.NOTICE_BROKEN_LEGS.sendTo(p);
        }
    }

}
