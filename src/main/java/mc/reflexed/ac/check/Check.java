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

    protected void flag() {
        vl++;

        if(user == null) {
            throw new NullPointerException("Player is null!");
        }

        ChatUtil.broadcast(String.format("§d%s §7failed §d%s §7(§d%s§7) §7[%sms]", user.getPlayer().getName(), name, id, user.getPing()), true);
    }

    public final void register(User user) {
        this.user = user;
    }

    public void unregister() {
        ReflexedAC.getInstance().getEventManager().unregister(this);
    }
}
