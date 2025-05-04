package io.github.rankgift.listeners;

import io.github.rankgift.gui.ConfirmGUI;
import io.github.rankgift.utils.CoinsUtils;
import io.github.rankgift.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfirmGUIListener implements Listener {

    @EventHandler
    public void onConfirmClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getView().getTitle().startsWith("是否接受")) return;

        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta() || !clicked.getItemMeta().hasDisplayName()) return;

        String displayName = clicked.getItemMeta().getDisplayName();
        ConfirmGUI.PendingConfirm data = ConfirmGUI.pendingMap.get(player.getUniqueId());
        if (data == null) return;

        Player sender = Bukkit.getPlayer(data.sender);

        if (displayName.contains("接受")) {
            RankUtils.setRank(player, data.rank);
            CoinsUtils.removeCoins(data.sender, data.price);

            player.sendMessage("§a你已接受 Rank: " + data.rank);
            if (sender != null)
                sender.sendMessage("§a" + player.getName() + " 接受了你的 Rank 贈送！");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        } else if (displayName.contains("拒絕")) {
            CoinsUtils.addCoins(data.sender, data.price);
            player.sendMessage("§c你拒絕了該 Rank。");
            if (sender != null)
                sender.sendMessage("§e" + player.getName() + " 拒絕了你的贈送，Coins 已退回。");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
        }

        ConfirmGUI.pendingMap.remove(player.getUniqueId());
        player.closeInventory();
    }
}
