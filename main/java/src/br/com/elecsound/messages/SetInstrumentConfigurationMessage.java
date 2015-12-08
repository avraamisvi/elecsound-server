package br.com.elecsound.messages;

import br.com.elecsound.project.InstrumentConfiguration;

public class SetInstrumentConfigurationMessage extends Message {
	
	String instrumentItemId;
	InstrumentConfiguration configuration;
	
	public SetInstrumentConfigurationMessage() {
		this.name = MessageConstants.SET_INSTRUMENT_CONFIGURATION;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}
	
	public InstrumentConfiguration getConfiguration() {
		return configuration;
	}
}
