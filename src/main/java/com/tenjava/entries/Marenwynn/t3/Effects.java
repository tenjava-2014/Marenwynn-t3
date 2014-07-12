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
        PlayerData pd = Data.getPlayerData(p.getUniqueId());

        pd.setBleedSeverity(severity);
        pd.setBleeding(true);

        Data.bleedTasks.put(p.getUniqueId(), new BleedPlayer(p, pd).runTaskTimer(tj, 0, 20L * 10));

        if (!Data.bleedTasks.containsKey(p.getUniqueId())) {
            pd.setWalkSpeed(pd.getWalkSpeed() - 0.1F);
            Util.playerYell(p, Msg.YELL_BLEEDING, pd.getBleedSeverity());
        }

        Data.savePlayer(p.getUniqueId());

        p.setWalkSpeed(pd.getWalkSpeed());
        Msg.NOTICE_BLEEDING.sendTo(p);
    }

    public static void breakArm(Player p) {
        PlayerData pd = Data.getPlayerData(p.getUniqueId());
        pd.setBrokenArm(true);
        Data.savePlayer(p.getUniqueId());

        Msg.NOTICE_BROKEN_ARM.sendTo(p);
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
        } else {
            bleedPlayer(p, 10);
        }
    }

}
