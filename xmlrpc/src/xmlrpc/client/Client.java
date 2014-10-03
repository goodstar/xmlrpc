package xmlrpc.client;

import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;

import common.GoodHandler;
import common.HelloHandler;

import xmlrpc.client.business.EchoCallback;

public class Client {
	public static void main(String[] args) throws Exception
	{
		// create configuration
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://localhost:8080/xmlrpc_server/XmlRpcServer"));
		config.setEnabledForExtensions(true);
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);

		XmlRpcClient client = new XmlRpcClient();
		// set configuration
		client.setConfig(config);

		// make a call using dynamic proxy
		ClientFactory factory = new ClientFactory(client);

		HelloHandler handler = (HelloHandler) factory
				.newInstance(HelloHandler.class);
		String str = handler.sayHello("Bill David");
		System.out.println(str);

		// make an asynchronous call
		List<String> params = new Vector<String>(); // for JDK before 1.5, use
		// 'List params = new
		// Vector();'
		params.add("Tom");
		client.executeAsync("common.HelloHandler.sayHello",
				params, new EchoCallback());
		System.out.println("sending...................");

		GoodHandler gh = (GoodHandler) factory.newInstance(GoodHandler.class);
		try {
			System.out.println(gh.cal(10, 30));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}