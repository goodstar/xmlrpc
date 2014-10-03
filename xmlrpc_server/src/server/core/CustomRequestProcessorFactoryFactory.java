package server.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * CustomRequestProcessorFactoryFactory
 * 
 * @author：lixing3@asiainfo-linkage.com
 * @2014-10-3 下午12:16:21
 * @version 1.0
 */
public class CustomRequestProcessorFactoryFactory implements
		RequestProcessorFactoryFactory, InitializingBean,
		ApplicationContextAware {

	private static Map<Class<?>, RequestProcessorFactory> cache = new HashMap<Class<?>, RequestProcessorFactory>();

	private static ApplicationContext ctx;

	/**
	 * RequestProcessorFactoryFactory初始化及XML RPC的handler初始化
	 * 
	 * @author：lixing3@asiainfo-linkage.com
	 * @2014-10-3 上午02:17:52
	 */
	public void init() throws XmlRpcException
	{
		PropertyHandlerMapping mapping = ctx
				.getBean(PropertyHandlerMapping.class);

		// 1、初始化RequestProcessorFactoryFactory
		mapping.setRequestProcessorFactoryFactory(this);

		// 2、在此处需要将cache初始化，并且把对应的RequestProcessorFactory也初始化
		Map<String, Object> map = ctx
				.getBeansWithAnnotation(RpcHandlerInSpring.class);

		Set<Entry<String, Object>> entry = map.entrySet();
		for (final Entry<String, Object> e : entry) {

			RequestProcessorFactory rf = new RequestProcessorFactory() {

				@Override
				public Object getRequestProcessor(XmlRpcRequest req)
						throws XmlRpcException
				{
					return ctx.getBean(e.getKey());
				}
			};

			cache.put(e.getValue().getClass(), rf);

			// 3、添加xmlRpc handler处理,由于客户端使用接口创建代理类
			mapping.addHandler(e.getKey(), e.getValue().getClass());
		}

		// 4、加入鉴权控制
		mapping.setAuthenticationHandler(ctx
				.getBean(AuthenticationHandler.class));

	}

	@SuppressWarnings("unchecked")
	@Override
	public RequestProcessorFactory getRequestProcessorFactory(Class claz)
			throws XmlRpcException
	{
		return cache.get(claz);
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		// 初始化配置
		init();
	}

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException
	{
		this.ctx = ctx;
	}

	public static ApplicationContext getApplicationContext()
	{
		return ctx;
	}

}
