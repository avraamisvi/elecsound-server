package br.com.elecsound.messages;

public class SaveProjectMessage extends Message {
	
	String filePath;
	
	public SaveProjectMessage() {
		this.name = MessageConstants.SAVE_PROJECT;
	}

	public String getFilePath() {
		return filePath;
	}
}
