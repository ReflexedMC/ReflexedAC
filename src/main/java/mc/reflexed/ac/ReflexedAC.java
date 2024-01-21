package mc.reflexed.ac;

import lombok.Getter;
import mc.reflexed.ac.check.CheckManager;
import mc.reflexed.ac.user.User;
import mc.reflexed.ac.util.ChatUtil;
import mc.reflexed.event.EventManager;
import mc.reflexed.event.data.EventInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Method;

@Getter
public enum ReflexedAC {
    INSTANCE;

    private final EventManager eventManager;
    private final CheckManager checkManager;

    ReflexedAC() {
        this.eventManager = getRegisteredEventManager();
        this.checkManager = new CheckManager();
        this.eventManager.register(this);
    }

    @EventInfo
    public void onJoin(PlayerJoinEvent event) {
        User.get(event.getPlayer()).register();
        ChatUtil.broadcast("User " + event.getPlayer().getName() + " has joined!", "reflexed.admin");
    }

    @EventInfo
    public void onQuit(PlayerQuitEvent event) {
        User.get(event.getPlayer()).unregister();
        ChatUtil.broadcast("User " + event.getPlayer().getName() + " has left!", "reflexed.admin");
    }

    private EventManager getRegisteredEventManager() {
        try {
            Class<?> mainClass = Class.forName("mc.reflexed.Reflexed");
            Method get = mainClass.getMethod("get");

            Object instance = get.invoke(null);
            Method getEventManager = mainClass.getMethod("getEventManager");

            return (EventManager) getEventManager.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Failed to get EventManager!");
    }
}
