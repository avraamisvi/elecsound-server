package br.com.elecsound.messages;

public class SetLoopIndexMessage extends Message {
	
	String instrumentItemId;
	boolean pianoRoll;
	
	public SetLoopIndexMessage() {
		this.name = MessageConstants.SET_INSTRUMENT_MODE;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public boolean isPianoRoll() {
		return pianoRoll;
	}
}