package br.com.elecsound.messages;

public class CreateProjectResponse extends MessageResponse {
	
	public String status;
	
	public CreateProjectResponse() {
		super(MessageConstants.CREATE_PROJECT);
	}
	
}
