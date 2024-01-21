package mc.reflexed.ac.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mc.reflexed.ac.ReflexedAC;
import mc.reflexed.ac.check.Check;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class User {

    @Getter
    public static final List<User> users = new ArrayList<>();

    private final List<Check> checks = new ArrayList<>();

    private final Player player;
    private int vl;

    public void register() {
        users.add(this);
        ReflexedAC.getInstance().getCheckManager().register(this);
    }

    public void unregister() {
        users.remove(this);
        checks.forEach(Check::unregister);
    }

    public void registerCheck(Check check) {
        checks.add(check);
        check.register(this);
    }

    public int getPing() {
        try {
            return player.getClass().getDeclaredField("ping").getInt(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static User get(Player player) {
        if(users.stream().anyMatch(user -> user.getPlayer().equals(player))) {
            return users.stream().filter(user -> user.getPlayer().equals(player)).findFirst().orElse(null);
        }

        return new User(player, 0);
    }
}
