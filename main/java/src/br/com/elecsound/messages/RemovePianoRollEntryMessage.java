package br.com.elecsound.messages;

public class RemovePianoRollEntryMessage extends Message {
	
	String instrumentItemId;
	String entryId;
	
	public RemovePianoRollEntryMessage() {
		this.name = MessageConstants.REMOVE_PIANOROLL_ENTRY;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public String getEntryId() {
		return entryId;
	}
	
}
