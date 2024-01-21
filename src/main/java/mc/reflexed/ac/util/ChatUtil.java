package mc.reflexed.ac.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

@UtilityClass
public class ChatUtil {

    public void broadcast(String message, String permission) {
        String prefix = "§8[§cReflexed§8] ";

        Bukkit.broadcast(Component.text(prefix + message), permission);
    }

}
