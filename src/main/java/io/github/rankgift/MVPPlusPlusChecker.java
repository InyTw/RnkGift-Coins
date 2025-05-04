package io.github.rankgift.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MVPPlusPlusChecker {
    public static void checkAndDowngrade(Player player) {
        String rank = RankUtils.getRank(player);
        if (!rank.equals("MVP++")) return;

        int cost = ConfigManager.getPrice("MVP++");
        boolean removed = CoinsUtils.removeCoins(player.getUniqueId(), cost);
        if (!removed) {
            RankUtils.setRank(player, "MVP+");
            player.sendMessage("§c你沒有足夠的 Coins，MVP++ 已降級為 MVP+");
        }
    }
}
