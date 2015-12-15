package br.com.elecsound.messages;

public class SetPianoRollEntryMessage extends Message {
	
	String instrumentItemId;
	String entryId;
	double duration;
	double when;
	
	public SetPianoRollEntryMessage() {
		this.name = MessageConstants.SET_PIANOROLL_ENTRY;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public String getEntryId() {
		return entryId;
	}

	public double getDuration() {
		return duration;
	}

	public double getWhen() {
		return when;
	}
	
}