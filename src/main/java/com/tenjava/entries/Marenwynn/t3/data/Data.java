package com.tenjava.entries.Marenwynn.t3.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.tenjava.entries.Marenwynn.t3.TenJava;

public class Data {

    private static TenJava               tj;
    private static Map<Msg, String>      messages;

    private static File                  playerDataFolder;
    private static Map<UUID, PlayerData> players;

    public static void init(TenJava tj) {
        Data.tj = tj;
        messages = new HashMap<Msg, String>();
        loadConfig();

        playerDataFolder = new File(tj.getDataFolder().getPath() + File.separator + "players");
        players = new HashMap<UUID, PlayerData>();

        if (!playerDataFolder.exists())
            playerDataFolder.mkdirs();
    }

    public static void kill() {
        messages = null;
    }

    public static void loadConfig() {
        tj.saveDefaultConfig();
        messages.clear();

        for (Msg msg : Msg.values())
            messages.put(msg, tj.getConfig().getString("Language." + msg.name(), msg.getDefaultMsg()));
    }

    public static String getMsg(Msg msg) {
        return messages.get(msg);
    }

    public static PlayerData getPlayerData(UUID playerUUID) {
        return players.get(playerUUID);
    }

    public static PlayerData loadPlayer(UUID playerUUID) {
        File playerDataFile = new File(playerDataFolder + File.separator + playerUUID);

        if (playerDataFile.exists()) {
            Object uncasted = null;

            try {
                FileInputStream fis = new FileInputStream(playerDataFile);
                ObjectInputStream ois = new ObjectInputStream(fis);

                try {
                    uncasted = ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (uncasted != null && uncasted instanceof PlayerData) {
                players.put(playerUUID, (PlayerData) uncasted);
            }
        } else {
            players.put(playerUUID, new PlayerData());
        }

        return players.get(playerUUID);
    }

    public static void unloadPlayer(UUID playerUUID) {
        if (players.containsKey(playerUUID))
            players.remove(playerUUID);
    }

    public static void savePlayer(UUID playerUUID) {
        File playerDataFile = new File(playerDataFolder + File.separator + playerUUID);

        try {
            FileOutputStream fos = new FileOutputStream(playerDataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            try {
                oos.writeObject(players.get(playerUUID));
            } finally {
                oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAllPlayers() {
        for (UUID playerUUID : players.keySet())
            savePlayer(playerUUID);
    }

}
