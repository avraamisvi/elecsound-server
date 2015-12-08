package br.com.elecsound.messages;

public class RemoveTrackItemMessage extends Message {
	
	private String trackLineId;
	private String trackItemId;
	
	public RemoveTrackItemMessage() {
		this.name = MessageConstants.REMOVE_TRACK_ITEM;
	}

	public String getTrackItemId() {
		return trackItemId;
	}

	public void setTrackItemId(String trackItemId) {
		this.trackItemId = trackItemId;
	}
	
	public String getTrackLineId() {
		return trackLineId;
	}
}