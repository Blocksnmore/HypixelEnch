package io.github.blocksnmore.hypixelench.events;

import io.github.blocksnmore.hypixelench.gui.EnchantGui;
import io.github.blocksnmore.hypixelench.utils.Color;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;


public class EnchantGuiClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        InventoryView view = e.getView();
        HumanEntity p = e.getView().getPlayer();
        ItemStack clickedItem = e.getCurrentItem();
        if (view.title().equals(Color.applyColor("&2Enchant"))) {
            if (e.getRawSlot() < e.getInventory().getSize()) {
                if (clickedItem == null) {
                    if (e.getCursor() != null){
                        EnchantGui.openGui((Player) p, e.getCursor());
                    }
                    return;
                }
                p.sendMessage(clickedItem.displayName());
                if (e.getSlot() != 28) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
