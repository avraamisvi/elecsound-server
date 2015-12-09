package br.com.elecsound.messages;

public class SetTrackItemInfo extends Message {
	private String trackLineId;
	private String trackItemId;
	
	private double start;
	private double end;
	
	public SetTrackItemInfo() {
		super();
		this.name = MessageConstants.SET_TRACKITEM_INFO;
	}
	
	public double getStart() {
		return start;
	}
	public void setStart(double start) {
		this.start = start;
	}
	public double getEnd() {
		return end;
	}
	public void setEnd(double end) {
		this.end = end;
	}

	public String getTrackLineId() {
		return trackLineId;
	}

	public void setTrackLineId(String trackLineId) {
		this.trackLineId = trackLineId;
	}

	public String getTrackItemId() {
		return trackItemId;
	}

	public void setTrackItemId(String trackItemId) {
		this.trackItemId = trackItemId;
	}
	
}
