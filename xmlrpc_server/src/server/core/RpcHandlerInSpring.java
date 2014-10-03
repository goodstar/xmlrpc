package server.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * 标记性注解，方便从spring中查找 RpcHandlerInSpring 
 *  
 * @author：lixing3@asiainfo-linkage.com
 * @2014-10-3 下午12:26:10 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcHandlerInSpring {

}
