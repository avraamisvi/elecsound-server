package br.com.elecsound.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.library.LibraryManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ProjectFileParser {
	
	private static Gson gson;
	
	public static JsonObject toJson(Project project) {
		
		gson = new Gson();
		
		JsonObject json = new JsonObject();
		
		json.addProperty("name", project.getName());
		json.addProperty("fileName", project.getFileName());
		
		json.add("instrumentsItem", instrumentItemListToJson(project.getInstruments()));
		json.add("trackLines", trackLinesToJson(project.getTrackLines()));
		json.add("settings", gson.toJsonTree(project.getSettings()));
		
		return json;
	}
	
	private static JsonArray trackLinesToJson(List<TrackLine> trackLines) {
		JsonArray array = new JsonArray();
		
		for(TrackLine trl : trackLines) {
			array.add(trackLineToJson(trl));
		}
		
		return array;
	}
	
	private static JsonObject trackLineToJson(TrackLine trl) {
		JsonObject obj = new JsonObject();
		
		obj.addProperty("id", trl.getId());
		obj.addProperty("name", trl.getName());
		obj.add("tracks", trackItemsToJson(trl.getTracks()));
		
		return obj;
	}
	
	private static JsonObject trackItemsToJson(HashMap<String, TrackItem> tracks) {
		JsonObject obj = new JsonObject();
		
		for (TrackItem trackItem : tracks.values()) {
			obj.add(trackItem.getId(), trackItemToJson(trackItem));
		}
		
		return obj;
	}	
	
	private static JsonObject trackItemToJson(TrackItem itm) {
		JsonObject obj = new JsonObject();
		
		obj.addProperty("id",itm.getId());
		obj.addProperty("instrumentItem", itm.getInstrumentItem().getId());
		obj.addProperty("start", itm.getStart());
		obj.addProperty("end", itm.getEnd());
		
		return obj;
	}
	
	private static JsonArray instrumentItemListToJson(List<InstrumentItem> itms) {
		JsonArray arr = new JsonArray();

		for(InstrumentItem itm : itms) {
			arr.add(instrumentItemToJson(itm));
		}
		
		return arr;
	}	
	
	private static JsonObject instrumentItemToJson(InstrumentItem itm) {
		JsonObject obj = new JsonObject();

		obj.addProperty("id",itm.getId());
		obj.addProperty("instrument", itm.getInstrument().getId());
		obj.addProperty("position", itm.getPosition());
		
		return obj;
	}
	
	//============================ READING ===============================================================
	
	public static JsonObject loadProject(Project project, File file) throws FileNotFoundException {
		gson = new Gson();
		
		FileReader reader = new FileReader(file);
		JsonObject obj = gson.fromJson(reader, JsonObject.class);
		
		project.setName(obj.get("name").getAsString());
		project.setName(obj.get("fileName").getAsString());		
		
		instrumentItemListFromJson(project, obj.get("instrumentsItem").getAsJsonArray());
		trackLinesFromJson(project, obj.get("trackLines").getAsJsonArray());
		
		return obj;
	}
	
	private static void trackLinesFromJson(Project project, JsonArray array) {
		
		for(int i = 0; i < array.size(); i++) {
			JsonObject lineJson = array.get(i).getAsJsonObject();
			project.addTrackLine(trackLineFromJson(project, lineJson));
		}
		
	}
	
	private static TrackLine trackLineFromJson(Project project, JsonObject obj) {
		
		TrackLine line = new TrackLine(obj.get("id").getAsString(), obj.get("name").getAsString());
		
		trackItemsFromJson(project, line, obj.get("tracks").getAsJsonArray());
		
		return line;
	}
	
	private static void trackItemsFromJson(Project project, TrackLine line, JsonArray array) {
		
		for(int i = 0; i < array.size(); i++) {
			JsonObject obj = array.get(i).getAsJsonObject();
			line.add(trackItemFromJson(project, obj));
		}
		
	}	
	
	private static TrackItem trackItemFromJson(Project project, JsonObject obj) {
		
		String id = obj.get("id").getAsString();
		String instrumentItemId = obj.get("instrumentItem").getAsString();
		double start = obj.get("start").getAsDouble();
		double end = obj.get("end").getAsDouble();

		TrackItem item = new TrackItem(id, project.getInstrumentItem(instrumentItemId));
		item.setStart(start);
		item.setEnd(end);
		
		return item;
	}
	
	private static void instrumentItemListFromJson(Project project, JsonArray array) {

		for(int i = 0; i < array.size(); i++) {
			JsonObject itemJson = array.get(i).getAsJsonObject();
			project.addInstrumentItem(instrumentItemFromJson(project, itemJson));
		}
	}	
	
	private static InstrumentItem instrumentItemFromJson(Project project, JsonObject obj) {

		InstrumentItem item = new InstrumentItem(obj.get("id").getAsString(), 
				LibraryManager.createInstrument(obj.get("instrument").getAsString()), obj.get("position").getAsInt());
		PlayerManager.loadInstrumentItem(item);
		
		return item;
	}
	
	public static void saveToFile(Project project, String fileName) throws IOException {
		project.setFileName(fileName);
		File file = new File(fileName);
		
		JsonObject data = ProjectFileParser.toJson(project);
		
		FileWriter writer = new FileWriter(file);
		writer.write(data.toString());
		writer.close();
	}	
}
