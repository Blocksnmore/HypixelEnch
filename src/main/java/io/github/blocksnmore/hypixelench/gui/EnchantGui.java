package io.github.blocksnmore.hypixelench.gui;

import io.github.blocksnmore.hypixelench.Hypixelench;
import io.github.blocksnmore.hypixelench.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantGui {
    // Yes, I could automatically generate this list. Will I? No.
    private final static int[] bookSlots = new int[] {
      21, 22, 23, 24, 25,
      30, 31, 32, 33, 34,
      39, 40, 41, 42, 43
    };

    public static void openGui(Player p, ItemStack playerItem) {
        EnchantGui.openGui(p, playerItem, Bukkit.createInventory(p, 9 * 6, Color.applyColor("&2Enchant")));
    }

    public static void openGui(Player p, ItemStack playerItem, Inventory playerInventory){
        if (playerInventory == null) return;
        ItemStack filler = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        ArrayList<Enchantment> enchantments = EnchantGui.enchantsForItem(playerItem);

        fillerMeta.displayName(Color.applyColor("&r"));
        filler.setItemMeta(fillerMeta);

        for (int i = 0; i < 6 * 9; i++) {
            playerInventory.setItem(i, filler);
        }

        for (int slot : EnchantGui.bookSlots) {
            ItemStack fillerItem = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta fillerItemMeta = fillerItem.getItemMeta();
            if (playerItem == null) {
                fillerItemMeta.displayName(Color.applyColor("&cInsert an item to view enchants"));
            } else {
                fillerItemMeta.displayName(Color.applyColor("&f"));
            }
            fillerItem.setItemMeta(fillerItemMeta);
            playerInventory.setItem(slot, fillerItem);
        }

        p.sendMessage(String.valueOf(enchantments.size()));

        for (int slot = 0; slot < Math.min(enchantments.size(), EnchantGui.bookSlots.length); slot++) {
            Enchantment enchantment = enchantments.get(slot);
            ItemStack enchantItem = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta enchantItemMeta = enchantItem.getItemMeta();
            enchantItemMeta.displayName(Color.applyColor("&e"+EnchantGui.namespaceToString(enchantment.getKey().toString())));
            enchantItem.setItemMeta(enchantItemMeta);
            playerInventory.setItem(EnchantGui.bookSlots[slot], enchantItem);
        }

        playerInventory.setItem(28, playerItem);

        ItemStack pageNavigator = new ItemStack(Material.ARROW);
        ItemMeta pageNavigatorMeta = pageNavigator.getItemMeta();
        ItemStack pageCategory = new ItemStack(Material.PAPER);
        ItemMeta pageCategoryMeta = pageCategory.getItemMeta();
        ItemStack closeMenu = new ItemStack(Material.BARRIER);
        ItemMeta closeMenuMeta = closeMenu.getItemMeta();
        ItemStack xpBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta xpBottleMeta = xpBottle.getItemMeta();

        // Xp bottle
        xpBottleMeta.displayName(Color.applyColor("&2Your xp: "+ p.getLevel()));
        xpBottle.setItemMeta(xpBottleMeta);
        playerInventory.setItem(10, xpBottle);

        // Close
        closeMenuMeta.displayName(Color.applyColor("&cClose"));
        closeMenu.setItemMeta(closeMenuMeta);
        playerInventory.setItem(49, closeMenu);

        // Page Navigator
        pageNavigatorMeta.displayName(Color.applyColor("&ePrevious page"));
        pageNavigator.setItemMeta(pageNavigatorMeta);
        playerInventory.setItem(2, pageNavigator);
        pageNavigatorMeta.displayName(Color.applyColor("&eNext page"));
        pageNavigator.setItemMeta(pageNavigatorMeta);
        playerInventory.setItem(6, pageNavigator);

        // Category
        pageCategoryMeta.displayName(Color.applyColor("&eCurrent page: 1"));
        pageCategory.setItemMeta(pageCategoryMeta);
        playerInventory.setItem(4, pageCategory);

        p.openInventory(playerInventory);
    }

    // For some reason this returns 0 even when it shouldn't
    public static ArrayList<Enchantment> enchantsForItem(ItemStack item) {
        ArrayList<Enchantment> workingEnchants = new ArrayList<>();
        List<String> disabledEnchants = Hypixelench.config.getStringList("enchgui.disableenchants");

        for (Enchantment enchantment : EnchantGui.getAllEnchants()) {
            if (disabledEnchants.contains(enchantment.getKey().toString().substring("minecraft:".length())))
            if (enchantment.canEnchantItem(item)) {
                workingEnchants.add(enchantment);
            }
        }

        return workingEnchants;
    }

    public static Enchantment[] getAllEnchants() {
                return new Enchantment[]{
                        Enchantment.DURABILITY,
                        Enchantment.MENDING,
                        Enchantment.LOOT_BONUS_BLOCKS,
                        Enchantment.DIG_SPEED,
                        Enchantment.SILK_TOUCH,
                        Enchantment.DAMAGE_ALL,
                        Enchantment.DAMAGE_ARTHROPODS,
                        Enchantment.DAMAGE_UNDEAD,
                        Enchantment.FIRE_ASPECT,
                        Enchantment.KNOCKBACK,
                        Enchantment.ARROW_INFINITE,
                        Enchantment.ARROW_DAMAGE,
                        Enchantment.ARROW_FIRE,
                        Enchantment.ARROW_KNOCKBACK,
                        Enchantment.SWEEPING_EDGE,
                        Enchantment.CHANNELING,
                        Enchantment.IMPALING,
                        Enchantment.LOYALTY,
                        Enchantment.LUCK,
                        Enchantment.LURE,
                        Enchantment.MULTISHOT,
                        Enchantment.PIERCING,
                        Enchantment.QUICK_CHARGE,
                        Enchantment.RIPTIDE,
                        Enchantment.DEPTH_STRIDER,
                        Enchantment.FROST_WALKER,
                        Enchantment.OXYGEN,
                        Enchantment.PROTECTION_ENVIRONMENTAL,
                        Enchantment.PROTECTION_EXPLOSIONS,
                        Enchantment.PROTECTION_FALL,
                        Enchantment.PROTECTION_FIRE,
                        Enchantment.PROTECTION_PROJECTILE,
                        Enchantment.SOUL_SPEED,
                        Enchantment.THORNS,
                        Enchantment.WATER_WORKER
                };
    }

    private static String namespaceToString(String name){
        ArrayList<String> stringParts = new ArrayList<>();
        name = name.substring(name.indexOf(":") + 1);
        String[] splitName = name.split("_");
        for (String part : splitName) {
            stringParts.add(part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase());
        }
        return String.join(" ", stringParts);
    }
}
