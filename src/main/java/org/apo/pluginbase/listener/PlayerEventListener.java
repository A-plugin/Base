package org.apo.pluginbase.listener;

import org.apo.pluginbase.PluginBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerEventListener implements Listener {
    PluginBase pb=new PluginBase();
    FileConfiguration config = pb.getConfig();
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p=e.getPlayer();
        pb.getData();
        pb.title = pb.title.replace("%player%", p.getName());
        pb.title = pb.title.replace("&", "§");
        pb.titleKo = pb.titleKo.replace("%player%", p.getName());
        pb.titleKo = pb.titleKo.replace("&", "§");
        pb.sub = pb.sub.replace("%player%", p.getName());
        pb.sub =pb.sub.replace("&", "§");
        pb.subKo = pb.subKo.replace("%player%", p.getName());
        pb.subKo = pb.subKo.replace("&", "§");
        pb.jm = pb.jm.replace("%player%", p.getName());
        pb.jm = pb.jm.replace("&", "§");
        pb.kojoin = pb.kojoin.replace("%player%", p.getName());
        pb.kojoin = pb.kojoin.replace("&", "§");

        if (config.getBoolean("title.enable")) {
            if (!pb.title.isEmpty()){
                if (config.getBoolean("title.title.enable") && !config.getBoolean("title.sub.enable")) {
                    if (p.getLocale().startsWith("ko")) {
                        p.sendTitle(pb.titleKo, "", 10, 80, 10);
                    } else {
                        p.sendTitle(pb.title, "", 10, 80, 10);
                    }
                } else if (!config.getBoolean("title.title.enable") && config.getBoolean("title.sub.enable")) {
                    if (p.getLocale().startsWith("ko")) {
                        p.sendTitle("", pb.subKo, 10, 80, 10);
                    } else {
                        p.sendTitle("", pb.sub, 10, 80, 10);
                    }
                } else {
                    if (p.getLocale().startsWith("ko")) {
                        p.sendTitle(pb.titleKo, pb.subKo, 10, 80, 10);
                    } else {
                        p.sendTitle(pb.title, pb.sub, 10, 80, 10);
                    }
                }
            } else {
                p.sendMessage("null");
            }
        }
        if (config.getBoolean("join-message.enable")) {
            if (!config.getBoolean("join-message.admin-join-log")) {
                if (e.getPlayer().getLocale().startsWith("ko")) {
                    e.setJoinMessage(pb.kojoin);
                } else {
                    e.setJoinMessage(pb.jm);
                }
            } else {
                if (p.isOp()) {
                    pb.getLogger().info(ChatColor.AQUA+"ADMIN JOINED");
                    e.setJoinMessage("");
                } else {
                    if (e.getPlayer().getLocale().startsWith("ko")) {
                        e.setJoinMessage(pb.kojoin);
                    } else {
                        e.setJoinMessage(pb.jm);
                    }
                }
            }
        }
    }

    @EventHandler
    public void left(PlayerQuitEvent e) {
        Player p=e.getPlayer();
        pb.lm=pb.lm.replace("%player%", p.getName());
        pb.lm = pb.lm.replace("&", "§");
        pb.koleft=pb.koleft.replace("%player%", p.getName());
        pb.koleft=pb.koleft.replace("&", "§");
        if (config.getBoolean("join-message.enable")) {
            if (!config.getBoolean("join-message.admin-join-log")) {
                if (e.getPlayer().getLocale().startsWith("ko")) {
                    e.setQuitMessage(pb.koleft);
                } else {
                    e.setQuitMessage(pb.lm);
                }
            } else {
                if (p.isOp()) {
                    pb.getLogger().info(ChatColor.AQUA+"ADMIN JOINED");
                    e.setQuitMessage("");
                } else {
                    if (e.getPlayer().getLocale().startsWith("ko")) {
                        e.setQuitMessage(pb.koleft);
                    } else {
                        e.setQuitMessage(pb.lm);
                    }
                }
            }
        }
    }
    @EventHandler
    public void chat(PlayerChatEvent e) {
        Player p=e.getPlayer();
        if (config.getBoolean("chat.enable")) {
            e.setCancelled(true);
            pb.chat=config.getString("chat.txt", "");
            if ((!config.getBoolean("perfix.enable"))) {
                pb.chat = pb.chat.replace("%player%", p.getName());
            } else {
                if (Objects.isNull(config.getString("perfix."+p.getName()))) {
                    pb.chat = pb.chat.replace("%player%", p.getName());
                } else {
                    pb.chat=pb.chat.replace("%player%", config.getString("perfix."+p.getName())+p.getName());
                }
            }
            pb.chat=pb.chat.replace("[chat]", e.getMessage());
            pb.chat=pb.chat.replace("&", "§");
            if (config.getBoolean("chat.distance-chat")) {
                for (Player p1 : Bukkit.getOnlinePlayers()) {
                    if (p1.getLocation().distance(p.getLocation()) <= config.getInt("chat.distance")) {
                        p1.sendMessage(pb.chat);
                        pb.getLogger().info(pb.chat);
                    }
                }
            } else {
                Bukkit.broadcastMessage(pb.chat);
            }
        }
    }
}
