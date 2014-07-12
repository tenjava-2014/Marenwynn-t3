package com.tenjava.entries.Marenwynn.t3.data;

import java.util.HashMap;
import java.util.Map;

import com.tenjava.entries.Marenwynn.t3.TenJava;

public class Data {

    private static TenJava          tj;
    private static Map<Msg, String> messages;

    public static void init(TenJava tj) {
        Data.tj = tj;
        messages = new HashMap<Msg, String>();
        loadConfig();
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

}
