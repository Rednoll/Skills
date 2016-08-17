package inwaiders.redn.skillsengine.bank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class anno {

	@Target(value=ElementType.TYPE)
	@Retention(value= RetentionPolicy.RUNTIME)
	public @interface isSkill {    
	
	}
}
