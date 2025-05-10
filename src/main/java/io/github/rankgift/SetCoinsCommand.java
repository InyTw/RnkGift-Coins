package io.github.rankgift;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetCoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rankgift.setcoins")) {
            sender.sendMessage("§c你沒有權限使用此指令！");
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("§e用法: /setcoins <玩家> <數量>");
            return true;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§c找不到玩家 " + args[0]);
            return true;
        }

        try {
            int amount = Integer.parseInt(args[1]);
            if (amount < 0) {
                sender.sendMessage("§c數量不能小於 0！");
                return true;
            }

            CoinsUtils.setCoins(target, amount);
            sender.sendMessage("§a成功設定 " + target.getName() + " 的 Coins 為 " + amount + "。");

        } catch (NumberFormatException e) {
            sender.sendMessage("§c請輸入正確的數字！");
        }

        return true;
    }
}
