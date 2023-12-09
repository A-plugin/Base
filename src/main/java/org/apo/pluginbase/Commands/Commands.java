package org.apo.pluginbase.Commands;

import org.apo.pluginbase.PluginBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {
    PluginBase pb=new PluginBase();
    FileConfiguration config = pb.getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p=(Player) sender;
            if (p.hasPermission("Base.base")) {
                if (args[0].isEmpty()) {
                    return false;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    pb.reloadConfig();
                    config= pb.getConfig();
                    Bukkit.setMotd(Objects.requireNonNull(config.getString("motd.txt")
                            .replace("&", "§")));
                    p.sendMessage(ChatColor.GREEN+"Base plugin reload success.");
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
