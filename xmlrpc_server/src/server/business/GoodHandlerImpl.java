package server.business;

import common.GoodHandler;

import server.core.RpcHandlerInSpring;

@RpcHandlerInSpring
public class GoodHandlerImpl implements GoodHandler {

	public int cal(int a, int b)
	{
		return a + b;
	}

}
