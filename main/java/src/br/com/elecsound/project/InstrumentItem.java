package br.com.elecsound.project;

import br.com.elecsound.engine.Player;

public class InstrumentItem {
	
	private String id;
	private Instrument instrument;
	private Color color;
	private int position;
	
	public InstrumentItem(String id, Instrument instrument, Color color, int position) {
		this.id = id;
		this.instrument = instrument;
		this.color = color;
		this.position = position;
	}

	public Instrument getInstrument() {
		return instrument;
	}
	
	public Instrument createInstrument() {
		return instrument.copy();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getPosition() {
		return position;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getId() {
		return id;
	}

	public void connect(Player player) {
		this.instrument.connect(player);
	}
	
	public void disconnect() {
		this.instrument.disconnect();
	}	
}
