package br.com.elecsound.messages;


public class RemoveInstrumentMessage extends Message {
	
	String instrumentItemId;
	
	public RemoveInstrumentMessage() {
		this.name = MessageConstants.REMOVE_INSTRUMENT;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

}