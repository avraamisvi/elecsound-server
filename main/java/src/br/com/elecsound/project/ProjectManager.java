package br.com.elecsound.project;

import com.google.gson.JsonObject;

import br.com.elecsound.engine.Player;

/**
 * Receive the operations from client and interact with the project.
 * 
 * @author abraao
 *
 */
public class ProjectManager {
	
	private Project project;
	private Player player;
	
	//PROJECT
	
	public Project createProjectSettings(JsonObject json) {
		return project = new Project(json.get("name").getAsString());
	}
	
	public void setProjectSettings(JsonObject json) {
		
	}	
	
	public void saveProject(JsonObject json) {
		
	}

	//PLAYLIST
	
	public void addTrackLine(JsonObject json) {
		
	}
	
	public void addTrackItem(JsonObject json) {
		
	}	

	public void removeTrackLine(JsonObject json) {
		
	}
	
	public void removeTrackItem(JsonObject json) {
		
	}	
	
	//INSTRUMENT
	
	public void addInstrument(JsonObject json) {
		
	}	
	
	public void removeInstrument(JsonObject json) {
		
	}
	
	public void changeInstrumentColor(JsonObject json) {
		
	}

	//PIANOROLL
	public void addPianoRollEntry(JsonObject json) {
		
	}	
	
	public void removePianoRollEntry(JsonObject json) {
		
	}
	
}
