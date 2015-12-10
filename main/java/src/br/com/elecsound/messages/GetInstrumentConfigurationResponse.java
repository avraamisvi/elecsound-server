package br.com.elecsound.messages;

import br.com.elecsound.project.InstrumentConfiguration;

public class GetInstrumentConfigurationResponse extends MessageResponse{
	
	InstrumentConfiguration configuration;
	String instrumentItemId;
	
	public GetInstrumentConfigurationResponse() {
		super(MessageConstants.GET_INSTRUMENT_CONFIG);
	}
	
	public InstrumentConfiguration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(InstrumentConfiguration configuration) {
		this.configuration = configuration;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}
}
