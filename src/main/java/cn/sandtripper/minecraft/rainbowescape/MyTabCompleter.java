package cn.sandtripper.minecraft.rainbowescape;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTabCompleter implements TabCompleter {
    private RainbowEscape plugin;

    public MyTabCompleter(RainbowEscape plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        // 在这里添加逻辑来决定哪些建议应该返回
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions = Arrays.asList("reload");
        }
        return suggestions;
    }
}