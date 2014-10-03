package server.servlet;

import javax.servlet.ServletException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import server.core.CustomRequestProcessorFactoryFactory;

/**
 *	XmlRpcServer已经初始化了TypeConverterFactoryImpl
 *  
 * @author：lixing3@asiainfo-linkage.com
 * @2014-10-3 下午01:21:43 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomXmlRpcServlet extends XmlRpcServlet {

	@Override
	public void init() throws ServletException
	{
	}

	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
			throws XmlRpcException
	{
		return CustomRequestProcessorFactoryFactory.getApplicationContext()
				.getBean(PropertyHandlerMapping.class);
	}

}
