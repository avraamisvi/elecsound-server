package br.com.elecsound.engine;

import java.net.InetSocketAddress;

import br.com.elecsound.Configuration;
import br.com.elecsound.project.Project;

import com.google.gson.JsonObject;

public class PlayerManager {
	
	Project project;
	Player player;
	PlayingStatusServer playingStatusServer;
	
	public PlayerManager() {
		
		playingStatusServer = new PlayingStatusServer(new InetSocketAddress(Configuration.getHost(), Configuration.getPlayPort()));
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				playingStatusServer.start();
			}
		}).start();		

	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	/**
	 * Plays the project
	 */
	public void play(JsonObject json) {
		player.play(project, new PlayingStatus() {
			
			@Override
			public void update(int seconds) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void stop(JsonObject json) {
		player.stop();
	}
	
	public void playInstrument(JsonObject json) {
		player.playinstrument(null, 0);
	}
	
	public void loadInstrument(JsonObject json) {
		player.loadInstrument(null);
	}
	
	public void unloadInstrument(JsonObject json) {
		player.unloadInstrument(null);
	}	
	
}
