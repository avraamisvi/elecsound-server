package br.com.elecsound.messages;

public class PlayInstrumentMessage extends Message {
	
	String instrumentItemId;
	int note;
	
	public PlayInstrumentMessage() {
		this.name = MessageConstants.PLAY_INSTRUMENT;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

}