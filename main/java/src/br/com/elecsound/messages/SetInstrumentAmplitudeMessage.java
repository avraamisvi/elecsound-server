package br.com.elecsound.messages;

public class SetInstrumentAmplitudeMessage extends Message {
	
	String instrumentItemId;
	double amplitude;
	
	public SetInstrumentAmplitudeMessage() {
		super();
		this.name = MessageConstants.SET_INSTRUMENT_AMPLITUDE;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public void setInstrumentItemId(String instrumentItemId) {
		this.instrumentItemId = instrumentItemId;
	}

	public double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}

}
