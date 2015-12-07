package br.com.elecsound;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Server {	
	
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		final MessagesServer server = new MessagesServer(new InetSocketAddress(Configuration.getHost(), Configuration.getPort()));
		server.start();
	}	
}
