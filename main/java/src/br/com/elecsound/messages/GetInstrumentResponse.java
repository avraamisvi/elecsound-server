package br.com.elecsound.messages;

import br.com.elecsound.project.Instrument;

public class GetInstrumentResponse extends MessageResponse {

	Instrument instrument;
	
	public GetInstrumentResponse(Instrument instrument) {
		super(MessageConstants.GET_INSTRUMENT_CONFIG);
		this.instrument= instrument;
	}
}
