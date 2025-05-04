package io.github.rankgift.utils;

import io.github.rankgift.RankGift;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CoinsUtils {
    private static File file;
    private static YamlConfiguration data;

    public static void init(RankGift plugin) {
        file = new File(plugin.getDataFolder(), "coins.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static int getCoins(UUID uuid) {
        return data.getInt(uuid.toString(), 0);
    }

    public static void addCoins(UUID uuid, int amount) {
        int current = getCoins(uuid);
        data.set(uuid.toString(), current + amount);
        save();
    }

    public static boolean removeCoins(UUID uuid, int amount) {
        int current = getCoins(uuid);
        if (current < amount) return false;
        data.set(uuid.toString(), current - amount);
        save();
        return true;
    }

    private static void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
