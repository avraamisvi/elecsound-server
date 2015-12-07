package br.com.elecsound.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a project
 * 
 * @author abraao
 *
 */
public class Project {
	
	String name;
	String fileName;
	List<InstrumentItem> instruments;
	List<TrackLine> trackLines;
	
	HashMap<String, InstrumentItem> instrumentsHash;
	HashMap<String, TrackLine> trackLinesHash;
	
	public Project(String name) {
		this.name = name;
		
		this.instruments = new ArrayList<>();
		this.trackLines = new ArrayList<>();
		this.instrumentsHash = new HashMap<>();
		this.trackLinesHash = new HashMap<>();
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
		return instruments;
	}

	public List<TrackLine> getTrackLines() {
		return trackLines;
	}
	
	public HashMap<String, TrackLine> getTrackLinesHash() {
		return trackLinesHash;
	}
	
	public HashMap<String, InstrumentItem> getInstrumentsHash() {
		return instrumentsHash;
	}
}
