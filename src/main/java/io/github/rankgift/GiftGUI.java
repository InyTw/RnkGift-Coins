package io.github.rankgift.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GiftGUI {
    public static void openGiftGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "Rank 贈送 GUI");

        gui.setItem(10, createRankItem(Material.COAL, "§aVIP"));
        gui.setItem(12, createEnchantedRankItem(Material.COAL, "§aVIP+"));
        gui.setItem(14, createRankItem(Material.GOLD_INGOT, "§bMVP"));
        gui.setItem(16, createEnchantedRankItem(Material.GOLD_INGOT, "§bMVP+"));
        gui.setItem(20, createRankItem(Material.DIAMOND, "§eMVP++"));
        gui.setItem(49, createEnchantedRankItem(Material.EMERALD, "§aCoins: 點我確認"));

        player.openInventory(gui);
    }

    private static ItemStack createRankItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createEnchantedRankItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.setLore(Arrays.asList("§7點擊以送出或購買"));
        item.setItemMeta(meta);
        return item;
    }
}
