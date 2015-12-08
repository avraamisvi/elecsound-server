package br.com.elecsound.messages;

public class RemoveTrackLineMessage extends Message {
	
	private String trackLineId;
	
	public RemoveTrackLineMessage() {
		this.name = MessageConstants.REMOVE_TRACK_LINE;
	}
	
	public String getTrackLineId() {
		return trackLineId;
	}

}