package org.apo.pluginbase;

import org.apo.pluginbase.Commands.Commands;
import org.apo.pluginbase.Commands.TabComplete;
import org.apo.pluginbase.listener.PlayerEventListener;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginBase extends JavaPlugin {
    FileConfiguration config = getConfig();
    public String title = config.getString("title.title.txt", "");
    public String titleKo = config.getString("title.title.ko", "");
    public String sub = config.getString("title.sub.txt", "");
    public String subKo = config.getString("title.sub.ko", "");
    public String jm = config.getString("join-message.join", "");
    public String kojoin = config.getString("join.message.ko-join", "");
    public String lm= config.getString("join-message.left","");
    public String koleft=config.getString("join.message.ko-left", "");
    public String header= config.getString("tab.header", "");
    public String footer=config.getString("tab.footer", "");
    public String chat=config.getString("chat.txt", "");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
        getCommand("base").setExecutor(new Commands());
        getCommand("base").setTabCompleter(new TabComplete());
        getLogger().info(Color.AQUA+"Plugins By.APO");
        FileConfiguration config = getConfig();
        saveDefaultConfig();
    }

    public void getData() {
        title = config.getString("title.title.txt", "");
        titleKo = config.getString("title.title.ko", "");
        sub = config.getString("title.sub.txt", "");
        subKo = config.getString("title.sub.ko", "");
        jm = config.getString("join-message.join", "");
        kojoin = config.getString("join.message.ko-join", "");
        lm= config.getString("join-message.left","");
        koleft=config.getString("join.message.ko-left", "");
        header= config.getString("tab.header", "");
        footer=config.getString("tab.footer", "");
        chat=config.getString("chat.txt", "");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
