package cn.sandtripper.minecraft.rainbowescape;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
    RainbowEscape plugin;

    public CommandHandler(RainbowEscape plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String cmd, String[] args) {
        if(args.length == 0) {
            plugin.startEscape(commandSender);
        }
        else if (args.length == 1) {
            if (args[0].equals("reload")) {
                plugin.reload(commandSender);
            } else {
                commandSender.sendMessage("§c错误的用法");
            }
        } else {
            commandSender.sendMessage("§c错误的用法");
        }
        return true;
    }


}