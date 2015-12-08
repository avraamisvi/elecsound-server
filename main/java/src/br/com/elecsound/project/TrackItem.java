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
		
		double time = 0;
		
		for (int i = 0; i < instrument.getLoopSequence().length; i++) {
			
			if(time < end) {
				int state = instrument.getLoopSequence()[i];
				if(state == Instrument.LOOP_PLAY) {
					instrument.playLoopIndex(i, start + time);
				}
			} else {
				break;
			}
			
			time = time + Instrument.LOOP_PLAY_TIMESTAMP;
		}
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
	
}
