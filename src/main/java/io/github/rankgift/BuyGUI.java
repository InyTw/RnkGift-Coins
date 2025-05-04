package io.github.rankgift.gui;

import io.github.rankgift.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BuyGUI {
    public static void openBuyGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "購買 Rank");

        addRankItem(gui, Material.COAL, "VIP", player);
        addRankItem(gui, Material.COAL_BLOCK, "VIP+", player);
        addRankItem(gui, Material.GOLD_INGOT, "MVP", player);
        addRankItem(gui, Material.GOLD_BLOCK, "MVP+", player);
        addRankItem(gui, Material.DIAMOND, "MVP++", player);

        player.openInventory(gui);
    }

    private static void addRankItem(Inventory gui, Material mat, String rank, Player player) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + rank);
        meta.setLore(Arrays.asList("§7價格: §6" + ConfigManager.getPrice(rank) + " coins"));
        item.setItemMeta(meta);
        gui.addItem(item);
    }
}
