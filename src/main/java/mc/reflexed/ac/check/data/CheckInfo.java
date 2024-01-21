package mc.reflexed.ac.check.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CheckInfo {
    String name();
    String description() default "No description provided.";
    CheckType type() default CheckType.NORMAL;
    int maxVl() default 10;
}
