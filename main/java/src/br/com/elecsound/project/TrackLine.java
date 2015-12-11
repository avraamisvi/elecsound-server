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
		tracks.remove(id).disconnect();
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
	
	public HashMap<String, TrackItem> getTracks() {
		return tracks;
	}
	
	public void play() {
		for (TrackItem track : tracks.values()) {
			track.play();
		}
	}
	
	public void stop() {
		for (TrackItem track : tracks.values()) {
			track.stop();
		}
	}	

	public void disconnect() {
		for (TrackItem track : tracks.values()) {
			track.disconnect();
		}
	}
}
