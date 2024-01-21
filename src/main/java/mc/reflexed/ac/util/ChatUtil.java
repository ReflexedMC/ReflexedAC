package mc.reflexed.ac.util;

import lombok.experimental.UtilityClass;
import mc.reflexed.ac.AntiCheatConsumer;
import mc.reflexed.ac.ReflexedAC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class ChatUtil {

    public void broadcast(String message) {
        broadcast(message, false);
    }

    public void broadcast(String message, boolean permission) {
        broadcast(Component.text(message), permission);
    }

    public void broadcast(Component component, boolean permission) {
        String message = PlainTextComponentSerializer.plainText().serialize(component);

        String prefix = "§dReflexedAC §7» ";

        AntiCheatConsumer consumer = ReflexedAC.getInstance().getAntiCheatConsumer();

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(permission && !consumer.accept(player)) {
                continue;
            }

            player.sendMessage(Component.text(prefix + message));
        }
    }

}
