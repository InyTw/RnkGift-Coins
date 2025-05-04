package io.github.rankgift.listeners;

import io.github.rankgift.gui.ConfirmBookGUI;
import io.github.rankgift.utils.CoinsUtils;
import io.github.rankgift.utils.ConfigManager;
import io.github.rankgift.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiftInventoryListener implements Listener {

    @EventHandler
    public void onGiftGUIClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (!event.getView().getTitle().equals("Rank 贈送 GUI")) return;

        event.setCancelled(true); // 防止物品被拖動
        Player sender = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        ItemMeta meta = clicked.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String name = ChatColor.stripColor(meta.getDisplayName());

        // 判斷點到哪個 Rank
        String rank = switch (name) {
            case "VIP", "VIP+" -> name;
            case "MVP", "MVP+", "MVP++" -> name;
            default -> null;
        };

        if (rank != null) {
            // TODO: 改為彈出讓玩家選擇目標玩家或進入書本確認
            sender.sendMessage("你選擇了要送出或購買 Rank: " + rank);
            ConfirmBookGUI.sendConfirmBook(sender, sender, rank, ConfigManager.getPrice(rank));
            sender.closeInventory();
        }

        // 點擊 Coins 確認按鈕時（可進一步處理）
        if (name.startsWith("Coins")) {
            sender.sendMessage("請先選擇 Rank，再進行確認。");
        }
    }
}
