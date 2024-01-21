package mc.reflexed.ac.check;

import lombok.Getter;
import mc.reflexed.ac.ReflexedAC;
import mc.reflexed.ac.check.data.CheckInfo;
import mc.reflexed.ac.check.data.CheckType;
import mc.reflexed.ac.user.User;
import mc.reflexed.ac.util.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Getter
public class Check {

    protected User user = null;

    protected final String name, description, id;
    protected final CheckType type;

    private final int maxVL;
    private int vl;

    public Check() {
        CheckInfo checkInfo = this.getClass().getAnnotation(CheckInfo.class);

        if(checkInfo == null) {
            throw new RuntimeException("CheckInfo annotation not found!");
        }

        this.name = checkInfo.name();
        this.description = checkInfo.description();
        this.id = checkInfo.value();
        this.type = checkInfo.type();
        this.maxVL = checkInfo.maxVl();
    }

    protected void flag(String... data) {
        vl++;

        if(user == null) {
            throw new NullPointerException("Player is null!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("§d").append(name).append(" §7(").append(id).append(")\n§r\n");

        for(String s : data) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException("Data does not contain '='!");
            }

            String[] split = s.split("=");

            stringBuilder.append("§d").append(split[0]).append(":§7 ").append(split[1]);

            if(!s.equals(data[data.length - 1])) {
                stringBuilder.append("\n§r");
            }
        }

        stringBuilder.append("\n§r\n");
        stringBuilder.append("§dVL:§7 ").append(vl).append("/").append(maxVL).append("\n");
        stringBuilder.append("§dPing:§7 ").append(user.getPing()).append("ms");

        stringBuilder.append("\n§r\n");
        stringBuilder.append("§7Click to teleport to player!");

        TextComponent.Builder message = Component.text(String.format("%s§d%s §7failed §d%s §7(§d%s§7) §7[§d%s§7ms]", ChatUtil.getPrefix(), user.getPlayer().getName(), name, id, user.getPing()))
                .hoverEvent(Component.text(stringBuilder.toString()))
                .clickEvent(ClickEvent.runCommand("/tp " + user.getPlayer().getName()))
                .toBuilder();

        ChatUtil.broadcast(message.build(), true);
    }

    public final void register(User user) {
        this.user = user;
    }

    public void unregister() {
        ReflexedAC.getInstance().getEventManager().unregister(this);
    }
}
