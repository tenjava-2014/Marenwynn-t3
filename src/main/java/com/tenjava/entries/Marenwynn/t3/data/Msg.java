package com.tenjava.entries.Marenwynn.t3.data;

import org.bukkit.command.CommandSender;

import com.tenjava.entries.Marenwynn.t3.Util;

public enum Msg {

    BANDAGE_OTHER("&aYou patch up &6%s&a... hopefully soon enough."),
    BANDAGE_OTHER_NOTICE("&6%s &abandages your wounds. How considerate!"),
    BANDAGE_SELF("&aYou bandage up your wounds. There was so much blood..."),
    MEND_LEGS("&aYou splint your broken leg. That's a load off..."),
    MEND_LEGS_OTHER("&aYou splint &6%s&a's leg."),
    MEND_LEGS_OTHER_NOTICE("&6%s &asplints your broken leg. Much better!"),
    NOTICE_BLEEDING("&cYou're bleeding!"),
    NOTICE_BROKEN_LEGS("&cOne of your legs is in bad shape."),
    PREFIX_ERROR("&cError: &f"),
    YELL("&c&o\"%s\" yells %s."),
    YELL_BLEEDING("Touché!"),
    YELL_BROKEN_LEG("Fuck! My leg!!");

    private final String defaultMsg;

    Msg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    @Override
    public String toString() {
        return Util.color(Data.getMsg(this));
    }

    public void sendErrorTo(CommandSender sender) {
        sender.sendMessage(PREFIX_ERROR.toString() + toString());
    }

    public void sendErrorTo(CommandSender sender, Object... args) {
        sender.sendMessage(PREFIX_ERROR.toString() + String.format(toString(), args));
    }

    public void sendTo(CommandSender sender) {
        sender.sendMessage(toString());
    }

    public void sendTo(CommandSender sender, Object... args) {
        sender.sendMessage(String.format(toString(), args));
    }

}
