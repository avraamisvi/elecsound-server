package br.com.elecsound.messages;

public class RemovePianoRollEntryMessage extends Message {
	
	String instrumentItemId;
	String entryId;
	
	public RemovePianoRollEntryMessage() {
		this.name = MessageConstants.REMOVE_PIANO_ROLL_ENTRY;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public String getEntryId() {
		return entryId;
	}
	
}
