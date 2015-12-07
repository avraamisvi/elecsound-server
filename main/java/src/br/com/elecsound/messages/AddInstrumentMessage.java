package br.com.elecsound.messages;

public class AddInstrumentMessage extends Message {
	
	private String id;//instrument id
	
	public AddInstrumentMessage() {
		this.name = MessageConstants.ADD_INSTRUMENT;
	}
	
	public String getId() {
		return id;
	}
}
