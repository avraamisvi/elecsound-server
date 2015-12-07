package br.com.elecsound.project;

public class TrackItem {
	
	private String id;
	private Instrument instrument;
	
	private double start;
	private double end;
	
	public TrackItem(String id, Instrument instrument) {
		this.instrument = instrument;
		this.id = id;
	}
	
	public void play() {
		
		double time = 0;
		
		for (int i = 0; i < instrument.loopSequence.length; i++) {
			
			if(time < end) {
				int state = instrument.loopSequence[i];
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
}
