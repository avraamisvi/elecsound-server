package br.com.elecsound.library;

import java.util.ArrayList;
import java.util.List;

import br.com.elecsound.messages.ListInstrumentsResponse;
import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.instruments.GoogleWaveOscilator;
import br.com.elecsound.project.instruments.SineOscilator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Represents the instruments library.
 * 
 * @author abraao
 *
 */
public class LibraryManager {
	
	private static List<LibraryGroup> groups = new ArrayList<LibraryGroup>();
	private static Gson gson;
	
	private LibraryManager(){}
	
	public static void load() {
		gson = new Gson();
		
		LibraryGroup basic = new LibraryGroup();
		basic.name = "Oscilators";
		
		LibraryItem itm = new LibraryItem("SineOscilator","Sine OSC");
		basic.instruments.put("SineOscilator", itm);
		
		itm = new LibraryItem("GoogleWaveOscilator","Google Wave OSC");
		basic.instruments.put("GoogleWaveOscilator", itm);	
		
		groups.add(basic);
		
	}
	
	public static ListInstrumentsResponse listInstruments() {
		
		ListInstrumentsResponse msg = new ListInstrumentsResponse(groups);
		
		return msg;
	}
	
	/**
	 * Creates a new instrument by its id.
	 * 
	 * @param id
	 * @return
	 */
	public static Instrument createInstrument(String id) {//TODO
		
		if(id.equals("SineOscilator")) {
			return new SineOscilator();
		} else {
			return new GoogleWaveOscilator();
		}
		
	}
}
