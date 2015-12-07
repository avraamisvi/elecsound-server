package br.com.elecsound;

import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.library.LibraryManager;
import br.com.elecsound.messages.Message;
import br.com.elecsound.messages.MessageConstants;
import br.com.elecsound.project.Project;
import br.com.elecsound.project.ProjectManager;

import com.google.gson.JsonObject;

/**
 * Manage all the received messages.
 * 
 * @author abraao
 *
 */
public class Manager {
	
	MessagesServer server;
	
	Project project;
	
	ProjectManager projectManager;
	PlayerManager playerManager;
	
	public Manager(MessagesServer server) {
		
		LibraryManager.load();
		
		this.projectManager = new ProjectManager();
		this.playerManager = new PlayerManager();
		this.server = server;
	}
	
	public void parseMessage(JsonObject msg) {
		
		switch (msg.get("name").getAsString()) {
		
		case MessageConstants.CREATE_PROJECT:
			this.createProject(gson.fromJson(msg, JsonObject.class));
			break;

		case MessageConstants.LIST_INSTRUMENTS:
			this.listInstruments();
			break;
		
		case MessageConstants.GET_INSTRUMENT:
			this.getInstrument(msg.getData());
			break;
		
		case MessageConstants.ADD_INSTRUMENT:
			this.addInstrument(msg.getData());
			break;
		
		default:
			break;
		}
	}
	
	private void addInstrument(JsonObject data) {
		projectManager.addInstrument(data);
	}

	private void listInstruments() {
		server.send(LibraryManager.listInstruments());
	}

	public void createProject(JsonObject json) {
		project = projectManager.createProjectSettings(json);
		playerManager.setProject(project);
	}
	
	public void getInstrument(JsonObject json) {
		//TODO
	}	
	
	
}
