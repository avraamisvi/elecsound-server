package br.com.elecsound;

import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.library.LibraryManager;
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
	LibraryManager libraryManager;
	
	public Manager(MessagesServer server) {
		this.projectManager = new ProjectManager();
		this.playerManager = new PlayerManager();
		this.libraryManager = new LibraryManager();
		this.server = server;
	}
	
	public void parseMessage(JsonObject json) {
		
		switch (json.get("name").getAsString()) {
		
		case MessageConstants.CREATE_PROJECT:
			this.createProject(json);
			break;

		case MessageConstants.LIST_INSTRUMENTS:
			this.listInstruments();
			break;
		
		case MessageConstants.GET_INSTRUMENT:
			this.listInstruments();
			break;
			
		default:
			break;
		}
	}
	
	private void listInstruments() {
		server.send(libraryManager.listInstruments());
	}

	public void createProject(JsonObject json) {
		project = projectManager.createProjectSettings(json);
		playerManager.setProject(project);
	}
}
