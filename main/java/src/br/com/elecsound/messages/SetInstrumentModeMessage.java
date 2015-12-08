package br.com.elecsound.messages;

public class SetInstrumentModeMessage extends Message {
	
	String instrumentItemId;
	boolean pianoRoll;
	
	public SetInstrumentModeMessage() {
		this.name = MessageConstants.SET_INSTRUMENT_MODE;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public boolean isPianoRoll() {
		return pianoRoll;
	}
}
