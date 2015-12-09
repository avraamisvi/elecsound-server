package br.com.elecsound.engine;

import java.net.InetSocketAddress;

import br.com.elecsound.Configuration;
import br.com.elecsound.project.InstrumentItem;
import br.com.elecsound.project.Project;

public class PlayerManager {
	
	private static Player player;
	private static PlayingStatusServer playingStatusServer;
	
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
	public static void play(Project project, double at, PlayingStatus playingStatus) {
		player.play(project, playingStatus);
	}
	
	public static void stop() {
		player.stop();
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
