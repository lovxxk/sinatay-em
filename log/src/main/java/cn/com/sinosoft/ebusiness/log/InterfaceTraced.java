package cn.com.sinosoft.ebusiness.log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识一个方法将通过AOP进行Traced.
 * 
 * @see InterfaceTraced
 * 
 *  
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InterfaceTraced {
}
