package io.github.blocksnmore.hypixelench.utils;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    private final static Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]){6}");
    public static Component applyColor(String message) {
        return Component.text(Color.applyStringColor(message));
    }
    public static String applyStringColor(String message) {
        Matcher matcher = hexPattern.matcher(message);
        while (matcher.find()) {
            final ChatColor hexColor = ChatColor.of(matcher.group().substring(1));
            final String before = message.substring(0, matcher.start());
            final String after = message.substring(matcher.end());
            message = before + hexColor + after;
            matcher = hexPattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
