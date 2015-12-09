package br.com.elecsound.messages;

public class StopInstrumentMessage extends Message {
	
	String instrumentItemId;
	
	public StopInstrumentMessage() {
		this.name = MessageConstants.STOP_INSTRUMENT;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}

}
