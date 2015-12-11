package br.com.elecsound.project;

//TODO criar um listener para os eventos de delete do instrumentItem?
public class TrackItem {
	
	private String id;
	private InstrumentItem instrumentItem;
	private Instrument instrument;
	
	private double start;
	private double end;
	
	public TrackItem(String id, InstrumentItem instrumentItem) {
		this.instrument = instrumentItem.createInstrument();
		this.instrumentItem = instrumentItem;
		this.id = id;
	}
	
	public void play() {		
		instrument.play(start, end);
	}
	
	public void setStart(double start) {
		this.start = start;
	}
	
	public void setEnd(double end) {
		this.end = end;
	}
	
	public String getId() {
		return id;
	}

	public void disconnect() {
		this.instrument.disconnect();
	}
	
	public double getStart() {
		return start;
	}
	
	public double getEnd() {
		return end;
	}
	
	public InstrumentItem getInstrumentItem() {
		return instrumentItem;
	}
	
	public void stop() {
		instrument.noteOff();
	}
	
}
