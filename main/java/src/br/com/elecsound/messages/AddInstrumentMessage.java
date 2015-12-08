package br.com.elecsound.messages;

public class AddInstrumentMessage extends Message {
	
	private String instrumentItemId;//instrumentItem id
	private String instrumentId;
	private int position;
	
	public AddInstrumentMessage() {
		this.name = MessageConstants.ADD_INSTRUMENT;
	}
	
	public int getPosition() {
		return position;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public String getInstrumentId() {
		return instrumentId;
	}
	
}
