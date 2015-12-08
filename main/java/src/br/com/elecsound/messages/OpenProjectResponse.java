package br.com.elecsound.messages;

import com.google.gson.JsonObject;

public class OpenProjectResponse extends MessageResponse {

	JsonObject project;
	
	public OpenProjectResponse(JsonObject project) {
		super(MessageConstants.OPEN_PROJECT);
		this.project = project;
	}
}
