package br.com.elecsound.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Represents a project
 * 
 * @author abraao
 *
 */
public class Project {
	
	private String name;
	private String fileName;
	
	private List<InstrumentItem> instrumentsItems;
	private List<TrackLine> trackLines;
	
	private HashMap<String, InstrumentItem> instrumentsItemsHash;
	private HashMap<String, TrackLine> trackLinesHash;
	
	private ProjectSettings settings;
	
	/**
	 * For new projects use this.
	 * 
	 * @param name
	 */
	public Project(String name) {
		this.name = name;
		
		this.instrumentsItems = new ArrayList<>();
		this.trackLines = new ArrayList<>();
		this.instrumentsItemsHash = new HashMap<>();
		this.trackLinesHash = new HashMap<>();
		
		this.settings = new ProjectSettings();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<InstrumentItem> getInstruments() {
		return instrumentsItems;
	}

	public List<TrackLine> getTrackLines() {
		return trackLines;
	}
	
	public HashMap<String, TrackLine> getTrackLinesHash() {
		return trackLinesHash;
	}
	
	public HashMap<String, InstrumentItem> getInstrumentsHash() {
		return instrumentsItemsHash;
	}

	public void addTrackLine(TrackLine line) {
		trackLines.add(line);
		trackLinesHash.put(line.getId(), line);
	}
	
	public void removeTrackLine(String id) {
		TrackLine track = trackLinesHash.remove(id);
		trackLines.remove(track);
		track.disconnect();
	}
	
	public void addInstrumentItem(InstrumentItem instrumentItem) {
		instrumentsItems.add(instrumentItem);
		instrumentsItemsHash.put(instrumentItem.getId(), instrumentItem);
	}
	
	public void removeInstrumentItem(String id) {
		
		InstrumentItem instItm = instrumentsItemsHash.remove(id);
		instrumentsItems.remove(instItm);
		instItm.disconnect();
	}		
	
	public InstrumentItem getInstrumentItem(String id) {
		return instrumentsItemsHash.get(id);
	}
	
	public TrackLine getTrackLine(String id) {
		return trackLinesHash.get(id);
	}
	
	public void generate() {
		for (TrackLine trackline : trackLines) {
			trackline.play();
		}
	}
	
	public ProjectSettings getSettings() {
		return settings;
	}
	
	public void setSettings(ProjectSettings settings) {
		this.settings = settings;
	}
	
	public void close() {
		
		for (InstrumentItem instrumentItem : instrumentsItems) {
			instrumentItem.disconnect();
		}
		
		for (TrackLine trl : trackLines) {
			trl.disconnect();
		}	
	}
}
