package com.tenjava.entries.Marenwynn.t3.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import com.tenjava.entries.Marenwynn.t3.TenJava;
import com.tenjava.entries.Marenwynn.t3.Util;

public class Data {

    private static TenJava                tj;
    private static Map<Msg, String>       messages;

    private static File                   playerDataFolder;
    private static Map<UUID, PlayerData>  players;

    public static Map<String, ItemStack> customItems;

    public static void init(TenJava tj) {
        Data.tj = tj;
        messages = new HashMap<Msg, String>();
        loadConfig();

        playerDataFolder = new File(tj.getDataFolder().getPath() + File.separator + "players");
        players = new HashMap<UUID, PlayerData>();

        if (!playerDataFolder.exists())
            playerDataFolder.mkdirs();

        customItems = new HashMap<String, ItemStack>();

        // Create gauze
        List<String> lore = new ArrayList<String>();
        lore.add("&fCan be used to patch up");
        lore.add("&fflesh wounds.");
        lore.add("&8&oRight-click to use");

        ItemStack gauze = Util.setItemNameAndLore(new ItemStack(Material.PAPER, 1), "&aGauze", lore);
        customItems.put("gauze", gauze);

        ShapedRecipe sr = new ShapedRecipe(gauze);
        sr.shape("SW ", "SW ", "SW ");
        sr.setIngredient('S', Material.STRING);
        sr.setIngredient('W', Material.WOOL);
        tj.getServer().addRecipe(sr);

        // Create splint
        lore.clear();
        lore.add("&fBraces broken legs to");
        lore.add("restore mobility");
        lore.add("&8&oRight-click to use");

        ItemStack splint = Util.setItemNameAndLore(new ItemStack(Material.STICK, 1), "&aSplint", lore);
        customItems.put("splint", splint);

        sr = new ShapedRecipe(splint);
        sr.shape(" S ", " T ", " S ");
        sr.setIngredient('S', Material.STRING);
        sr.setIngredient('T', Material.STICK);
        tj.getServer().addRecipe(sr);
    }

    public static void kill() {
        // Clear custom recipes from server
        Iterator<Recipe> recipes = tj.getServer().recipeIterator();
        Recipe recipe;

        while (recipes.hasNext()) {
            recipe = recipes.next();

            if (recipe != null && customItems.containsValue(recipe.getResult()))
                recipes.remove();
        }

        tj = null;
        messages = null;
        playerDataFolder = null;
        players = null;
        customItems = null;
    }

    public static void loadConfig() {
        tj.saveDefaultConfig();

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
