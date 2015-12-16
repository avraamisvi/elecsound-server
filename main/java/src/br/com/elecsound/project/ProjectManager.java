package br.com.elecsound.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.elecsound.engine.Player;
import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.library.LibraryManager;
import br.com.elecsound.messages.AddInstrumentMessage;
import br.com.elecsound.messages.AddPianoRollEntryMessage;
import br.com.elecsound.messages.AddTrackItemMessage;
import br.com.elecsound.messages.AddTrackLineMessage;
import br.com.elecsound.messages.CreateProjectMessage;
import br.com.elecsound.messages.CreateProjectResponse;
import br.com.elecsound.messages.GetInstrumentConfigurationMessage;
import br.com.elecsound.messages.GetInstrumentConfigurationResponse;
import br.com.elecsound.messages.OpenProjectMessage;
import br.com.elecsound.messages.OpenProjectResponse;
import br.com.elecsound.messages.RemoveInstrumentMessage;
import br.com.elecsound.messages.RemovePianoRollEntryMessage;
import br.com.elecsound.messages.RemoveTrackItemMessage;
import br.com.elecsound.messages.RemoveTrackLineMessage;
import br.com.elecsound.messages.SaveProjectMessage;
import br.com.elecsound.messages.SetInstrumentConfigurationMessage;
import br.com.elecsound.messages.SetInstrumentModeMessage;
import br.com.elecsound.messages.SetLoopIndexMessage;
import br.com.elecsound.messages.SetPianoRollEntryMessage;
import br.com.elecsound.messages.SetProjectSettingsMessage;
import br.com.elecsound.messages.SetTrackItemInfo;

/**
 * Receive the operations from client and interact with the project.
 * 
 * @author abraao
 *
 */
public class ProjectManager {
	
	private static Project project;
	
	//PROJECT
	
	private ProjectManager() {
	}
	
	public static Project getProject() {
		return project;
	}
	
	public static OpenProjectResponse openProject(OpenProjectMessage msg) throws FileNotFoundException {
		
		if(project != null) {
			project.close();
		}
		
		project = new Project("");
		
		OpenProjectResponse resp = new OpenProjectResponse(ProjectFileParser.loadProject(project, new File(msg.getFileName())));
		
		return resp;
	}
	
	public static CreateProjectResponse createProject(CreateProjectMessage msg) {
		try {
			if(project != null) {
				project.close();
			}
		}catch(RuntimeException ex) {
			ex.printStackTrace();
		}catch(Throwable ex) {
			ex.printStackTrace();
		}
		
		CreateProjectResponse resp = new CreateProjectResponse();
		
		resp.status = "OK";
		project = new Project(msg.getProjectName());
		
		return resp;
	}

	public static void setProjectSettings(SetProjectSettingsMessage msg) {
		project.setSettings(msg.getSettings());
	}	
	
	public static void saveProject(SaveProjectMessage msg) throws IOException {
			ProjectFileParser.saveToFile(project, msg.getFilePath());
	}
	
	//PLAYLIST
	
	public static void addTrackLine(AddTrackLineMessage msg) {
		
		TrackLine line = new TrackLine(msg.getTrackLineId(), msg.getTrackLineName());
		project.addTrackLine(line);
	}
	
	public static void addTrackItem(AddTrackItemMessage msg) {
		
		TrackItem trk = new TrackItem(msg.getTrackItemId(), project.getInstrumentItem(msg.getInstrumentItemId()));
		
		trk.setStart(msg.getStart());
		trk.setEnd(msg.getEnd());
		
		project.getTrackLine(msg.getTrackLineId()).add(trk);
	}	

	public static void removeTrackLine(RemoveTrackLineMessage msg) {
		project.removeTrackLine(msg.getTrackLineId());
	}
	
	public static void removeTrackItem(RemoveTrackItemMessage msg) {
		project.getTrackLine(msg.getTrackLineId()).remove(msg.getTrackItemId());
	}	
	
	//INSTRUMENT
	
	public static void addInstrument(AddInstrumentMessage msg) {
		
		Instrument instrument = LibraryManager.createInstrument(msg.getInstrumentItemId());
		InstrumentItem item = new InstrumentItem(msg.getInstrumentItemId(), instrument, msg.getPosition());
		
//		item.connect(player);
		PlayerManager.loadInstrumentItem(item);//Connect to player
		project.addInstrumentItem(item);
		
	}	
	
	public static void removeInstrument(RemoveInstrumentMessage msg) {
		project.removeInstrumentItem(msg.getInstrumentItemId());
	}
	
	public static void setInstrumentConfiguration(SetInstrumentConfigurationMessage msg) {
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		itm.getInstrument().setConfiguration(msg.getConfiguration());
	}
	
	public static void setLoopIndex(SetLoopIndexMessage msg) {
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		itm.getInstrument().setLoop(msg.getIndex(), msg.getState());
	}
	
	public static void setInstrumentMode(SetInstrumentModeMessage msg) {
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		itm.getInstrument().setPianoRollMode(msg.isPianoRoll());
	}	

	//PIANOROLL
	public static void addPianoRollEntry(AddPianoRollEntryMessage msg) {
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		itm.getInstrument().addPianoRollEntry(msg.getEntryId(), msg.getNote(), msg.getWhen(), msg.getDuration());
	}	
	
	public static void removePianoRollEntry(RemovePianoRollEntryMessage msg) {
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		itm.getInstrument().removePianoRollEntry(msg.getEntryId());		
	}
	
	public static GetInstrumentConfigurationResponse getInstrumentConfig(GetInstrumentConfigurationMessage msg) {
		GetInstrumentConfigurationResponse resp = new GetInstrumentConfigurationResponse();
		
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		
		resp.setInstrumentItemId(itm.getId());
		resp.setConfiguration(itm.getInstrument().getConfiguration());
		
		return resp;
	}

	public static void setTrackItemInfo(SetTrackItemInfo msg) {
		TrackItem track = project.getTrackLine(msg.getTrackLineId()).getTrack(msg.getTrackItemId());
		track.setEnd(msg.getEnd());
		track.setStart(msg.getStart());
	}

	public static void setPianoRollEntry(SetPianoRollEntryMessage msg) {
		InstrumentItem itm = project.getInstrumentItem(msg.getInstrumentItemId());
		PianoRollEntry entry = itm.getInstrument().getPianoRoll().get(msg.getEntryId());//addPianoRollEntry(msg.getEntryId(), msg.getNote(), msg.getWhen(), msg.getDuration());
		
		entry.duration = msg.getDuration();
		entry.when = msg.getWhen();		
	}	
	
}
