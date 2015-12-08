package br.com.elecsound.messages;

public class AddTrackItemMessage extends Message {
	
	private String trackLineId;
	private String trackItemId;
	private String instrumentItemId;
	
	private double start;
	private double end;
	
	public AddTrackItemMessage() {
		this.name = MessageConstants.ADD_TRACK_ITEM;
	}
	
	public String getTrackLineId() {
		return trackLineId;
	}

	public String getTrackItemId() {
		return trackItemId;
	}

	public String getInstrumentItemId() {
		return instrumentItemId;
	}

	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}

}