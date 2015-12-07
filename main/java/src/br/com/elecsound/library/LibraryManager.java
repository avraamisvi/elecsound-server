package br.com.elecsound.library;

import java.util.ArrayList;
import java.util.List;

import br.com.elecsound.messages.ListInstrumentsResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Allows acces to instruments library installed in the server.
 * 
 * @author abraao
 *
 */
public class LibraryManager {
	
	List<LibraryGroup> groups = new ArrayList<LibraryGroup>();
	
	public LibraryManager() {
		
		LibraryGroup teste = new LibraryGroup();
		
		LibraryItem itm = new LibraryItem("1","instrument 1");
		teste.instruments.add(itm);
		
		itm = new LibraryItem("2","instrument 2");
		teste.instruments.add(itm);
		
		groups.add(teste);
	}
	
	public JsonElement listInstruments() {
		Gson gson = new Gson();
		
		ListInstrumentsResponse msg = new ListInstrumentsResponse(this.groups);
		
		return gson.toJsonTree(msg);
	}
}
