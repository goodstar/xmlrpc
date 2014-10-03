package xmlrpc.server.business;

import common.HelloHandler;

public class HelloHandlerImpl implements HelloHandler {
	public String sayHello(String str)
	{
		return "Hello, " + str + "!";
	}
}