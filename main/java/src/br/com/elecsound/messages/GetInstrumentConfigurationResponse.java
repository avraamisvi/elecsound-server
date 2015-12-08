package br.com.elecsound.messages;

import br.com.elecsound.project.InstrumentConfiguration;

public class GetInstrumentConfigurationResponse extends MessageResponse{
	
	InstrumentConfiguration configuration;
	
	public GetInstrumentConfigurationResponse() {
		super(MessageConstants.GET_INSTRUMENT_CONFIG);
	}
	
	public InstrumentConfiguration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(InstrumentConfiguration configuration) {
		this.configuration = configuration;
	}
}
