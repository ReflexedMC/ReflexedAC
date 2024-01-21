package mc.reflexed.ac.check;

import lombok.Getter;
import mc.reflexed.ac.ReflexedAC;
import mc.reflexed.ac.user.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckManager {

    @Getter
    private static List<Class<?>> classes = new ArrayList<>();

    public void register(User user) {
        for(Class<?> clazz : classes) {
            try {
                Check check = (Check) clazz.getDeclaredConstructors()[0].newInstance();
                user.registerCheck(check);

                ReflexedAC.getInstance().getEventManager().register(check, user.getPlayer());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addChecks(Class<?>... checks) {
        classes.addAll(Arrays.asList(checks));
    }

}
