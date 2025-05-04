package io.github.rankgift.commands;

import io.github.rankgift.gui.BuyGUI;
import io.github.rankgift.utils.CoinsUtils;
import io.github.rankgift.utils.ConfigManager;
import io.github.rankgift.utils.RankUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {
            player.sendMessage("/rank <rank> 或 /rank buy");
            return true;
        }

        if (args[0].equalsIgnoreCase("buy")) {
            BuyGUI.openBuyGUI(player);
            return true;
        }

        String rank = args[0].toUpperCase();
        int price = ConfigManager.getPrice(rank);

        if (price == -1) {
            player.sendMessage("無效的 Rank: " + rank);
            return true;
        }

        if (CoinsUtils.removeCoins(player.getUniqueId(), price)) {
            RankUtils.setRank(player, rank);
            player.sendMessage("你已購買了 Rank: " + rank);
        } else {
            player.sendMessage("你沒有足夠的 Coins。");
        }
        return true;
    }
}
