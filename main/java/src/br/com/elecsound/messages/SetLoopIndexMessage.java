package br.com.elecsound.messages;

public class SetLoopIndexMessage extends Message {
	
	String instrumentItemId;
	int index;
	int state;
	
	public SetLoopIndexMessage() {
		this.name = MessageConstants.SET_INSTRUMENT_MODE;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}
	
}