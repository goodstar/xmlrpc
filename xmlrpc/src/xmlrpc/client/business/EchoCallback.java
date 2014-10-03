package xmlrpc.client.business;

import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.AsyncCallback;

public class EchoCallback implements AsyncCallback {
	public void handleResult(XmlRpcRequest pRequest, Object pResult)
	{
		System.out.println("Server returns: " + (String) pResult);
	}

	public void handleError(XmlRpcRequest pRequest, Throwable pError)
	{
		System.out.println("Error occurs: " + pError.getMessage());
	}
}