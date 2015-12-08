package br.com.elecsound.project.instrument.form;

public class FormFactory {

	public static Form loadForm(String instrumentId) {
		return new Form(100, 100);
	}
}
