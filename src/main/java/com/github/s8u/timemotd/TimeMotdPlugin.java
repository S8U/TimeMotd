package com.github.s8u.timemotd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeMotdPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onPing(ServerListPingEvent event) {
        long time = Bukkit.getWorlds().get(0).getTime();

        int hour = (int) Math.floor(time / 1000) + 6; // 0 Tick = 6 AM
        if (hour >= 24) {
            hour -= 24;
        }
        int minute = (int) Math.floor((time % 1000) / (1000 / 60));
        String ampm = hour >= 12 ? "PM" : "AM";

        ChatColor color = (18 < hour || hour < 6) ? ChatColor.DARK_BLUE : ChatColor.AQUA;

        event.setMotd(color + ampm + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute));
    }

}