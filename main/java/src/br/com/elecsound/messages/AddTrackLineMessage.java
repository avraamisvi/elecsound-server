package br.com.elecsound.messages;

public class AddTrackLineMessage extends Message {
	
	private String trackLineId;
	private String trackLineName;
	private int position;
	
	public AddTrackLineMessage() {
		this.name = MessageConstants.ADD_TRACK_LINE;
	}
	
	public int getPosition() {
		return position;
	}

	public String getTrackLineId() {
		return trackLineId;
	}

	public String getTrackLineName() {
		return trackLineName;
	}
	
}
