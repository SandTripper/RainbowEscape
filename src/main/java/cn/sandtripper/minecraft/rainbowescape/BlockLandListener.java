package cn.sandtripper.minecraft.rainbowescape;

import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BlockLandListener implements Listener {
    RainbowEscape plugin;

    BlockLandListener( RainbowEscape plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockLand(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock) {
            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
            if (fallingBlock.hasMetadata("RainbowEscape")) {
                event.setCancelled(true);
            }
        }
    }
}
