package org.apo.pluginbase.Commands;

import org.apo.pluginbase.PluginBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
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
                    p.sendMessage(Color.GREEN+"Base plugin reload success.");
                } else if (args[0].equalsIgnoreCase("list")) {
                    pb.getData();
                    p.sendMessage(ChatColor.GREEN+"====BASE====\n" +
                            Color.BLUE+"title : "+Color.WHITE+pb.title+
                            Color.BLUE+"\nsub title : "+Color.WHITE+pb.sub+
                            Color.BLUE+"\njoinmessage : "+Color.WHITE+pb.jm+
                            Color.BLUE+"\nleftmessage : "+Color.WHITE+ pb.lm+
                            Color.BLUE+"\nChatting : "+Color.WHITE+ pb.chat +
                            Color.BLUE+"\nmotd : "+Color.WHITE+config.getString("motd.txt")+
                            Color.BLUE+"\nTab header : " +Color.WHITE+ pb.header+
                            Color.BLUE+"\nTab footer : " +Color.WHITE+ pb.footer+
                            Color.GREEN+"\n========="
                    );
                } else if (args[0].equalsIgnoreCase("motd")) {
                    if (args.length>=1) {
                        pb.getServer().setMotd(config.getString("motd.txt").replace("&", "§"));
                        p.sendMessage(Color.RED+"Motd Set");
                    } else {
                        pb.getConfig().set("motd.txt", args[1]);
                        pb.getServer().setMotd(args[1].replace("&", "§"));
                        p.sendMessage(Color.GREEN+"Motd set"+args[1]);
                        pb.saveConfig();
                    }
                } else if (args[0].equalsIgnoreCase("")) {
                    
                }
            } else {
                if (p.getLocale().startsWith("ko")) {
                    p.sendMessage(Color.RED+"당신은 권한을 가지고 있지 않습니다!");
                } else {
                    p.sendMessage(Color.RED+"You don't have permission");
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
