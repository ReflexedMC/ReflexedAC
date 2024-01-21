package mc.reflexed.ac;

import lombok.Getter;
import mc.reflexed.ac.check.CheckManager;
import mc.reflexed.ac.user.User;
import mc.reflexed.ac.util.ChatUtil;
import mc.reflexed.event.EventManager;
import mc.reflexed.event.data.EventInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;

@Getter
public class ReflexedAC {

    @Getter
    public static ReflexedAC instance;

    private final EventManager eventManager;
    private final CheckManager checkManager;

    private final AntiCheatConsumer antiCheatConsumer;

    public ReflexedAC(EventManager eventManager, AntiCheatConsumer antiCheatConsumer) {
        if(instance != null) {
            throw new RuntimeException("ReflexedAC instance already exists!");
        }

        instance = this;
        this.eventManager = eventManager;
        this.antiCheatConsumer = antiCheatConsumer;
        this.checkManager = new CheckManager();
        this.eventManager.register(this);

        Bukkit.getOnlinePlayers().forEach(player -> User.get(player).register()); // player's that are already online
        ChatUtil.broadcast(ChatUtil.getPrefix() + "ReflexedAC has been enabled!");
    }

    @EventInfo
    public void onJoin(PlayerJoinEvent event) {
        User.get(event.getPlayer()).register();
    }

    @EventInfo
    public void onQuit(PlayerQuitEvent event) {
        User.get(event.getPlayer()).unregister();
    }

}
