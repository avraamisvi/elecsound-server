package br.com.elecsound.messages;

public class SetInstrumentMutedMessage extends Message {
	
	String instrumentItemId;
	boolean muted;
	
	public SetInstrumentMutedMessage() {
		super();
		this.name = MessageConstants.SET_INSTRUMENT_MUTED;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	
}
