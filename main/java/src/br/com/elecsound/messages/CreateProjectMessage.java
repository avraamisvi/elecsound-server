package br.com.elecsound.messages;

public class CreateProjectMessage extends Message {
	
	private String projectName;
	
	public CreateProjectMessage() {
		this.name = MessageConstants.CREATE_PROJECT;
	}

	public String getProjectName() {
		return projectName;
	}
	
}
