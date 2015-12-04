package br.com.elecsound;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Server extends WebSocketServer{	
	
	Manager manager;
	Gson gson;
	WebSocket conn;
	
	public Server(InetSocketAddress address) throws UnknownHostException {
		super();
		
		System.out.println("Starting server on: " + address.getHostName() + " " + address.getPort());
		
		gson = new GsonBuilder().create();
		this.manager = new Manager(this);
	}
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		// TODO Auto-generated method stub
		
		JsonElement obj = gson.fromJson(message, JsonElement.class);
		manager.parseMessage(obj.getAsJsonObject());
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub
		
	}
	
	public void send(JsonElement obj) {
		this.conn.send(gson.toJson(obj));
	}
	
	public static void main(String[] args) throws UnknownHostException {
		new Server(new InetSocketAddress(Configuration.getHost(), Configuration.getPort())).start();
	}	
}
