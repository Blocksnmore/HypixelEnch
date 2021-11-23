package io.github.blocksnmore.hypixelench.events;

import io.github.blocksnmore.hypixelench.Hypixelench;
import io.github.blocksnmore.hypixelench.gui.EnchantGui;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnchantTableClick implements Listener {
    @EventHandler
    public void onEnchantTableClick(PlayerInteractEvent e){
        Block clickedBlock = e.getClickedBlock();
        if (clickedBlock == null) return;
        if (clickedBlock.getType().equals(Material.ENCHANTING_TABLE)){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!Hypixelench.config.getBoolean("enchgui.disabletable")) {
                    e.setCancelled(true);
                    EnchantGui.openGui(e.getPlayer(), null);
                }
            }
        }
    }
}
