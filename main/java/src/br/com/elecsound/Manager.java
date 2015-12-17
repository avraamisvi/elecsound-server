package br.com.elecsound;

import java.io.IOException;

import br.com.elecsound.engine.Player;
import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.engine.PlayingStatus;
import br.com.elecsound.library.LibraryManager;
import br.com.elecsound.messages.AddInstrumentMessage;
import br.com.elecsound.messages.AddPianoRollEntryMessage;
import br.com.elecsound.messages.AddTrackItemMessage;
import br.com.elecsound.messages.AddTrackLineMessage;
import br.com.elecsound.messages.CreateProjectMessage;
import br.com.elecsound.messages.CreateProjectResponse;
import br.com.elecsound.messages.GetInstrumentConfigurationMessage;
import br.com.elecsound.messages.MessageConstants;
import br.com.elecsound.messages.MessageResponse;
import br.com.elecsound.messages.PlayInstrumentMessage;
import br.com.elecsound.messages.PlayMessage;
import br.com.elecsound.messages.RemoveInstrumentMessage;
import br.com.elecsound.messages.RemovePianoRollEntryMessage;
import br.com.elecsound.messages.RemoveTrackItemMessage;
import br.com.elecsound.messages.RemoveTrackLineMessage;
import br.com.elecsound.messages.SaveProjectMessage;
import br.com.elecsound.messages.SetInstrumentConfigurationMessage;
import br.com.elecsound.messages.SetInstrumentModeMessage;
import br.com.elecsound.messages.SetLoopIndexMessage;
import br.com.elecsound.messages.SetPianoRollEntryMessage;
import br.com.elecsound.messages.SetTrackItemInfo;
import br.com.elecsound.messages.StopInstrumentMessage;
import br.com.elecsound.project.ProjectManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

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
		
		System.out.println(msg);
		
		MessageResponse response = null;
		
		switch (msg.get("name").getAsString()) {
		
		case MessageConstants.CREATE_PROJECT:
			this.createProject(gson.fromJson(msg, CreateProjectMessage.class));
			break;

		case MessageConstants.PLAY_INSTRUMENT:
			this.playInstrument(gson.fromJson(msg, PlayInstrumentMessage.class));
			break;

		case MessageConstants.STOP_INSTRUMENT:
			this.stopInstrument(gson.fromJson(msg, StopInstrumentMessage.class));
			break;
			
		case MessageConstants.LIST_INSTRUMENTS:
			response = LibraryManager.listInstruments();
			break;
		
		case MessageConstants.GET_INSTRUMENT_CONFIG:
			response = ProjectManager.getInstrumentConfig(gson.fromJson(msg, GetInstrumentConfigurationMessage.class));
			break;
		
		case MessageConstants.ADD_INSTRUMENT:
			this.addInstrument(gson.fromJson(msg, AddInstrumentMessage.class));
			break;
		
		case MessageConstants.REMOVE_INSTRUMENT:
			ProjectManager.removeInstrument(gson.fromJson(msg, RemoveInstrumentMessage.class));
			break;

		case MessageConstants.SET_INSTRUMENT_CONFIGURATION:
			ProjectManager.setInstrumentConfiguration(gson.fromJson(msg, SetInstrumentConfigurationMessage.class));
			break;
			
		case MessageConstants.SET_INSTRUMENT_MODE:
			ProjectManager.setInstrumentMode(gson.fromJson(msg, SetInstrumentModeMessage.class));
			break;

		case MessageConstants.SAVE_PROJECT:
			try {
				ProjectManager.saveProject(gson.fromJson(msg, SaveProjectMessage.class));
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case MessageConstants.ADD_PIANOROLL_ENTRY:
			ProjectManager.addPianoRollEntry(gson.fromJson(msg, AddPianoRollEntryMessage.class));
			break;
			
		case MessageConstants.REMOVE_PIANOROLL_ENTRY:
			ProjectManager.removePianoRollEntry(gson.fromJson(msg, RemovePianoRollEntryMessage.class));
			break;
		
		case MessageConstants.SET_PIANOROLL_ENTRY:
			ProjectManager.setPianoRollEntry(gson.fromJson(msg, SetPianoRollEntryMessage.class));
			break;
			
		case MessageConstants.ADD_TRACK_LINE:
			ProjectManager.addTrackLine(gson.fromJson(msg, AddTrackLineMessage.class));
			break;
		
		case MessageConstants.REMOVE_TRACK_LINE:
			ProjectManager.removeTrackLine(gson.fromJson(msg, RemoveTrackLineMessage.class));
			break;

		case MessageConstants.ADD_TRACK_ITEM:
			ProjectManager.addTrackItem(gson.fromJson(msg, AddTrackItemMessage.class));
			break;

		case MessageConstants.REMOVE_TRACK_ITEM:
			ProjectManager.removeTrackItem(gson.fromJson(msg, RemoveTrackItemMessage.class));
			break;
			
		case MessageConstants.SET_TRACKITEM_INFO:
			ProjectManager.setTrackItemInfo(gson.fromJson(msg, SetTrackItemInfo.class));
			break;			

		case MessageConstants.SET_LOOP_INDEX:
			ProjectManager.setLoopIndex(gson.fromJson(msg, SetLoopIndexMessage.class));
			break;

		case MessageConstants.PLAY:
			this.play(gson.fromJson(msg, PlayMessage.class));
			break;

		case MessageConstants.STOP:
			PlayerManager.stop();
			break;
			
		case MessageConstants.SHUTDOWN:
			PlayerManager.stop();
			System.exit(0);
			break;			
			
		default:
			break;
		}
		
		if(response != null) {
			server.send(gson.toJsonTree(response));
		}
	}
	
	private void play(PlayMessage msg) {
		
		PlayerManager.play(ProjectManager.getProject(), msg.getStart());
	}

	private void stopInstrument(StopInstrumentMessage msg) {
		PlayerManager.stopInstrumentItem(msg.getInstrumentItemId());
	}

	private void playInstrument(PlayInstrumentMessage msg) {
		PlayerManager.playInstrumentItem(msg.getInstrumentItemId(), msg.getNote());
	}

	private void addInstrument(AddInstrumentMessage msg) {
		ProjectManager.addInstrument(msg);
	}

	public CreateProjectResponse createProject(CreateProjectMessage msg) {
		return ProjectManager.createProject(msg);
	}	
	
}
