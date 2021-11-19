package io.github.blocksnmore.hypixelench;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import io.github.blocksnmore.hypixelench.commands.Enchant;
import io.github.blocksnmore.hypixelench.events.EnchantTableClick;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hypixelench extends JavaPlugin {
    public static FileConfiguration config;
    public static PaperCommandManager cmdManager;
    public static Hypixelench instance;

    @Override
    public void onEnable() {
        getLogger().info("[HypixelEnchants] Starting plugin");
        getLogger().info("[HypixelEnchants] Made by Blocks_n_more");
        saveDefaultConfig();
        Hypixelench.config = getConfig();
        Hypixelench.cmdManager = new PaperCommandManager(this);
        Hypixelench.instance = this;
        registerEvents();
        getLogger().info("[HypixelEnchants] Started plugin");
    }

    private void registerEvents() {
        for (Listener listener : new Listener[]{
                new EnchantTableClick()
        }) {
            getServer().getPluginManager().registerEvents(listener, this);
        }

        for (BaseCommand command : new BaseCommand[]{
                new Enchant()
        }) {
            Hypixelench.cmdManager.registerCommand(command);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("[HypixelEnchants] Thanks for using my plugin");
    }
}
