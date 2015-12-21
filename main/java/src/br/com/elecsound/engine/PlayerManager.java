package br.com.elecsound.engine;

import java.net.InetSocketAddress;

import br.com.elecsound.Configuration;
import br.com.elecsound.project.InstrumentItem;
import br.com.elecsound.project.Project;
import br.com.elecsound.project.TrackLine;

public class PlayerManager {
	
	private static Player player;
	private static PlayingStatusServer playingStatusServer;
	private static Project playingProject;
	
	private PlayerManager() {
	}
	
	public static void init(Player player) {
		
		PlayerManager.player = player;
		
		playingStatusServer = new PlayingStatusServer(new InetSocketAddress(Configuration.getHost(), Configuration.getPlayPort()));
		playingStatusServer.start();
	}

	
	/**
	 * Plays the project
	 * 
	 * @param project project
	 * @param at initial position in seconds
	 * @param playingStatus callback status
	 */
	public static void play(Project project, double at) {
		
		synchronized (player) {//TODO precisa disso?
			
			if(player.isRunning())
				return;
			
			playingProject = project;
			player.play(project, new PlayingStatus() {
				@Override
				public void update(double seconds) {
					if(playingStatusServer.conn != null) {
						playingStatusServer.conn.send(seconds + "");
					} 
				}
			});			
		}
	}
	
	public static void stop() {
		
		synchronized (player) {//TODO remover
			player.stop();
			
			if(playingProject != null) {
				for(TrackLine trk : playingProject.getTrackLines()) {
					trk.stop();
				}
			}
			
			playingProject = null;
			
			player.start();
		}
	}
	
	public static void playInstrumentItem(String id, int note) {
		player.playInstrument(id, note);
	}
	
	public static void stopInstrumentItem(String id) {
		player.stopInstrument(id);
	}
	
	public static void loadInstrumentItem(InstrumentItem instrument) {
		player.loadInstrumentItem(instrument);
	}
	
	public static void unloadInstrumentItem(String id) {
		player.unloadInstrumentItem(id);
	}	
	
}
