package br.com.elecsound.project;

import com.google.gson.JsonObject;

import br.com.elecsound.engine.Player;
import br.com.elecsound.library.LibraryManager;
import br.com.elecsound.messages.AddInstrumentMessage;
import br.com.elecsound.messages.CreateProjectMessage;

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
	
	public ProjectManager(Player player) {
		this.player = player;
	}	
	
	public Project createProject(CreateProjectMessage msg) {
		return project = new Project(msg.getProjectName());
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
	
	public void addInstrument(AddInstrumentMessage msg) {
		
		Instrument instrument = LibraryManager.createInstrument(msg.getInstrumentItemId());
		InstrumentItem item = new InstrumentItem(msg.getInstrumentId(), instrument, Color.GRAY, msg.getPosition());
		project.addInstrumentItem(item);
		
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
