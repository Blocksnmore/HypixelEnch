package io.github.blocksnmore.hypixelench.gui;

import io.github.blocksnmore.hypixelench.utils.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantGui {

    public enum EnchantOption {
        MISC, MELEE, RANGED
    }

    public static void openGui(Player p, ItemStack playerItem) {
        EnchantGui.openGui(p, playerItem, EnchantOption.MISC);
    }

    public static void openGui(Player p, ItemStack playerItem, EnchantOption page){
        Inventory playerInventory = Bukkit.createInventory(p, 9 * 6, Component.text(Color.applyColor("&aEnchant")));
        ItemStack filler = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();

        fillerMeta.displayName(Component.text(Color.applyColor("&r")));
        filler.setItemMeta(fillerMeta);

        for (int i = 0; i < 6 * 9; i++) {
            playerInventory.setItem(i, filler);
        }

        ItemStack pageNavigator = new ItemStack(Material.ARROW);
        ItemMeta pageNavigatorMeta = pageNavigator.getItemMeta();
        ItemStack pageCategory = new ItemStack(Material.PAPER);
        ItemMeta pageCategoryMeta = pageCategory.getItemMeta();

        // Page Navigator
        pageNavigatorMeta.displayName(Component.text(Color.applyColor("&ePrevious page")));
        pageNavigator.setItemMeta(pageNavigatorMeta);
        playerInventory.setItem(3, pageNavigator);
        pageNavigatorMeta.displayName(Component.text(Color.applyColor("&eNext page")));
        pageNavigator.setItemMeta(pageNavigatorMeta);
        playerInventory.setItem(7, pageNavigator);

        // Category
        pageCategoryMeta.displayName(Component.text(Color.applyColor("&eCurrent page: "+ EnchantGui.optionToString(page))));

    }

    public static String optionToString(EnchantOption opt) {
        switch (opt) {
            case MISC: return "Misc";
            case MELEE: return "Melee";
            case RANGED: return "Ranged";
            default:
                return "Unknown";
        }
    }
}
