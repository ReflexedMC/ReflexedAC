package mc.reflexed.ac.check;

import lombok.Getter;
import mc.reflexed.ac.ReflexedAC;
import mc.reflexed.ac.check.data.CheckInfo;
import mc.reflexed.ac.check.data.CheckType;
import mc.reflexed.ac.user.User;
import mc.reflexed.ac.util.ChatUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Getter
public class Check {

    protected Player player = null;

    protected final String name;
    protected final String description;
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
        this.type = checkInfo.type();
        this.maxVL = checkInfo.maxVl();
    }

    protected void flag() {
        vl++;

        if(player == null) {
            throw new NullPointerException("Player is null!");
        }

        ChatUtil.broadcast(String.format("§c%s §7failed §c%s §7[§c%s§7]", player.getName(), name, vl), "reflexed.staff");
    }

    public final void register(User user) {
        ReflexedAC.INSTANCE.getEventManager().register(this, this.player = user.getPlayer());
    }

}
