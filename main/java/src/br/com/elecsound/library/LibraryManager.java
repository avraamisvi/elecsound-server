package br.com.elecsound.library;

import java.util.ArrayList;
import java.util.List;

import br.com.elecsound.messages.ListInstrumentsResponse;
import br.com.elecsound.project.Instrument;
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
		
//		LibraryGroup teste = new LibraryGroup();
//		teste.name = "teste";
//		
//		LibraryItem itm = new LibraryItem("1","instrument 1");
//		teste.instruments.put("1", itm);
//		
//		itm = new LibraryItem("2","instrument 2");
//		teste.instruments.put("2", itm);
		
		for(int i = 0; i < 30; i++) {
			LibraryGroup teste = new LibraryGroup();
			teste.name = "teste" + i;
			
			LibraryItem itm = new LibraryItem("1"+i,"instrument 1"+i);
			teste.instruments.put("1"+i, itm);
			
			itm = new LibraryItem("2"+i,"instrument 2"+i);
			teste.instruments.put("2"+i, itm);			
			
			groups.add(teste);
		}
		
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
		return new SineOscilator();
	}
}
