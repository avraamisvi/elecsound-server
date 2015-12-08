package br.com.elecsound.messages;


public class OpenProjectMessage extends Message {
	
	String fileName;
	
	public OpenProjectMessage() {
		this.name = MessageConstants.OPEN_PROJECT;
	}

	public String getFileName() {
		return fileName;
	}

}