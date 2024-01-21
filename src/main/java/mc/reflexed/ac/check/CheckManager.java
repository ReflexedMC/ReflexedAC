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
            if(!clazz.isAssignableFrom(Check.class)) {
                throw new RuntimeException("Class " + clazz.getName() + " is not a check!");
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
