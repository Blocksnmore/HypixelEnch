package io.github.blocksnmore.hypixelench.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import io.github.blocksnmore.hypixelench.gui.EnchantGui;
import org.bukkit.entity.Player;

@CommandAlias("enchant|ench|enchantments|enchantgui")
public class Enchant extends BaseCommand {
    @Default
    public void onDefault(Player p) {
        EnchantGui.openGui(p, null);
    }
}
