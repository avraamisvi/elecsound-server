package br.com.elecsound.project;

import java.util.HashMap;

public class TrackLine {
	
	private String id;
	private String name;
	
	HashMap<String, TrackItem> tracks;

	public TrackLine(String id, String name) {
		this.id = id;
		this.name = name;
		this.tracks = new HashMap<>();
	}
	
	public void add(TrackItem item) {
		tracks.put(item.getId(), item);
	}
	
	public void remove(String id) {
		tracks.remove(id);//TODO operacao de remover o track inclui remover o voice do player
	}	
	
	public TrackItem getTrack(String id) {
		return tracks.get(id);
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void play() {
		for (TrackItem track : tracks.values()) {
			track.play();
		}
	}

	public void disconnect() {
		for (TrackItem track : tracks.values()) {
			track.disconnect();
		}
	}
}
