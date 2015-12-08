package br.com.elecsound.messages;

public class AddPianoRollEntryMessage extends Message {
	
	String instrumentItemId;
	String entryId;
	int note;
	
	public AddPianoRollEntryMessage() {
		this.name = MessageConstants.ADD_PIANO_ROLL_ENTRY;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public String getEntryId() {
		return entryId;
	}
	
	public int getNote() {
		return note;
	}
}