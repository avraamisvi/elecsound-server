package br.com.elecsound.engine;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class PlayingStatusServer extends WebSocketServer {
	
	WebSocket conn;
	
	public PlayingStatusServer(InetSocketAddress address) {
		super(address);
		
		System.out.println("Starting PlayingStatusServer on: " + address.getHostName() + " " + address.getPort());
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		// TODO Auto-generated method stub
		conn.send("OK2");
		System.out.println("entrou2");
		this.conn = conn;
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub
		
	}

}
