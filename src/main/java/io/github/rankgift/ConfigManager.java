package io.github.rankgift.utils;

import io.github.rankgift.RankGift;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private static Map<String, Integer> rankPrices = new HashMap<>();

    public static void init(RankGift plugin) {
        FileConfiguration config = plugin.getConfig();
        for (String rank : config.getConfigurationSection("rank-prices").getKeys(false)) {
            int price = config.getInt("rank-prices." + rank);
            rankPrices.put(rank.toUpperCase(), price);
        }
    }

    public static int getPrice(String rank) {
        return rankPrices.getOrDefault(rank.toUpperCase(), -1);
    }
}
