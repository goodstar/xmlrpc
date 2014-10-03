package server.business;

import common.HelloHandler;

import server.core.RpcHandlerInSpring;

@RpcHandlerInSpring
public class HelloHandlerImpl implements HelloHandler {
	public String sayHello(String str)
	{
		return "Hello, " + str + "!";
	}
}