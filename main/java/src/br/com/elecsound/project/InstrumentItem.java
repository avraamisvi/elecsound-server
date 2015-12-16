package br.com.elecsound.project;

import br.com.elecsound.engine.Player;

public class InstrumentItem {
	
	private String id;
	private Instrument instrument;
	private int position;
	
	public InstrumentItem(String id, Instrument instrument, int position) {
		this.id = id;
		this.instrument = instrument;
		this.position = position;
	}

	public Instrument getInstrument() {
		return instrument;
	}
	
	public Instrument createInstrument() {
		return instrument.copy();
	}
	
	public int getPosition() {
		return position;
	}
	
	public String getId() {
		return id;
	}
	
	public void connect(Player player) {
		this.instrument.connect(player);
	}
	
	public void disconnect() {
		this.instrument.disconnectPianoRoll();
		this.instrument.disconnect();
	}
}
