package mc.reflexed.ac.check.checks;

import mc.reflexed.ac.check.Check;
import mc.reflexed.ac.check.data.CheckInfo;
import mc.reflexed.ac.check.data.CheckType;
import mc.reflexed.ac.util.ChatUtil;
import mc.reflexed.event.data.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@CheckInfo(name = "test", description = "Test check", type = CheckType.EXPERIMENTAL)
public class TestCheck extends Check {

    @EventInfo
    public void onPlayerMove(Player player, PlayerMoveEvent event) {
        ChatUtil.broadcast("(" + player.getName() + " " + this.player.getName() + ") " + this.name, "reflexed.staff"); // they should be the same
    }
}
