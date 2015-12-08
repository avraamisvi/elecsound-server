package br.com.elecsound.messages;

import br.com.elecsound.project.ProjectSettings;

public class SetProjectSettingsMessage extends Message {
	
	ProjectSettings settings;
	
	public SetProjectSettingsMessage() {
		this.name = MessageConstants.SET_PROJECT_SETTINGS;
	}

	public ProjectSettings getSettings() {
		return settings;
	}
}