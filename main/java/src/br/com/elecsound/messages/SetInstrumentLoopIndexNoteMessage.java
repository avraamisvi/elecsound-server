package br.com.elecsound.messages;

public class SetInstrumentLoopIndexNoteMessage extends Message {
	
	String instrumentItemId;
	int index;
	int note;
	
	public SetInstrumentLoopIndexNoteMessage() {
		this.name = MessageConstants.SET_INSTRUMENT_LOOP_INDEX_NOTE;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}
	
}