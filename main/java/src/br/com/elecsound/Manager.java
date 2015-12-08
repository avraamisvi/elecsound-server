package br.com.elecsound;

import br.com.elecsound.engine.Player;
import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.library.LibraryManager;
import br.com.elecsound.messages.AddInstrumentMessage;
import br.com.elecsound.messages.CreateProjectMessage;
import br.com.elecsound.messages.GetInstrumentConfigurationMessage;
import br.com.elecsound.messages.MessageConstants;
import br.com.elecsound.project.Project;
import br.com.elecsound.project.ProjectManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Manage all the received messages.
 * 
 * @author abraao
 *
 */
public class Manager {
	
	MessagesServer server;
	Gson gson;
	
	public Manager(MessagesServer server) {
		
		Player player = new Player();
		
		LibraryManager.load();		
		PlayerManager.init(player);
		
		this.server = server;
		
		gson = new Gson();
	}
	
	public void parseMessage(JsonObject msg) {
		
		switch (msg.get("name").getAsString()) {
		
		case MessageConstants.CREATE_PROJECT:
			this.createProject(gson.fromJson(msg, CreateProjectMessage.class));
			break;

		case MessageConstants.LIST_INSTRUMENTS:
			this.listInstruments();
			break;
		
		case MessageConstants.GET_INSTRUMENT_CONFIG:
			this.getInstrumentConfig(gson.fromJson(msg, GetInstrumentConfigurationMessage.class));
			break;
		
		case MessageConstants.ADD_INSTRUMENT:
			this.addInstrument(gson.fromJson(msg, AddInstrumentMessage.class));
			break;
		
		default:
			break;
		}
	}
	
	private void addInstrument(AddInstrumentMessage msg) {
		ProjectManager.addInstrument(msg);
	}

	private void listInstruments() {
		server.send(LibraryManager.listInstruments());
	}

	public void createProject(CreateProjectMessage msg) {
		ProjectManager.createProject(msg);
	}
	
	public void getInstrumentConfig(GetInstrumentConfigurationMessage msg) {
		server.send(gson.toJsonTree(ProjectManager.getInstrumentConfig(msg)));
	}	
	
	
}
