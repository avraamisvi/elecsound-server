package br.com.elecsound.messages;

public class GetInstrumentConfigurationMessage extends MessageResponse{
	
	String instrumentItemId;
	
	public GetInstrumentConfigurationMessage() {
		super(MessageConstants.GET_INSTRUMENT_CONFIG);
	}
	
	public String getInstrumentItemId() {
		return instrumentItemId;
	}

}
