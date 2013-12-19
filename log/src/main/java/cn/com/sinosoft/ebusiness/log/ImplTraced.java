package cn.com.sinosoft.ebusiness.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识一个实现类的方法将通过AOP进行ImplTraced.
 * 
 * @see TraceAspect
 * 
 *  
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ImplTraced {

	Environment configEnv() default Environment.PRODUCT;
}
