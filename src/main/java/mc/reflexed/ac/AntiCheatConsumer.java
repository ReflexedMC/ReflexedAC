package mc.reflexed.ac;

import org.bukkit.entity.Player;

public interface AntiCheatConsumer {
    boolean accept(Player player); // if the player has the permission to view anti-cheat alerts
}
