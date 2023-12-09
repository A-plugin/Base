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
                } else if (args[0].equalsIgnoreCase("list")) {
                    pb.getData();
                    p.sendMessage(ChatColor.GREEN+"====BASE====\n" +
                            ChatColor.BLUE+"title : "+ChatColor.WHITE+pb.title+
                            ChatColor.BLUE+"\nsub title : "+ChatColor.WHITE+pb.sub+
                            ChatColor.BLUE+"\njoinmessage : "+ChatColor.WHITE+pb.jm+
                            ChatColor.BLUE+"\nleftmessage : "+ChatColor.WHITE+ pb.lm+
                            ChatColor.BLUE+"\nChatting : "+ChatColor.WHITE+ pb.chat +
                            ChatColor.BLUE+"\nmotd : "+ChatColor.WHITE+config.getString("motd.txt")+
                            ChatColor.BLUE+"\nTab header : " +ChatColor.WHITE+ pb.header+
                            ChatColor.BLUE+"\nTab footer : " +ChatColor.WHITE+ pb.footer+
                            ChatColor.GREEN+"\n========="
                    );
                } else if (args[0].equalsIgnoreCase("motd")) {
                    if (args.length>=1) {
                        pb.getServer().setMotd(config.getString("motd.txt").replace("&", "§"));
                        p.sendMessage(ChatColor.RED+"Motd Set");
                    } else {
                        pb.getConfig().set("motd.txt", args[1]);
                        pb.getServer().setMotd(args[1].replace("&", "§"));
                        p.sendMessage(ChatColor.GREEN+"Motd set"+args[1]);
                        pb.saveConfig();
                    }
                } else if (args[0].equalsIgnoreCase("")) {
                    
                }
            } else {
                if (p.getLocale().startsWith("ko")) {
                    p.sendMessage(ChatColor.RED+"당신은 권한을 가지고 있지 않습니다!");
                } else {
                    p.sendMessage(ChatColor.RED+"You don't have permission");
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
