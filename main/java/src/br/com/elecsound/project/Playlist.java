package br.com.elecsound.project;

import java.util.HashMap;

public class Playlist {
	
	HashMap<String, TrackLine> trackLines;
	
	public void addItem(TrackLine track) {
		trackLines.put(track.getId(), track);
	}
	
	public void removeItem(TrackLine track) {
		trackLines.put(track.getId(), track);
	}
	
	public TrackLine getItem(String id) {
		return trackLines.get(id);
	}
}
