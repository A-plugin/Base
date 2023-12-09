package org.apo.pluginbase.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    List<String> arguments = new ArrayList<String>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1){
            if (arguments.isEmpty()) {
                arguments.add("reload");
                arguments.add("list");
                arguments.add("motd");
                arguments.add("perfix");
                arguments.add("enable");
            }
        }
        List<String> result1 = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result1.add(a);
                }
            }
        } else if (args.length == 2) {
            if ("enable".equalsIgnoreCase(args[0])) {
                result1.clear();
                result1.add("title");
                result1.add("title-title");
                result1.add("title-subtitle");
                result1.add("join-message");
                result1.add("join-message_AdminLog");
                result1.add("tab");
                result1.add("motd");
                result1.add("chat");
                result1.add("distance-chat");
                result1.add("perfix");
            }
        } else if (args.length == 3) {
            result1.clear();
            result1.add("true");
            result1.add("false");
        }
        return result1;
    }
}
