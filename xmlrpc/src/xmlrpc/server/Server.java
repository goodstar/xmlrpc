package xmlrpc.server;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import xmlrpc.server.business.GoodHandlerImpl;
import xmlrpc.server.business.HelloHandlerImpl;

public class Server {
	private static final int port = 8080;

	public static void main(String[] args) throws Exception
	{
		WebServer webServer = new WebServer(port);
		XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
		PropertyHandlerMapping phm = new PropertyHandlerMapping();

		phm.addHandler("common.HelloHandler",
				HelloHandlerImpl.class);

		phm.addHandler("common.GoodHandler",
				GoodHandlerImpl.class);

		xmlRpcServer.setHandlerMapping(phm);
		XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer
				.getConfig();

		serverConfig.setEnabledForExtensions(true);
		serverConfig.setContentLengthOptional(false);
		webServer.start();
	}
}