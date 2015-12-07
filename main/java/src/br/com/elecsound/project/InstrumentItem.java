package br.com.elecsound.project;

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
		return instrument;
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
}
