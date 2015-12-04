package br.com.elecsound.messages;

import java.util.List;

import br.com.elecsound.library.LibraryGroup;

public class ListInstrumentsMessage {

	String name;
	List<LibraryGroup> groups;
	
	public ListInstrumentsMessage(List<LibraryGroup> groups) {
		this.name = MessageConstants.LIST_INSTRUMENTS;
		this.groups = groups;
	}
}
