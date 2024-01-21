package mc.reflexed.ac.check;

import mc.reflexed.ac.check.checks.TestCheck;
import mc.reflexed.ac.user.User;

import java.lang.reflect.InvocationTargetException;

public class CheckManager {

    private final Class<?>[] classes = new Class<?>[] {
            TestCheck.class
    };

    public void register(User user) {
        for(Class<?> clazz : classes) {
            if(!Check.class.isAssignableFrom(clazz)) {
                throw new RuntimeException("Class " + clazz.getName() + " does not extend Check!");
            }

            try {
                Check check = (Check) clazz.getDeclaredConstructors()[0].newInstance();
                user.registerCheck(check);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
