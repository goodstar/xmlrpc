package server.core;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler;

/**
 * 
 * 鉴权CustomAuthenticationHandler 
 *  
 * @author：lixing3@asiainfo-linkage.com
 * @2014-10-3 下午12:11:37 
 * @version 1.0
 */
public class CustomAuthenticationHandler implements AuthenticationHandler {

	@Override
	public boolean isAuthorized(XmlRpcRequest req)
			throws XmlRpcException
	{
		StringBuilder sb = new StringBuilder();
		int len = req.getParameterCount();
		for (int i = 0; i < len; i++) {
			sb.append(req.getParameter(i)).append(',');
		}
		System.out
				.println("============================================");

		System.out.println("methodName [" + req.getMethodName() + "]");
		System.out.println("parameter ["
				+ sb.deleteCharAt(sb.length() - 1) + "]");
		System.out.println("timeZone [" + req.getConfig().getTimeZone()
				+ "]");

		System.out
				.println("============================================");

		System.out.println();

		if ("common.GoodHandler.cal".equalsIgnoreCase(req
				.getMethodName()))
			return false;
		return true;
	}

}
