package io.github.rankgift.utils;

import io.github.rankgift.RankGift;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class RankUtils {
    private static LuckPerms lp;

    public static void init(RankGift plugin) {
        lp = plugin.getServer().getServicesManager().getRegistration(LuckPerms.class).getProvider();
    }

    public static void setRank(Player player, String rank) {
        CompletableFuture<User> future = lp.getUserManager().loadUser(player.getUniqueId());
        future.thenAcceptAsync(user -> {
            user.data().clear(); // 清空舊的權限組
            InheritanceNode node = InheritanceNode.builder(rank.toLowerCase()).value(true).build();
            user.data().add(node);
            lp.getUserManager().saveUser(user);
        });
    }
}
