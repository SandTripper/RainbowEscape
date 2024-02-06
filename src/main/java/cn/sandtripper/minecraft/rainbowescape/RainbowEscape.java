package cn.sandtripper.minecraft.rainbowescape;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public final class RainbowEscape extends JavaPlugin {

    List<Material> colors;
    private double speed;
    private int tickBlockKeep;
    private int tickBlockInterval;
    private int tickEffectDuration;
    private HashSet<String> flyingPlayer;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getCommand("RainbowEscape").setExecutor(new CommandHandler(this));
        getCommand("RainbowEscape").setTabCompleter(new MyTabCompleter(this));
        loadConfig();
        getServer().getPluginManager().registerEvents(new BlockLandListener(this), this);
        flyingPlayer = new HashSet<>();

        int pluginId = 20925;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void startEscape(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§c只有玩家可以执行这个指令!");
            return;
        }
        Player player = (Player) commandSender;
        Vector direction = player.getLocation().getDirection();
        direction.multiply(speed); // 调整这个值来改变速度
        player.setVelocity(direction);
        if (!flyingPlayer.contains(player.getName())) {
            flyingPlayer.add(player.getName());

            RainbowEscape ins = this;
            new BukkitRunnable() {
                int ticks = 0;

                @Override
                public void run() {
                    if (ticks >= 5 && (!player.isOnline() || player.isOnGround()) || ticks >= tickEffectDuration) {
                        flyingPlayer.remove(player.getName());
                        this.cancel();
                        return;
                    }
                    Location loc = player.getLocation().subtract(0, 1, 0); // 玩家脚下的位置
                    FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(loc, colors.get(ticks % colors.size()).createBlockData());
                    fallingBlock.setMetadata("RainbowEscape", new FixedMetadataValue(ins, true));
                    fallingBlock.setDropItem(false);
                    Bukkit.getScheduler().runTaskLater(ins, fallingBlock::remove, tickBlockKeep);
                    ticks++;
                }
            }.runTaskTimer(ins, 0L, tickBlockInterval);
        }
    }


    public void reload(CommandSender commandSender) {
        reloadConfig();
        loadConfig();
        commandSender.sendMessage("§a插件重载成功!");
    }

    private void loadConfig() {
        colors = new LinkedList<>();

        List<String> lst = getConfig().getStringList("materials");
        for (String s : lst) {
            Material material = Material.getMaterial(s);
            if (material != null) {
                colors.add(material);
            } else {
                getLogger().info("\033[31mError Material name: " + s + "\033[0m");
            }
        }

        speed = getConfig().getDouble("speed");
        tickBlockKeep = getConfig().getInt("tick-block-keep");
        tickBlockInterval = getConfig().getInt("tick-block-interval");
        tickEffectDuration = getConfig().getInt("tick-effect-duration");
    }
}
