package io.github.rankgift.gui;

import io.github.rankgift.utils.CoinsUtils;
import io.github.rankgift.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class ConfirmGUI {

    public static final HashMap<UUID, PendingConfirm> pendingMap = new HashMap<>();

    public static void openConfirmGUI(Player receiver, Player sender, String rank, int price) {
        Inventory gui = Bukkit.createInventory(null, 9, "是否接受 " + rank + "？");

        ItemStack accept = new ItemStack(Material.LIME_DYE);
        ItemMeta acceptMeta = accept.getItemMeta();
        acceptMeta.setDisplayName("§a接受");
        accept.setItemMeta(acceptMeta);

        ItemStack deny = new ItemStack(Material.RED_DYE);
        ItemMeta denyMeta = deny.getItemMeta();
        denyMeta.setDisplayName("§c拒絕");
        deny.setItemMeta(denyMeta);

        gui.setItem(2, accept);
        gui.setItem(6, deny);

        pendingMap.put(receiver.getUniqueId(), new PendingConfirm(sender.getUniqueId(), rank, price));
        receiver.openInventory(gui);
    }

    public static class PendingConfirm {
        public final UUID sender;
        public final String rank;
        public final int price;

        public PendingConfirm(UUID sender, String rank, int price) {
            this.sender = sender;
            this.rank = rank;
            this.price = price;
        }
    }
}
