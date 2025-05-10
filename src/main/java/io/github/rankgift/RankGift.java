package io.github.rankgift;

import io.github.rankgift.commands.GiftCommand;
import io.github.rankgift.commands.RankCommand;
import io.github.rankgift.tasks.MVPPlusPlusChecker;
import io.github.rankgift.utils.ConfigManager;
import io.github.rankgift.utils.CoinsUtils;
import io.github.rankgift.utils.RankUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class RankGift extends JavaPlugin {

    private static RankGift instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        ConfigManager.init(this);
        CoinsUtils.init(this);
        RankUtils.init(this);

        getCommand("gift").setExecutor(new GiftCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("setcoins").setExecutor(new SetCoinsCommand());
        
        getServer().getScheduler().runTaskTimer(this, new MVPPlusPlusChecker(), 0L, 20L * 60 * 60 * 24); // 每天檢查
        getLogger().info("RankGift 插件已啟用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("RankGift 插件已停用。");
    }

    public static RankGift getInstance() {
        return instance;
    }
}
