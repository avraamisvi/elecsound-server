package br.com.elecsound.messages;

import java.util.List;

import br.com.elecsound.library.LibraryGroup;

public class ListInstrumentsResponse extends MessageResponse {

	List<LibraryGroup> groups;
	
	public ListInstrumentsResponse(List<LibraryGroup> groups) {
		super(MessageConstants.LIST_INSTRUMENTS);
		this.groups = groups;
	}
}
