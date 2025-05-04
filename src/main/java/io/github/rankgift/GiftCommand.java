package io.github.rankgift.commands;

import io.github.rankgift.gui.GiftGUI;
import io.github.rankgift.utils.CoinsUtils;
import io.github.rankgift.utils.ConfigManager;
import io.github.rankgift.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiftCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {
            GiftGUI.openGiftGUI(player);
        } else if (args.length == 1) {
            String rank = args[0].toUpperCase();
            if (RankUtils.isValidRank(rank)) {
                int price = ConfigManager.getPrice(rank);
                if (CoinsUtils.getCoins(player) >= price) {
                    // 這裡會處理玩家選擇送給其他玩家的邏輯
                    player.sendMessage("§a你選擇了送出 " + rank + " 給其他玩家。");
                    // TODO: 這裡應該顯示選擇對象的 GUI
                } else {
                    player.sendMessage("§c你沒有足夠的 Coins！");
                }
            } else {
                player.sendMessage("§c無效的 Rank！");
            }
        }
        return true;
    }
}
