package br.com.elecsound.messages;

public class AddPianoRollEntryMessage extends Message {
	
	String instrumentItemId;
	String entryId;
	int note;
	double duration;
	double when;
	
	public AddPianoRollEntryMessage() {
		this.name = MessageConstants.ADD_PIANOROLL_ENTRY;
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

	public double getDuration() {
		return duration;
	}

	public double getWhen() {
		return when;
	}
	
}